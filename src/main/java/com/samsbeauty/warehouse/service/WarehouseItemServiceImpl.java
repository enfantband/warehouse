package com.samsbeauty.warehouse.service;

import static com.samsbeauty.warehouse.specification.WarehouseItemSpecifications.activated;
import static com.samsbeauty.warehouse.specification.WarehouseItemSpecifications.generatedBarcode;
import static com.samsbeauty.warehouse.specification.WarehouseItemSpecifications.generatedBarcodes;
import static com.samsbeauty.warehouse.specification.WarehouseItemSpecifications.itemBoxId;
import static com.samsbeauty.warehouse.specification.WarehouseItemSpecifications.levelId;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.exception.rest.TooManyResultsException;
import com.samsbeauty.warehouse.mapper.WarehouseItemInventoryMapper;
import com.samsbeauty.warehouse.model.WarehouseInbound;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.model.WarehouseOutbound;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.param.WarehouseItemParam;
import com.samsbeauty.warehouse.repository.WarehouseInboundRepository;
import com.samsbeauty.warehouse.repository.WarehouseItemRepository;
import com.samsbeauty.warehouse.repository.WarehouseOutboundRepository;
@Service
@Transactional
public class WarehouseItemServiceImpl implements WarehouseItemService {
	@Autowired private WarehouseItemRepository warehouseItemRepository;	
	@Autowired private WarehouseItemInventoryMapper warehouseItemInventoryMapper;
	@Autowired private WarehouseInboundRepository warehouseInboundRepository;
	@Autowired private WarehouseOutboundRepository warehouseOutboundRepository;
	@Autowired private InventoryRestHelper inventoryRestHelper;

	public Page<WarehouseItem> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		
		Page<WarehouseItem> warehouseItemList = warehouseItemRepository.findAll(where(activated()), new PageRequest(pageNumber-1, pageSize, sort));
		warehouseItemInventoryMapper.putInventoryData(warehouseItemList.getContent());
		return warehouseItemList;
	}
	public Page<WarehouseItem> getAllByLevelId(Long levelId, Integer pageNumber, Integer pageSize, Sort sort) {
		Page<WarehouseItem> warehouseItemList = warehouseItemRepository.findAll(where(activated()).and(levelId(levelId)), new PageRequest(pageNumber-1, pageSize, sort));
		warehouseItemInventoryMapper.putInventoryData(warehouseItemList.getContent());
		return warehouseItemList;
	}
	public List<WarehouseItem> getAllByLevelId(Long levelId) {
		List<WarehouseItem> warehouseItemList = warehouseItemRepository.findAll(where(activated()).and(levelId(levelId)));
		warehouseItemInventoryMapper.putInventoryData(warehouseItemList);
		return warehouseItemList;
	}
	public List<WarehouseItem> getAllByItemBoxId(Integer itemBoxId) {
		List<WarehouseItem> warehouseItemList = warehouseItemRepository.findAll(where(activated()).and(itemBoxId(itemBoxId)));
		warehouseItemInventoryMapper.putInventoryData(warehouseItemList);
		return warehouseItemList;
	}
	public Page<WarehouseItem> getAllByParam(WarehouseItemParam param, Integer pageNumber, Integer pageSize, Sort sort) throws TooManyResultsException {
		Specifications<WarehouseItem> sp = where(activated());
		if(!StringUtils.isEmpty(param.getSearchKey())){
			try {
				List<Inventory> inventories = inventoryRestHelper.getInventoryList(param.getSearchKey(), "0", Integer.MAX_VALUE, "");
				Set<String> generatedBarcodes = new HashSet<>();
				inventories.forEach(inventory -> generatedBarcodes.add(inventory.getGeneratedBarcode()));
				if(generatedBarcodes.size() == 0) {
					// if returned value does not exist then put just empty string;
					generatedBarcodes.add("");
				} else if(generatedBarcodes.size() > 1000) {
					throw new TooManyResultsException("Too many results for the search keyword");
				}

				sp = sp.and(generatedBarcodes(generatedBarcodes));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(param.getItemBoxId() != null) {
			sp = sp.and(itemBoxId(param.getItemBoxId()));
		}	
		if(param.getGeneratedBarcode() != null) {
			sp = sp.and(generatedBarcode(param.getGeneratedBarcode()));
		}
		if(param.getLevelId() != null) {
			sp = sp.and(levelId(param.getLevelId()));
		}
		Page<WarehouseItem> warehouseItemList = warehouseItemRepository.findAll(sp, new PageRequest(pageNumber-1, pageSize, sort));
		warehouseItemInventoryMapper.putInventoryData(warehouseItemList.getContent());
		return warehouseItemList;
	}
	public WarehouseItem getOne(Integer itemId) {
		WarehouseItem item = warehouseItemRepository.getOne(itemId);
		warehouseItemInventoryMapper.putInventoryData(item);
		return item;
	}
	public WarehouseItem getOneByGeneratedBarcode(String barcode){
		WarehouseItem item = warehouseItemRepository.getOneByGeneratedBarcode(barcode);
		warehouseItemInventoryMapper.putInventoryData(item);
		return item;
	}
	public WarehouseItem save(WarehouseItem warehouseItem) {
		WarehouseItem item = warehouseItemRepository.save(warehouseItem);
		if(warehouseItem.getQuantity() > 0) {
			// inbound if quantity > 0
			WarehouseInbound inbound = WarehouseInbound.builder(item)
					.setQuantity(warehouseItem.getQuantity())
					.setRegBy(warehouseItem.getRegBy())
					.setRegDate(new Date())
					.createWarehouseInbound();
			warehouseInboundRepository.save(inbound);
		} else if(warehouseItem.getQuantity() < 0) {
			// outbound if quantity < 0
			WarehouseOutbound outbound = WarehouseOutbound.builder()
					.setQuantity(Math.abs(warehouseItem.getQuantity()))
					.setRegBy(warehouseItem.getRegBy())
					.setRegDate(new Date())
					.createWarehouseOutbound();
			warehouseOutboundRepository.save(outbound);
		}
		
		return item;
	}
	public void delete(Integer itemId) {
		warehouseItemRepository.delete(itemId);
	}
	public void outbound(WarehouseItem warehouseItem, Integer quantity, WarehouseEmployee modBy) {
		// create outbound history
		WarehouseOutbound outbound = WarehouseOutbound.builder()
				.setQuantity(quantity)
				.setRegBy(modBy)
				.setRegDate(new Date())
				.setWarehouseItem(warehouseItem)
				.createWarehouseOutbound();
		warehouseOutboundRepository.save(outbound);
		warehouseItem.outbound(quantity);
		warehouseItemRepository.save(warehouseItem);
	}
	public void inbound(WarehouseItem warehouseItem, Integer quantity, WarehouseEmployee modBy) {
		WarehouseInbound wi = WarehouseInbound.builder(warehouseItem)
				.setQuantity(quantity)
				.setRegBy(modBy)
				.setRegDate(new Date())
				.createWarehouseInbound();
		warehouseInboundRepository.save(wi);
		warehouseItem.inbound(quantity);
		warehouseItemRepository.save(warehouseItem);
	}
	public boolean exists(String generatedBarcode, Long levelId) {
		return warehouseItemRepository.exists(generatedBarcode, levelId);
	}
	public boolean exists(String generatedBarocde, Long levelId, Integer itemBoxId) {
		return warehouseItemRepository.exists(generatedBarocde, levelId, itemBoxId);
	}

	public WarehouseItem transfer(WarehouseItem warehouseItem, WarehouseLevel toLevel, WarehouseEmployee modBy) {
		WarehouseItem newItem = WarehouseItem.builder()
				.setGeneratedBarcode(warehouseItem.getGeneratedBarcode())
				.setModBy(modBy)
				.setModDate(new Date())
				.setProductId(warehouseItem.getProductId())
				.setProductId(warehouseItem.getProductId())
				.setQuantity(warehouseItem.getQuantity())
				.setRegBy(warehouseItem.getRegBy())
				.setRegDate(warehouseItem.getRegDate())
				.setWarehouseLevel(toLevel)
				.createWarehouseItem();
		warehouseItem.delete(modBy, new Date());
		warehouseItemRepository.save(warehouseItem);
		return warehouseItemRepository.save(newItem);
	}
	public WarehouseItem transferWithBox(WarehouseItem warehouseItem, WarehouseLevel toLevel, WarehouseItemBox toBox, WarehouseEmployee modBy) {
		WarehouseItem newItem = WarehouseItem.builder()
				.setGeneratedBarcode(warehouseItem.getGeneratedBarcode())
				.setModBy(modBy)
				.setModDate(new Date())
				.setProductId(warehouseItem.getProductId())
				.setProductId(warehouseItem.getProductId())
				.setQuantity(warehouseItem.getQuantity())
				.setRegBy(warehouseItem.getRegBy())
				.setRegDate(warehouseItem.getRegDate())
				.setWarehouseLevel(toLevel)
				.setWarehouseItemBox(toBox)
				.createWarehouseItem();
		warehouseItem.delete(modBy, new Date());
		warehouseItemRepository.save(warehouseItem);
		return warehouseItemRepository.save(newItem);
	}
}