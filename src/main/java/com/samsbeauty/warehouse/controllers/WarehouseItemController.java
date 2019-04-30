package com.samsbeauty.warehouse.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.ExistingWarehouseItemException;
import com.samsbeauty.warehouse.exception.rest.InvalidProductBarcodeException;
import com.samsbeauty.warehouse.exception.rest.LocationNotFoundException;
import com.samsbeauty.warehouse.exception.rest.TooManyResultsException;
import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.param.WarehouseItemParam;
import com.samsbeauty.warehouse.service.WarehouseItemBoxService;
import com.samsbeauty.warehouse.service.WarehouseItemService;
import com.samsbeauty.warehouse.service.WarehouseLevelService;
import com.samsbeauty.warehouse.util.BarcodeUtil;

@RestController
@RequestMapping("/api/warehouse/item")
public class WarehouseItemController {

	@Autowired private WarehouseItemService warehouseItemService;
	@Autowired private WarehouseLevelService warehouseLevelService;
	@Autowired private WarehouseEmployeeService warehouseEmployeeService;	
	@Autowired private InventoryRestHelper inventoryRestHelper;
	@Autowired private WarehouseItemBoxService warehouseItemBoxService;
	
	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;
	
	// Retrieve All Resources using paging
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<Page<WarehouseItem>> getAll(
					@RequestParam(name="pageSize", required=false) Integer pageSize,
					@RequestParam(name="page", required=false) Integer page,
					@RequestParam(name="search", required=false) String search,
					@RequestParam(name="sortingDirections", required=false) String sortingDirection,
					@RequestParam(name="sortingFields", required=false) String sortingField,
					@RequestParam(name="levelId", required=false) Long levelId,
					@RequestParam(name="itemBoxId", required=false) Integer itemBoxId) throws WrongParameterException, TooManyResultsException {
		if(pageSize == null){
			pageSize = DEFAULT_NUM_PER_PAGE;
		}
		if(page == null) {
			page = 1;
		}
		Sort sort = null;
		if(sortingDirection != null && sortingField != null) {
			String[] directions = sortingDirection.split(" ");
			String[] sortingFields = sortingField.split(" ");
			if(directions.length > 0) {
				if(directions.length != sortingFields.length) {
					throw new WrongParameterException("Number of sorting field and direction are different.");
				}
				sort = new Sort(Sort.Direction.fromString(directions[0]), sortingFields[0]);
				if(directions.length > 1) {
					for(int i=1; i<directions.length; i++) {
						sort = sort.and(new Sort(Sort.Direction.fromString(directions[i]), sortingFields[i]));
					}
				}
			}
		} else {
			sort = new Sort(Sort.Direction.DESC, "itemId");
		}
		if(!StringUtils.isEmpty(search) && search.length() < 4) {
			throw new WrongParameterException ("The keyword for searching is too short. It should be more than 4 characters");
		}
		WarehouseItemParam param = WarehouseItemParam.builder()
				.levelId(levelId)
				.itemBoxId(itemBoxId)
				.searchKey(search)
				.createWarehouseItemParam();
		
		Page<WarehouseItem> warehouseItemList = warehouseItemService.getAllByParam(param, page, pageSize, sort);
		 
		
		return new ResponseEntity<Page<WarehouseItem>>(warehouseItemList, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/inbound/{itemId}")
	WarehouseItem inbound(@PathVariable Integer itemId, @RequestParam(name="quantity", required=true) Integer quantity, HttpServletRequest request) {

		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		
		WarehouseItem warehouseItem = warehouseItemService.getOne(itemId);
		warehouseItemService.inbound(warehouseItem, quantity, regBy);
		return warehouseItem;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/outbound/{itemId}")
	WarehouseItem outbound(@PathVariable Integer itemId, @RequestParam(name="quantity", required=true) Integer quantity, HttpServletRequest request) {
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		WarehouseItem warehouseItem = warehouseItemService.getOne(itemId);
		warehouseItemService.outbound(warehouseItem, quantity, modBy);
		return warehouseItem;
	}


	// Retrieve All List at the location
	@RequestMapping(method=RequestMethod.GET, value="/location/barcode/{locationBarcode}")
	List<WarehouseItem> getAllByLocationBarcode(@PathVariable String locationBarcode) throws LocationNotFoundException {
		WarehouseLevel warehouseLevel = warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));
		return warehouseItemService.getAllByLevelId(warehouseLevel.getLevelId());
	}
	
	// Retrieve all list in the box at the location
	@RequestMapping(method=RequestMethod.GET, value="/location/barcode/{locationBarcode}/box/barcode/{boxBarcode}")
	List<WarehouseItem> getAllByLocationBarcodeAndBoxBarcode(@PathVariable String locationBarcode, @PathVariable String boxBarcode) throws LocationNotFoundException {
		WarehouseLevel warehouseLevel = warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));
		WarehouseItemBox warehouseItemBox = warehouseItemBoxService.getOneByBoxPrefixAndBoxCodeAndLevelId(BarcodeUtil.getBoxCode(boxBarcode), warehouseLevel.getLevelId());
		return warehouseItemService.getAllByItemBoxId(warehouseItemBox.getItemBoxId());
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/toLocation/{toLocation}")
	void transfer(@PathVariable final String toLocation, @RequestBody final List<WarehouseItem> warehouseItems, HttpServletRequest request) throws LocationNotFoundException {

		WarehouseLevel wl = warehouseLevelService.getOneByLocationBarcode(toLocation);
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		
		for(WarehouseItem wi : warehouseItems) {
			WarehouseItem warehouseItem = warehouseItemService.getOne(wi.getItemId());			
			warehouseItemService.transfer(warehouseItem, wl, modBy);
		}		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/toLocation/{toLocation}/toBox/{toBox}")
	void transferWithBox(@PathVariable final String toLocation, @PathVariable final String toBox, @RequestBody final List<WarehouseItem> warehouseItems, HttpServletRequest request) throws LocationNotFoundException {
		
		WarehouseLevel wl = warehouseLevelService.getOneByLocationBarcode(toLocation);
		WarehouseItemBox wib = warehouseItemBoxService.getOneByBoxPrefixAndBoxCodeAndLevelId(BarcodeUtil.getBoxCode(toBox), wl.getLevelId());
		
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee modBy = warehouseEmployeeService.getOneByGid(gid);
		
		for(WarehouseItem wi : warehouseItems) {
			WarehouseItem warehouseItem = warehouseItemService.getOne(wi.getItemId());
			
			warehouseItemService.transferWithBox(warehouseItem, wl, wib, modBy);
		}
	}
	
	// Bulk Item Input
	@RequestMapping(method=RequestMethod.POST, value="/location/barcode/{locationBarcode}")
	List<WarehouseItem> addItems(@PathVariable final String locationBarcode, @RequestBody @Valid final List<WarehouseItem> warehouseItems, HttpServletRequest request) throws LocationNotFoundException {

		WarehouseLevel warehouseLevel = warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));

		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		for(WarehouseItem warehouseItem : warehouseItems) {
			if(!warehouseItemService.exists(warehouseItem.getGeneratedBarcode(), warehouseLevel.getLevelId())) {
				WarehouseItem newWarehouseItem = WarehouseItem.builder()
						.setGeneratedBarcode(warehouseItem.getGeneratedBarcode())
						.setRegBy(regBy)
						.setRegDate(new Date())
						.setModBy(regBy)
						.setModDate(new Date())
						.setProductId(warehouseItem.getProductId())
						.setQuantity(warehouseItem.getQuantity())
						.setWarehouseLevel(warehouseLevel)
						.createWarehouseItem();
				warehouseItemService.save(newWarehouseItem);
			}			
		}
		return warehouseItems;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/location/barcode/{locationBarcode}/box/barcode/{boxBarcode}")
	List<WarehouseItem> addItemsInBox(@PathVariable final String locationBarcode, @PathVariable final String boxBarcode, @RequestBody @Valid final List<WarehouseItem> warehouseItems, HttpServletRequest request) throws LocationNotFoundException {
		WarehouseLevel warehouseLevel = warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));
		WarehouseItemBox warehouseItemBox = warehouseItemBoxService.getOneByBoxPrefixAndBoxCodeAndLevelId(BarcodeUtil.getBoxCode(boxBarcode), warehouseLevel.getLevelId());
		if(warehouseItemBox == null) {
			throw new LocationNotFoundException("Cannot find the box in the location");
		}
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		
		for(WarehouseItem warehouseItem : warehouseItems) {
			if(!warehouseItemService.exists(warehouseItem.getGeneratedBarcode(), warehouseLevel.getLevelId(), warehouseItemBox.getItemBoxId())) {
				WarehouseItem newWarehouseItem = WarehouseItem.builder()
						.setGeneratedBarcode(warehouseItem.getGeneratedBarcode())
						.setRegBy(regBy)
						.setRegDate(new Date())
						.setModBy(regBy)
						.setModDate(new Date())
						.setProductId(warehouseItem.getProductId())
						.setQuantity(warehouseItem.getQuantity())
						.setWarehouseLevel(warehouseLevel)
						.setWarehouseItemBox(warehouseItemBox)
						.createWarehouseItem();
				warehouseItemService.save(newWarehouseItem);
			}
		}
		return warehouseItems;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/barcode/{barcode}")
	WarehouseItem addByBarcode(
		@PathVariable final String barcode,
		@RequestParam(name="locationBarcode", required=true) final String locationBarcode,
		@RequestParam(name="quantity", required=true) final Integer quantity,
		HttpServletRequest request
			) throws InvalidProductBarcodeException, NumberFormatException, LocationNotFoundException, ExistingWarehouseItemException {

		List<Inventory> inventoryList = inventoryRestHelper.getInventoryList(Arrays.asList(barcode));
		if(inventoryList == null || inventoryList.size() == 0) {
			throw new InvalidProductBarcodeException("Cannot find a product with the barcode you provided");
		}
		Inventory inventory = inventoryList.get(0);
		
		WarehouseLevel warehouseLevel = warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode));
		
		if(warehouseItemService.exists(inventory.getGeneratedBarcode(), warehouseLevel.getLevelId())) {
			throw new ExistingWarehouseItemException("The item already exsits in the location. To adjust the quantity, use +/- button.");
		}
		
		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee regBy = warehouseEmployeeService.getOneByGid(gid);
		
		WarehouseItem newWarehouseItem = WarehouseItem.builder()
				.setGeneratedBarcode(inventory.getGeneratedBarcode())
				.setRegBy(regBy)
				.setRegDate(new Date())
				.setModBy(regBy)
				.setModDate(new Date())
				.setProductId(Long.valueOf(inventory.getProductId()))
				.setQuantity(quantity)
				.setWarehouseLevel(warehouseLevelService.getOneByLocationBarcode(BarcodeUtil.getLocationCode(locationBarcode)))
				.createWarehouseItem();
		return warehouseItemService.save(newWarehouseItem);
	}

	@RequestMapping(method=RequestMethod.PUT)
	WarehouseItem update(@RequestBody @Valid final WarehouseItem warehouseItem) {
		WarehouseItem updateWarehouseItem = warehouseItemService.getOne(warehouseItem.getItemId());
		//Implement here
		return updateWarehouseItem;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{itemId}")
	WarehouseItem delete(@PathVariable Integer itemId, HttpServletRequest request) {
		WarehouseItem deleteWarehouseItem = warehouseItemService.getOne(itemId);

		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee delBy = warehouseEmployeeService.getOneByGid(gid);
		
		deleteWarehouseItem.delete(delBy, new Date());
		return  warehouseItemService.save(deleteWarehouseItem);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{itemIds}")
	ResponseEntity<List<WarehouseItem>> deleteByIds(@PathVariable String itemIds, HttpServletRequest request) {
		List<String> ids = Arrays.asList(itemIds.split(","));
		List<WarehouseItem> deletedItems = new ArrayList<>();

		String gid = (String) request.getAttribute("GID");
		WarehouseEmployee delBy = warehouseEmployeeService.getOneByGid(gid);
		
		for(String id : ids) {
			Integer itemId = Integer.valueOf(id);
			WarehouseItem deletedItem = warehouseItemService.getOne(itemId);
			deletedItems.add(deletedItem);
			deletedItem.delete(delBy, new Date());
			warehouseItemService.save(deletedItem);
		}
		return new ResponseEntity<List<WarehouseItem>> (deletedItems, HttpStatus.OK);
	}

}