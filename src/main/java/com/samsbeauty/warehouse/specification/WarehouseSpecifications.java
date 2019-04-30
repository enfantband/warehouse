package com.samsbeauty.warehouse.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.Warehouse;

public class WarehouseSpecifications {
	public static Specification<Warehouse> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
}
