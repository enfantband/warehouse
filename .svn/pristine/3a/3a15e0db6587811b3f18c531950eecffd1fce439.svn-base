package com.samsbeauty.warehouse.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.WarehouseGroup;
import com.samsbeauty.warehouse.model.WarehouseSubgroup;

public class WarehouseSubgroupSpecifications {
	public static Specification<WarehouseSubgroup> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
	public static Specification<WarehouseSubgroup> groupId(final Long groupId) {
		return (root, query, cb) -> {
			return cb.equal(root.<WarehouseGroup>get("warehouseGroup").<Long>get("groupId"), groupId);
		};
	}
	public static Specification<WarehouseSubgroup> groupCode(final String groupCode) {
		return (root, query, cb) -> {
			return cb.equal(root.<WarehouseGroup>get("warehouseGroup").<Long>get("groupCode"), groupCode);
		};
	}
}
