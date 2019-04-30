package com.samsbeauty.warehouse.specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.WarehouseAisle;
import com.samsbeauty.warehouse.model.WarehouseGroup;

public class WarehouseGroupSpecifications {
	public static Specification<WarehouseGroup> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
	public static Specification<WarehouseGroup> aisleId(final Long aisleId) {
		return (root, query, cb) -> {
			final Subquery<Long> aisleQuery = query.subquery(Long.class);
			final Root<WarehouseAisle> warehouseAisle = aisleQuery.from(WarehouseAisle.class);
			final Join<WarehouseAisle, WarehouseGroup> warehouseGroups = warehouseAisle.join("warehouseGroups");
			aisleQuery.select(warehouseGroups.<Long> get("groupId"));
			aisleQuery.where(cb.equal(warehouseAisle.<Long> get("aisleId"), aisleId));
			
			return cb.in(root.get("groupId")).value(aisleQuery);
		};
	}
	public static Specification<WarehouseGroup> aisleCode(final String aisleCode) {
		return (root, query, cb) -> {
			final Subquery<Long> aisleQuery = query.subquery(Long.class);
			final Root<WarehouseAisle> warehouseAisle = aisleQuery.from(WarehouseAisle.class);
			final Join<WarehouseAisle, WarehouseGroup> warehouseGroups = warehouseAisle.join("warehouseGroups");
			aisleQuery.select(warehouseGroups.<Long> get("groupId"));
			aisleQuery.where(cb.equal(warehouseAisle.<Long> get("aisleCode"), aisleCode));
			
			return cb.in(root.get("groupId")).value(aisleQuery);
		};
	}
}
