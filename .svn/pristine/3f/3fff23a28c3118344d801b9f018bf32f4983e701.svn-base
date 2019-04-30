package com.samsbeauty.warehouse.picking.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExport;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExportSort;

public class PickingSortingTest {

	@Test
	public void getReverseListTest() {
		GroupPickingItemExport export = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-10A-01-01"))
				.setOption("test")
				.setQuantity(10)
				.setStock(10)
				.createOrderItemExport();

		GroupPickingItemExportSort sortingExport = new GroupPickingItemExportSort(export);
		sortingExport.setAisle(101);
		sortingExport.setSubgroup(1);
		GroupPickingItemExportSort sortingExport2 = new GroupPickingItemExportSort(export);
		sortingExport2.setAisle(101);
		sortingExport2.setSubgroup(2);
		GroupPickingItemExportSort sortingExport3 = new GroupPickingItemExportSort(export);
		sortingExport3.setAisle(102);
		sortingExport3.setSubgroup(1);
		GroupPickingItemExportSort sortingExport4 = new GroupPickingItemExportSort(export);
		sortingExport4.setAisle(102);
		sortingExport4.setSubgroup(2);
		
		List<GroupPickingItemExportSort> list = new ArrayList<>();
		list.add(sortingExport);
		list.add(sortingExport2);
		list.add(sortingExport3);
		list.add(sortingExport4);
		
		List<Pair<Integer,Integer>> pairs = PickingSorting.getReverseList(list);
		
		Assert.assertEquals(pairs.size(), 1);
		Pair<Integer, Integer> pair = pairs.get(0);
		Assert.assertEquals(pair.getLeft(), Integer.valueOf(2));
		Assert.assertEquals(pair.getRight(), Integer.valueOf(3));
		
	}

	@Test
	public void getReverseListTest2() {
		GroupPickingItemExport export = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-10A-01-01"))
				.setOption("test")
				.setQuantity(10)
				.setStock(10)
				.createOrderItemExport();

		GroupPickingItemExportSort sortingExport = new GroupPickingItemExportSort(export);
		sortingExport.setAisle(101);
		sortingExport.setSubgroup(1);
		GroupPickingItemExportSort sortingExport2 = new GroupPickingItemExportSort(export);
		sortingExport2.setAisle(101);
		sortingExport2.setSubgroup(2);
		GroupPickingItemExportSort sortingExport3 = new GroupPickingItemExportSort(export);
		sortingExport3.setAisle(102);
		sortingExport3.setSubgroup(1);
		GroupPickingItemExportSort sortingExport4 = new GroupPickingItemExportSort(export);
		sortingExport4.setAisle(102);
		sortingExport4.setSubgroup(2);
		GroupPickingItemExportSort sortingExport5 = new GroupPickingItemExportSort(export);
		sortingExport4.setAisle(103);
		sortingExport4.setSubgroup(1);
		GroupPickingItemExportSort sortingExport6 = new GroupPickingItemExportSort(export);
		sortingExport4.setAisle(103);
		sortingExport4.setSubgroup(2);
		
		List<GroupPickingItemExportSort> list = new ArrayList<>();
		list.add(sortingExport);
		list.add(sortingExport2);
		list.add(sortingExport3);
		list.add(sortingExport4);
		list.add(sortingExport5);
		list.add(sortingExport6);
		
		List<Pair<Integer,Integer>> pairs = PickingSorting.getReverseList(list);
		for(Pair<Integer, Integer> p : pairs) {
			System.out.println("left -> " + p.getLeft());
			System.out.println("right -> " + p.getRight());
		}
		
	}
	
	@Test
	public void reversePickingGrouipSortTest() {
		GroupPickingItemExport export = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-10A-01-01"))
				.setOption("test")
				.setQuantity(10)
				.setStock(10)
				.createOrderItemExport();

		GroupPickingItemExportSort sortingExport = new GroupPickingItemExportSort(export);
		sortingExport.setAisle(1);
		GroupPickingItemExportSort sortingExport2 = new GroupPickingItemExportSort(export);
		sortingExport2.setAisle(2);
		GroupPickingItemExportSort sortingExport3 = new GroupPickingItemExportSort(export);
		sortingExport3.setAisle(3);
		GroupPickingItemExportSort sortingExport4 = new GroupPickingItemExportSort(export);
		sortingExport4.setAisle(4);
		GroupPickingItemExportSort sortingExport5 = new GroupPickingItemExportSort(export);
		sortingExport5.setAisle(5);
		GroupPickingItemExportSort sortingExport6 = new GroupPickingItemExportSort(export);
		sortingExport6.setAisle(6);
		
		List<GroupPickingItemExportSort> list = new ArrayList<>();
		list.add(sortingExport);
		list.add(sortingExport2);
		list.add(sortingExport3);
		list.add(sortingExport4);
		list.add(sortingExport5);
		list.add(sortingExport6);
		// reverse from 1st to 4th 
		PickingSorting.reversePickingGroupSort(list, 0, 3);
		Assert.assertEquals(Integer.valueOf(4), list.get(0).getAisle());
		Assert.assertEquals(Integer.valueOf(1), list.get(3).getAisle());
	}
	
	@Test
	public void sortingTest() {
		GroupPickingItemExport export = GroupPickingItemExport
				.builder()
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-13A-04-01"))
				.createOrderItemExport();
		GroupPickingItemExport export2 = GroupPickingItemExport
				.builder()
				.setGeneratedBarcode("BBB")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-13A-04-03"))
				.createOrderItemExport();
		GroupPickingItemExport export3 = GroupPickingItemExport
				.builder()
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-13B-04-03"))
				.createOrderItemExport();
		GroupPickingItemExport export4 = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-13B-05-03"))
				.createOrderItemExport();
		GroupPickingItemExport export5 = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-14A-03-02"))
				.createOrderItemExport();
		GroupPickingItemExport export6 = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-15A-01-03"))
				.createOrderItemExport();

		GroupPickingItemExport export7 = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-15A-10-02"))
				.createOrderItemExport();

		GroupPickingItemExport export8 = GroupPickingItemExport
				.builder()
				.setDescription("test")
				.setGeneratedBarcode("AAA")
				.setLocationCodes(Arrays.asList("01010101"))
				.setLocations(Arrays.asList("HAIR-14A-09-02"))
				.createOrderItemExport();
		List<GroupPickingItemExport> list = Arrays.asList(export,export2,export3,export4,export5,export6,export7,export8);
		list = PickingSorting.pickingItemSortByLevel(list);
		for(GroupPickingItemExport item : list) {
			System.out.println("location = " + item.getLocations().get(0));
		}
	}
}
