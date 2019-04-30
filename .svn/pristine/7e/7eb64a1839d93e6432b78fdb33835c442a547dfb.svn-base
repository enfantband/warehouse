package com.samsbeauty.warehouse.woonseok.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;
import com.samsbeauty.warehouse.woonseok.model.Woonseok;

public class WoonseokSpecification {
	public static Specification<Woonseok> activated(final Boolean activated) {
		return (root, query, cb) -> {
			return cb.equal(root.<Boolean>get("activated"), activated);
		};
	}
	
	public static Specification<Woonseok> reason(final String reason) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("reason"), reason);
		};
	}
	
	public static Specification<Woonseok> status(final String status) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("status"), status);
		};
	}
}
