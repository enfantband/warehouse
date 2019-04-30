package com.samsbeauty.warehouse.specification;

import java.util.Set;

import javax.persistence.criteria.Path;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.WarehouseItem;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;

public class WarehouseItemSpecifications {
	public static Specification<WarehouseItem> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
	
	public static Specification<WarehouseItem> levelId(Long levelId) {
		return (root, query, cb) -> {
			return cb.equal(root.<WarehouseLevel>get("warehouseLevel").<Long>get("levelId"), levelId); 
		};
	}
	
	public static Specification<WarehouseItem> itemBoxId(Integer itemBoxId) {
		return (root, query, cb) -> {
			return cb.equal(root.<WarehouseItemBox>get("warehouseItemBox").<Long>get("itemBoxId"), itemBoxId);
		};
	}
	
	public static Specification<WarehouseItem> generatedBarcode(String generatedBarcode) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("generatedBarcode"), generatedBarcode);
		};
	}
	
	public static Specification<WarehouseItem> generatedBarcodes(Set<String> generatedBarcodes) {
		return (root, query, cb) -> {
			final Path<String> barcodes = root.<String>get("generatedBarcode");
			return barcodes.in(generatedBarcodes);
		};
	}
}
