package com.samsbeauty.warehouse.picking.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.picking.model.PickingJobGroup;

public class PickingJobGroupSpecifications {
	public static Specification<PickingJobGroup> activatedPickingJobGroup(final Boolean activated) {
		return (root, query, cb) -> {
			return cb.equal(root.<Boolean>get("activated"), activated);
		};
	}
	public static Specification<PickingJobGroup> searchByDate(final Date searchDateFrom, final Date searchDateTo) {
		return (root, query, cb) -> {
			return cb.and(cb.greaterThanOrEqualTo(root.<Date>get("regDate"), searchDateFrom), cb.lessThanOrEqualTo(root.<Date>get("regDate"), searchDateTo));
		};
	}
}
