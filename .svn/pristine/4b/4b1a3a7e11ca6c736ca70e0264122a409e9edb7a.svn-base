package com.samsbeauty.warehouse.picking.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.old.model.OrderInfoForPicking;
import com.samsbeauty.old.model.OrderItem;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.old.helper.OrderInfoHelper;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExport;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingJobExport;
import com.samsbeauty.warehouse.picking.model.MissingExport;
import com.samsbeauty.warehouse.picking.model.PackingExport;
import com.samsbeauty.warehouse.picking.model.PickingGroup;
import com.samsbeauty.warehouse.picking.model.PickingItem;
import com.samsbeauty.warehouse.picking.model.PickingItemInfo;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobExport;
import com.samsbeauty.warehouse.picking.model.PickingJobGroup;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.model.ProductType;
import com.samsbeauty.warehouse.picking.repository.PickingItemInfoRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobGroupRepository;
import com.samsbeauty.warehouse.picking.repository.PickingJobRepository;
import com.samsbeauty.warehouse.picking.sort.PickingSorting;
import com.samsbeauty.warehouse.service.WarehouseLevelService;
import com.samsbeauty.warehouse.util.PackingListExporter;
import com.samsbeauty.warehouse.util.PackingOrderComparator;
import com.samsbeauty.warehouse.util.PickingReportExporter;
import com.samsbeauty.warehouse.util.UrgentShippingChecking;

@Service
@Transactional
public class PickingJobServiceImpl implements PickingJobService {
	@Autowired private OrderInfoHelper orderInfoHelper;
	@Autowired private InventoryRestHelper inventoryRestHelper;
	@Autowired private PickingJobRepository pickingJobRepository;
	@Autowired private PickingJobGroupRepository pickingJobGroupRepository;
	@Autowired private PickingItemInfoRepository pickingItemInfoRepository;
	
	@Autowired private PickingJobExportService pickingJobExportService;	
	@Autowired private WarehouseLevelService warehouseLevelService;
	@Autowired private PackingExportService packingExportService;
	@Autowired private MissingExportService missingExportService;
	
	@Value("${pickinglist.pdf.dir}")
	private String groupPickingListDir;

	public Page<PickingJob> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingJobRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public PickingJob getOne(Long pickingJobId) {
		return pickingJobRepository.getOne(pickingJobId);
	}
	public PickingJob save(PickingJob pickingJob) {
		return pickingJobRepository.save(pickingJob);
	}
	
	public void createMissingList(PickingJobGroup pickingJobGroup, WarehouseEmployee warehouseEmployee, Map<String, PickingGroup> pickingGroupTable) throws Exception {
		List<GroupPickingJobExport> pickingJobExports = new ArrayList<>();
		for(PickingJob pickingJob : pickingJobGroup.getPickingJobs()) {
			// If picking job already has missing list, then delete it.
			if(pickingJob.getMissingExport() != null) {
				// delete file
				MissingExport missingExport = pickingJob.getMissingExport();
				
				String filePath = groupPickingListDir + "/" + missingExport.getFolder() + "/" + missingExport.getFilename();
				File file = new File(filePath);
				if(file.exists()) {
					file.delete();
				}
				missingExportService.delete(pickingJob.getMissingExport().getExportId());	
			}
			
			Map<String, Integer> pickingItemQty = new HashMap<>(); // This is for counting quantity because it could have multiple items that have same barcode.
			Map<String, PickingItem> pickingItems = new LinkedHashMap<>(); // This is for iterating second time only for missed items.
			List<String> barcodes = new ArrayList<>();
			for(PickingJobTimeline timeline : pickingJob.getPickingJobTimelines()){
				for(PickingItem item : timeline.getPickingItems()) {
					if(item.getPickingItemStatus().equals(PickingItem.PickingItemStatus.MISSED) || 
							item.getPickingItemStatus().equals(PickingItem.PickingItemStatus.WRONG_LOCATION)){
						barcodes.add(item.getGeneratedBarcode());
						String key = item.getGeneratedBarcode();
						if(pickingItemQty.containsKey(key)) { 
							pickingItemQty.put(key, pickingItemQty.get(key) + item.getQuantity());
						} else {
							pickingItemQty.put(key, item.getQuantity());
							pickingItems.put(key, item);
						}
					}
				}
			}
			List<Inventory> list = inventoryRestHelper.getInventoryList(barcodes);
			Map<String, Inventory> inventoryMap = new HashMap<>();
			for(Inventory inventory : list) {
				inventoryMap.put(inventory.getGeneratedBarcode(), inventory);
			}

			Map<String, GroupPickingItemExport> exportItems = new LinkedHashMap<>();
			for(PickingItem item : pickingItems.values()) {
				List<WarehouseLevel> levels = null;
				levels = warehouseLevelService.getAllByGeneratedBarcodeAndType(item.getGeneratedBarcode(), WarehouseLevel.LevelType.PICKING);				
				List<String> locationNames = new ArrayList<>();
				for(WarehouseLevel level : levels) {
					locationNames.add(level.getLocationName());
				}	
				Inventory inventory = inventoryMap.get(item.getGeneratedBarcode());
				GroupPickingItemExport itemExport = GroupPickingItemExport.builder()
						.setDescription(inventory.getTitle())
						.setGeneratedBarcode(item.getGeneratedBarcode())
						.setLocations(locationNames)
						.setOption(inventory.getProductOption())
						.setStock(Integer.valueOf(inventory.getStock()))
						.setProductType(inventory.getProductType())
						.setQuantity(item.getQuantity())
						.createOrderItemExport();
				exportItems.put(item.getGeneratedBarcode(), itemExport);
			}

			GroupPickingJobExport pickingJobExport = new GroupPickingJobExport(pickingJob, pickingJob.getPickingGroup().getName(), pickingJob.getPickingGroup().getDescription(), exportItems);
			pickingJobExports.add(pickingJobExport);
		}
		
		try {
			createPdfForMissing( pickingJobGroup, pickingJobExports, pickingJobGroup.getPickingSet(), warehouseEmployee);	
		} catch ( DocumentException | IOException e) {
			throw new Exception("Failed to create pdf file!");
		}
	}
	
	public PickingJobGroup createPickingJob(Integer numberOfProcess, Map<String, PickingGroup> pickingGroupTable, String includingOrders, Integer amzhs, Integer amzeb, WarehouseEmployee regBy) throws Exception {		
		Map<String, List<OrderItem>> orders = new HashMap<>();
		
		Integer pickingSet = pickingJobGroupRepository.getMaxPickingSet();
		if(pickingSet == null) {
			pickingSet = 1;
		} else {
			pickingSet += 1;
		}
		List<OrderItem> orderItems = orderInfoHelper.getOrderItemList(numberOfProcess, pickingSet, includingOrders, amzhs, amzeb);
		
		Map<Long, PickingJob> pickingJobMap = new LinkedHashMap<>();
		Map<Long, GroupPickingJobExport> pickingJobExportMap = new LinkedHashMap<>();
		Map<String, List<WarehouseLevel>> levelMap = new HashMap<>();

		PickingJobGroup pickingJobGroup = PickingJobGroup.builder()
				.setPickingSet(pickingSet)
				.setGroupStatus(PickingJobGroup.PickingJobGroupStatus.NOT_FINISHED)
				.setTotalItems(orderItems.size())
				.setRegBy(regBy)
				.setRegDate(new Date())
				.createPickingJobGroup();
		pickingJobGroupRepository.save(pickingJobGroup);
		
		Integer numberOfHairs = 0;
		Integer numberOfGMs = 0;
		
		for(OrderItem item : orderItems) {
			// Put unique order nos
			if(!orders.containsKey(item.getOrderNo())) {
				ArrayList<OrderItem> itemList = new ArrayList<>();
				itemList.add(item);
				orders.put(item.getOrderNo(), itemList);
			} else {
				List<OrderItem> items = orders.get(item.getOrderNo());
				items.add(item);
			}
			
			if(item.getProductType().equals(OrderItem.ProductType.HAIR)) {
				numberOfHairs ++;
			} else {
				numberOfGMs ++;
			}
			

			// Get Locations
			List<WarehouseLevel> levels = null;
			if(levelMap.containsKey(item.getGeneratedBarcode())) {
				levels = levelMap.get(item.getGeneratedBarcode());
			} else {
				levels = warehouseLevelService.getAllByGeneratedBarcodeAndType(item.getGeneratedBarcode(), WarehouseLevel.LevelType.PICKING);	
			}
			List<String> locationNames = new ArrayList<>();
			for(WarehouseLevel level : levels) {
				locationNames.add(level.getLocationName());
			}
			
			item.setLocationNames(locationNames);
			
			//#5 Comment it to allow duplication of order
			
			// check if this item already exists or not
//#5			if(!pickingItemInfoRepository.existsByGeneratedBarcodeAndOrderNo(item.getGeneratedBarcode(), item.getOrderNo())) {

			String searchKey = "";
			if(item.getHost().equals(OrderInfoForPicking.HOST.AMAZON_EBEUATY)) {
				searchKey = "AMZEB";
			} else if(!item.getProductType().equals(com.samsbeauty.old.model.OrderItem.ProductType.HAIR)) {
				searchKey = "GM";
			} else {
				searchKey = item.getCompany();
			}
			PickingGroup pickingGroup = pickingGroupTable.get(searchKey);
			if(!pickingJobMap.containsKey(pickingGroup.getPickingGroupId())) {
				// if picking job has not been created, create a new one.
				PickingJob pickingJob = PickingJob.builder(pickingJobGroup, pickingGroup)
						.setPickingStatus(PickingJob.PickingJobStatus.READY)
						.setSaved(Boolean.FALSE)
						.createPickingJob();

				pickingJobRepository.save(pickingJob);
				
				
				// Make a jobexport bean to generated paper
				Map<String, GroupPickingItemExport> exportItems = new LinkedHashMap<>();
				GroupPickingItemExport itemExport = GroupPickingItemExport.builder()
						.setDescription(item.getTitle())
						.setGeneratedBarcode(item.getGeneratedBarcode())
						.setLocations(locationNames)
						.setOption(item.getFirstOption())
						.setStock(item.getStock())
						.setProductType(item.getProductType())
						.setQuantity(item.getQuantity())
						.createOrderItemExport();
				exportItems.put(itemExport.getGeneratedBarcode(), itemExport);
				GroupPickingJobExport pickingJobExport = new GroupPickingJobExport(pickingJob, pickingGroup.getName(), pickingGroup.getDescription(), exportItems);
				// End Making the jobExport bean
				
				pickingJobExportMap.put(pickingGroup.getPickingGroupId(), pickingJobExport);		
				pickingJobMap.put(pickingGroup.getPickingGroupId(), pickingJob);
				
				pickingJobGroup.getPickingJobs().add(pickingJob);
				// Create picking item
				
				PickingItemInfo pickingItemInfo = PickingItemInfo.builder(pickingJob)
						.setGeneratedBarcode(item.getGeneratedBarcode())
						.setImageUrl(item.getImageUrl())
						.setProductGroup(item.getProductGroup())
						.setOrderNo(item.getOrderNo())
						.setOrderQuantity(item.getQuantity())
						.setPickingJob(pickingJob)
						.setProductBarcode(item.getProductBarcode())
						.setTitle(item.getTitle())
						.createPickingItemInfo();
				
				pickingItemInfoRepository.save(pickingItemInfo);
				
				
			} else {
				// Update export date
				GroupPickingJobExport exportJob = pickingJobExportMap.get(pickingGroup.getPickingGroupId());
				if(exportJob.getItemMap().containsKey(item.getGeneratedBarcode())) {
					GroupPickingItemExport itemExport = exportJob.getItemMap().get(item.getGeneratedBarcode());
					itemExport.addQuantity(item.getQuantity());
				} else {
					GroupPickingItemExport itemExport = GroupPickingItemExport.builder()
							.setDescription(item.getTitle())
							.setGeneratedBarcode(item.getGeneratedBarcode())
							.setLocations(locationNames)
							.setOption(item.getFirstOption())
							.setStock(item.getStock())
							.setProductType(item.getProductType())
							.setQuantity(item.getQuantity())
							.createOrderItemExport();
					exportJob.getItemMap().put(item.getGeneratedBarcode(), itemExport);	
				}
				// done for updating
				
				PickingJob pickingJob = pickingJobMap.get(pickingGroup.getPickingGroupId());
				PickingItemInfo pickingItemInfo = PickingItemInfo.builder(pickingJob)
						.setGeneratedBarcode(item.getGeneratedBarcode())
						.setImageUrl(item.getImageUrl())
						.setProductGroup(item.getProductGroup())
						.setOrderNo(item.getOrderNo())
						.setOrderQuantity(item.getQuantity())
						.setPickingJob(pickingJob)
						.setProductBarcode(item.getProductBarcode())
						.setTitle(item.getTitle())
						.createPickingItemInfo();
				
				pickingItemInfoRepository.save(pickingItemInfo);
			}
//#5			}
		}
		// Sorting by warehouse level
		for(GroupPickingJobExport export : pickingJobExportMap.values()) {
			List<GroupPickingItemExport> list = new ArrayList<>();
			list.addAll(export.getItemMap().values());
			list = PickingSorting.pickingItemSortByLevel(list);			
			export.setItemList(list);
		}
		
		
		// create pdf file		
		try {
			createPdfForPicking(pickingJobExportMap, pickingSet, regBy);	
		} catch ( DocumentException | IOException e) {
			throw new Exception("Failed to create pdf file!");
		}
		
		
		// create packing list for all groups
		StringBuffer sb = new StringBuffer();
		orders.keySet().forEach(orderNo -> {
			if(!sb.toString().equals("")) {
				sb.append(",");
			}
			sb.append(orderNo);			
		});
		
		List<OrderInfoForPicking> orderInfos = orderInfoHelper.getOrderInfoForPicking(sb.toString(), pickingSet);
		
		List<OrderInfoForPicking> amazonHairOrders = new ArrayList<>();
		List<OrderInfoForPicking> amazonGmOrders = new ArrayList<>();
		List<OrderInfoForPicking> samsOrders = new ArrayList<>();
		
		// Separate lists by host
		orderInfos.forEach(oi -> {
			switch(oi.getHost()) {
			case OrderInfoForPicking.HOST.SAMSBEAUTY:
				samsOrders.add(oi);
				break;
			
			case OrderInfoForPicking.HOST.AMAZON_EBEUATY:
				amazonGmOrders.add(oi);
				break;
				
			case OrderInfoForPicking.HOST.AMAZON_HAIRSTYLE21:
				amazonHairOrders.add(oi);
				break;				
			}
		});
		
		sortOrderForPacking(samsOrders, orders);
		sortOrderForPacking(amazonGmOrders, orders);
		sortOrderForPacking(amazonHairOrders, orders);
		
		List<OrderInfoForPicking> all = new ArrayList<>();
		all.addAll(samsOrders);
		all.addAll(amazonGmOrders);
		all.addAll(amazonHairOrders);
		
		// Sort orders 
		
		try {
			createPdfForPacking(all, pickingJobGroup, regBy);
		} catch ( DocumentException | IOException e) {
			throw new Exception("Failed to create pdf file!");
		}
		
		return pickingJobGroup;
	}
	public List<OrderInfoForPicking> sortOrderForPacking(List<OrderInfoForPicking> orderInfos, Map<String, List<OrderItem>> orders) {

		List<OrderInfoForPicking> urgentShipList = new ArrayList<>();
		List<OrderInfoForPicking> urgentILShipList = new ArrayList<>();
		List<OrderInfoForPicking> containingGM = new ArrayList<>();
		Comparator<OrderInfoForPicking> pcompare = new PackingOrderComparator();
		PriorityQueue<OrderInfoForPicking> oneDiffItemPQ = new PriorityQueue<>(10, pcompare);
		List<OrderInfoForPicking> twoDiffItems = new ArrayList<>();
		List<OrderInfoForPicking> threeDiffItems = new ArrayList<>();
		List<OrderInfoForPicking> manyDiffItems = new ArrayList<>();
		
		
		for(OrderInfoForPicking o : orderInfos) {
			o.setOrderItems(orders.get(o.getOrderNo()));
			
			if(UrgentShippingChecking.checkUrgentShipping(o.getShippingMethodName())) {
				urgentShipList.add(o);
			} else if(o.getShippingState() != null && o.getShippingState().equals("IL")) {
				urgentILShipList.add(o);
			} else {
				boolean containGM = false;
				for(OrderItem oi : o.getOrderItems()) {
					if( oi.getProductType().equals(ProductType.GM) &&
						!(
							oi.getProductGroup().equals("20751079") 
							||
							oi.getProductGroup().equals("ANN440")
							||
							oi.getProductGroup().equals("RE2225")
						) ){
						containGM = true;
						break;
					}
				}
				if(containGM) {
					containingGM.add(o);
				} else {
					if(o.getOrderItems().size() == 1) {
						oneDiffItemPQ.add(o);
					} else if(o.getOrderItems().size() == 2) {
						twoDiffItems.add(o);
					} else if(o.getOrderItems().size() == 3) {
						threeDiffItems.add(o);
					} else {
						manyDiffItems.add(o);
					}
				}
				
			}
		}
		// merge all list
		List<OrderInfoForPicking> mergedList = new ArrayList<>();
		mergedList.addAll(urgentShipList);
		mergedList.addAll(urgentILShipList);
		mergedList.addAll(containingGM);
		while(oneDiffItemPQ.size() > 0) {
			mergedList.add(oneDiffItemPQ.poll());
		}
		mergedList.addAll(twoDiffItems);
		mergedList.addAll(threeDiffItems);
		mergedList.addAll(manyDiffItems);
		return mergedList;
	}
	
	public void createPdfForPacking(List<OrderInfoForPicking> orderInfos, PickingJobGroup pickingJobGroup, WarehouseEmployee regBy) throws MalformedURLException, DocumentException, IOException {
		// create pdf
		PackingListExporter exporter = new PackingListExporter();
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");		
		String today = format.format(new Date());
		String folder = "Packing" + today;
		String path = groupPickingListDir + "/" +  folder + "/";
		File directory = new File(path);
		if(!directory.exists()) {
			directory.mkdir();
		}
		String filename = "packageList_" + pickingJobGroup.getPickingSet() + ".pdf";
		String filePath = path + filename;
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}		
		exporter.createPackingList(filePath, regBy, orderInfos);
		file = new File(filePath);
		// save file data
		PackingExport pe = PackingExport.builder().setFilename(filename)
				.setFilesize(file.length())
				.setFolder(folder)
				.setPickingGroup(pickingJobGroup)
				.setRegBy(regBy)
				.setRegDate(new Date())
				.createPackingExport();
		
		packingExportService.save(pe);
	}
	private void createPdfForMissing(PickingJobGroup pickingJobGroup, List<GroupPickingJobExport> pickingJobExports, Integer pickingSet, WarehouseEmployee regBy) throws DocumentException, IOException {
		PickingReportExporter exporter = new PickingReportExporter();		
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");		
		String today = format.format(new Date());
		String folder = "Missing" + today;
		String path = groupPickingListDir + "/" +  folder + "/";
		File directory = new File(path);
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		for(GroupPickingJobExport pj : pickingJobExports) {	
			if(pj.getItemList() != null && pj.getItemList().size() > 0) {
				String filename = "Missing_" + pickingSet.toString() + "_" + pj.getGroupName().replaceAll(" ", "").toLowerCase() + ".pdf";
				String uploadPath = path + filename;
				exporter.saveGroupPickingList(uploadPath, regBy, pickingSet, pj);	
				
				File file = new File(uploadPath);

				// save file data
				MissingExport missingExport = MissingExport.builder()
						.setPickingJob(pj.getPickingJob())
						.setFilename(filename)
						.setFilesize(file.length())
						.setRegBy(regBy)
						.setFolder(folder)
						.setRegDate(new Date())
						.createMissingExport();
				missingExportService.save(missingExport);
			}
		}
	}
	public void createPdfForPicking(Map<Long, GroupPickingJobExport> pickingJobExportMap, Integer pickingSet, WarehouseEmployee regBy) throws DocumentException, IOException {
		PickingReportExporter exporter = new PickingReportExporter();		
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");		
		String today = format.format(new Date());
		String folder = "GroupPicking" + today;
		String path = groupPickingListDir + "/" +  folder + "/";
		File directory = new File(path);
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		for(GroupPickingJobExport pj : pickingJobExportMap.values()) {	
			
			String filename = "GroupPicking" + pickingSet.toString() + "_" + pj.getGroupName().replaceAll(" ", "").toLowerCase() + ".pdf";
			String uploadPath = path + filename;
			exporter.saveGroupPickingList(uploadPath, regBy, pickingSet, pj);	
			
			File file = new File(uploadPath);

			// save file data
			PickingJobExport pickingJobExport = PickingJobExport.builder(pj.getPickingJob())
					.setFilename(filename)
					.setFilesize(file.length())
					.setRegBy(regBy)
					.setFolder(folder)
					.setRegDate(new Date())
					.createPickingJobExport();
			
			pickingJobExportService.save(pickingJobExport);
			
		}
	}
	public List<PickingJob> getAssignedJobs(Long warehouseEmployeeId) {
		// Set picking Sets
		List<PickingJob> pickingJobs = pickingJobRepository.findAssignedByWarehouseEmployeeId(warehouseEmployeeId);
		for(PickingJob job : pickingJobs) {
			PickingJobGroup group = pickingJobGroupRepository.getOneByPickingJobId(job.getPickingJobId());
			job.setPickingSet(group.getPickingSet());
		}
		return pickingJobs;
	}
	public void delete(Long pickingJobId) {
		pickingJobRepository.delete(pickingJobId);
	}
}