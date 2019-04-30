package com.samsbeauty.warehouse.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;

public class WarehouseItemBoxSpecifications {
	public static Specification<WarehouseItemBox> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
	public static Specification<WarehouseItemBox> levelId(Long levelId) {
		return (root, query, cb) -> {
			return cb.equal(root.<WarehouseLevel>get("warehouseLevel").<Long>get("levelId"), levelId);
		};
	}
	public static Specification<WarehouseItemBox> prefix(String prefix) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("boxPrefix"), prefix);
		};
	}
	
	public static Specification<WarehouseItemBox> code(String code) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("boxCode"), code);
		};
	}
}
