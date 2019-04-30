package com.samsbeauty.warehouse.picking.sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExport;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExportSort;

public class PickingSorting {
	public final static int TURN_SECTION = 5; // Temporary value for turning point when picker reach this section number, the next starting picking point is end of the aisle. You should add a function to calculate this value later. 
	public static List<GroupPickingItemExport> pickingItemSortByLevel(List<GroupPickingItemExport> list) {

		List<GroupPickingItemExportSort> noLocation = new ArrayList<>();
		List<GroupPickingItemExportSort> hasLocation = new ArrayList<>();
		
		// Find item which has location data. We cannot assume that all items have its location data.
		for(GroupPickingItemExport export : list) {
			if(export.getLocations() == null || export.getLocations().size() == 0) {
				GroupPickingItemExportSort item = new GroupPickingItemExportSort(export);
				noLocation.add(item);				
			} else {
				GroupPickingItemExportSort item = new GroupPickingItemExportSort(export);
				hasLocation.add(item);
			}
		}
		noLocation = sortForNoLocation(noLocation);
		Map<String, List<GroupPickingItemExportSort>> groupByWarehouse = new LinkedHashMap<>();
		// Divide group by warehouse
		for(GroupPickingItemExportSort item : hasLocation) {
			String location = item.getData().getLocations().get(0);
			String[] locationAry = location.split("-");		
			String warehouseTag = locationAry[0];

			String aisle = locationAry[1];
			// change aisle to integer value
			// Aisle could have A or B at the last so convert it to some value;
			Integer aisleNum = 0;
			if(aisle.endsWith("A") || aisle.endsWith("B")) {
				aisleNum = Integer.valueOf(aisle.substring(0, aisle.length() - 1));
				aisleNum *= 10; // If aisle is 10A it will be 100 for base. And if the last character is 'A' then plus 1 and if the last character is 'B' then plus 2 to sort. 
				if(aisle.endsWith("A")) {
					aisleNum += 1;
				} else {
					aisleNum += 2;
				}
			} else {
				aisleNum = Integer.valueOf(aisle); // for example aisle code is 10 then it will be 100
				aisleNum *= 10;
			}
			item.setAisle(aisleNum);
			String subgroup = locationAry[2];
			item.setSubgroup(Integer.valueOf(subgroup));

			String level = locationAry[3];
			item.setLevel(Integer.valueOf(level));
			
			if(groupByWarehouse.containsKey(warehouseTag)) {
				List<GroupPickingItemExportSort> l = groupByWarehouse.get(warehouseTag);
				l.add(item);
			} else {
				List<GroupPickingItemExportSort> l = new ArrayList<>();
				l.add(item);
				groupByWarehouse.put(warehouseTag, l);
			}
		}
		
		
		for(Entry<String, List<GroupPickingItemExportSort>> entry : groupByWarehouse.entrySet()) {
			List<GroupPickingItemExportSort> sortedList = sortForLocation(entry.getValue());

			groupByWarehouse.put(entry.getKey(), sortedList);
		}
		
		// Now, sort by subgroup number.
		// If there are items in subgroup 1, 2, and 3 at aisle 10A
		// Then 10B should start subgroup reversely like 3, 2, 1 
		
		for(Entry<String, List<GroupPickingItemExportSort>> entry : groupByWarehouse.entrySet()) {
			
			List<Pair<Integer, Integer>> reverseList = getReverseList(entry.getValue());
			for(Pair<Integer, Integer> p : reverseList) {
				reversePickingGroupSort(entry.getValue(), p.getLeft(), p.getRight());
			}
		}
		
		List<GroupPickingItemExport> returnList = new ArrayList<>();
		for(Entry<String, List<GroupPickingItemExportSort>> entry : groupByWarehouse.entrySet()) {
			for(GroupPickingItemExportSort item : entry.getValue()) {
				returnList.add(item.getData());	
			}
		}
		for(GroupPickingItemExportSort item : noLocation) {
			returnList.add(item.getData());
		}
		return returnList;
	}
	public static List<Pair<Integer, Integer>> getReverseList(List<GroupPickingItemExportSort> list) {
		List<Pair<Integer, Integer>> reverseList = new ArrayList<>();
		int start = 0;
		boolean reverse = false;
		int i=0;
		for(i=0; i<list.size()-1; i++) {
			GroupPickingItemExportSort item = list.get(i);
			GroupPickingItemExportSort nextItem = list.get(i+1);
			if(item.getAisle() != nextItem.getAisle()) {
				if(!reverse) {					
					start = i + 1;
					if(item.getSubgroup() > TURN_SECTION){
						reverse = true;
					}
				} else {
					if(start != i){
						reverseList.add(new ImmutablePair<>(start, i));
						reverse = false;
					}
				}
			}
		}
		if(reverse && start != i){ 
			reverseList.add(new ImmutablePair<>(start, i));
		}
		return reverseList;
	}
	public static void reversePickingGroupSort(List<GroupPickingItemExportSort> list, int start, int end) {
		if(list != null && list.size() > 0 && start < end) { // just check base case;
			int last = end;
			for(int i=start; i < (start+end+1)/2; i++) {
				GroupPickingItemExportSort item = list.get(i);
				GroupPickingItemExportSort chgItem = list.get(last);
				
				list.set(i, chgItem);
				list.set(last, item);
				
				last --;
			}
		}
	}
	
	public static List<GroupPickingItemExportSort> sortForNoLocation(List<GroupPickingItemExportSort> list) {
		int length = list.size();
		if(length <= 1) return list;

		int middle = (int) Math.ceil( (double) list.size() / 2 );
		GroupPickingItemExportSort pivotObj = list.get(middle);
		List<GroupPickingItemExportSort> left = new ArrayList<>();
		List<GroupPickingItemExportSort> right = new ArrayList<>();
		for(int i=0; i<length; i++) {
			if(i != middle) {
				GroupPickingItemExportSort item = list.get(i);
				if(item.getData().getGeneratedBarcode().compareTo(pivotObj.getData().getGeneratedBarcode()) > 0) {
					right.add(item);
				} else {
					left.add(item);
				}
			}
		}
		return merge(sortForNoLocation(left), pivotObj, sortForNoLocation(right));
	}
	
	public static List<GroupPickingItemExportSort> sortForLocation(List<GroupPickingItemExportSort> list) {
		int length = list.size();
		if(length <= 1) return list;
		int middle = (int) Math.ceil( (double) list.size() / 2 );
		GroupPickingItemExportSort pivotObj = list.get(middle);
				
		List<GroupPickingItemExportSort> left = new ArrayList<>();
		List<GroupPickingItemExportSort> right = new ArrayList<>();
		
		for(int i=0; i<length; i++) {
			GroupPickingItemExportSort item = list.get(i);
			if(i != middle) {
				if(item.getAisle() > pivotObj.getAisle()) {
					right.add(item);
				} else if(item.getAisle().equals(pivotObj.getAisle())){
					if(item.getSubgroup() > pivotObj.getSubgroup()) {
						
						right.add(item);
					} else if(item.getSubgroup().equals(pivotObj.getSubgroup())) {					
						if(item.getLevel() > pivotObj.getLevel()) {
							right.add(item);
						} else {
							left.add(item);
						}
						
					} else {
						left.add(item);
					}
				} else {
					left.add(item);
				}
			}
		}
		
		return merge(sortForLocation(left), pivotObj, sortForLocation(right));
	}
	
	public static ArrayList<GroupPickingItemExportSort> merge(List<GroupPickingItemExportSort> left, GroupPickingItemExportSort pivot, List<GroupPickingItemExportSort> right) {
		ArrayList<GroupPickingItemExportSort> list = new ArrayList<>();
		for(GroupPickingItemExportSort item : left) {
			list.add(item);
		}
		if(pivot != null) {
			list.add(pivot);	
		}
		for(GroupPickingItemExportSort item : right) {
			list.add(item);
		}
		return list;
	}
}
