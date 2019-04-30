package com.samsbeauty.warehouse.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.model.WarehouseAisle;

public class WarehouseAisleSpecifications {
	public static Specification<WarehouseAisle> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
	public static Specification<WarehouseAisle> warehouseId(Long warehouseId) {
		return (root, query, cb) -> {
			return cb.equal(root.<Warehouse>get("warehouse").<Long>get("warehouseId"), warehouseId);
		};
	}
	public static Specification<WarehouseAisle> warehouseCode(String warehouseCode) {
		return (root, query, cb) -> {
			return cb.equal(root.<Warehouse>get("warehouse").<Long>get("warehouseCode"), warehouseCode);
		};
	}
}
