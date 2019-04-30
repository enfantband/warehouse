package com.samsbeauty.warehouse.picking.report.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;

public class PickingReportSpecifications {
	public static Specification<PickingJobTimelineReport> activated(final Boolean activated) {
		return (root, query, cb) -> {
			return cb.equal(root.<Boolean>get("activated"), activated);
		};
	}
	public static Specification<PickingJobTimelineReport> picker(WarehouseEmployee picker) {
		return (root, query, cb) -> {
			return cb.equal(root.<WarehouseEmployee>get("warehouseEmployee").<Long>get("warehouseEmployeeId"), picker.getWarehouseEmployeeId());
		};
	}
	public static Specification<PickingJobTimelineReport> startDate(Date startDate) {
		return (root, query, cb) -> {
			return cb.greaterThanOrEqualTo(root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), startDate);
		};
	}
	public static Specification<PickingJobTimelineReport> endDate(Date endDate) {
		return (root, query, cb) -> {
			return cb.lessThanOrEqualTo(root.<PickingJobTimeline>get("endTimeline").<Date>get("eventTime"), endDate);
		};
	}
}
