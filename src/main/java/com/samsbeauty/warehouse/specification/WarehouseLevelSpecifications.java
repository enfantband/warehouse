package com.samsbeauty.warehouse.specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.model.UseFlag;
import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.model.WarehouseAisle;
import com.samsbeauty.warehouse.model.WarehouseGroup;
import com.samsbeauty.warehouse.model.WarehouseLevel;
import com.samsbeauty.warehouse.model.WarehouseSubgroup;
import com.samsbeauty.warehouse.util.WarehouseLocationParser;

public class WarehouseLevelSpecifications {
	public static Specification<WarehouseLevel> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<UseFlag>get("useFlag"), UseFlag.Y);
		};
	}
	
	public static Specification<WarehouseLevel> locationCode(String locationCode) {
		return (root, query, cb) -> {
			WarehouseLocationParser parser = new WarehouseLocationParser(locationCode);
			String warehouseCode = parser.getWarehouseCode();
			String aisleCode = parser.getAisleCode();
			String groupCode = parser.getGroupCode();
			String subgroupCode = parser.getSubgroupCode();
			String levelCode = parser.getLevelCode();
			
			final Subquery<Long> warehouseLevelQuery = query.subquery(Long.class);
			final Root<WarehouseLevel> warehouseLevel = warehouseLevelQuery.from(WarehouseLevel.class);
			final Join<WarehouseLevel, WarehouseSubgroup> warehouseSubgroup = warehouseLevel.join("warehouseSubgroup");
			final Join<WarehouseSubgroup, WarehouseGroup> warehouseGroup = warehouseSubgroup.join("warehouseGroup");
			final Join<WarehouseGroup, WarehouseAisle> warehouseAisles = warehouseGroup.join("warehouseAisles");
			final Join<WarehouseAisle, Warehouse> warehouse = warehouseAisles.join("warehouse");
			warehouseLevelQuery.select(warehouseLevel.<Long>get("levelId"));
			warehouseLevelQuery.where(
					cb.equal(warehouse.<String> get("warehouseCode"), warehouseCode), 
					cb.equal(warehouseAisles.<String> get("aisleCode"), aisleCode),
					cb.equal(warehouseGroup.<String> get("groupCode"), groupCode),
					cb.equal(warehouseSubgroup.<String> get("subgroupCode"), subgroupCode),
					cb.equal(warehouseLevel.<String> get("levelCode"), levelCode));
			
			return cb.in(root.get("levelId")).value(warehouseLevelQuery);
		};
	}
}
