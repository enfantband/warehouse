package com.samsbeauty.warehouse.adjustment.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.adjustment.model.AdjustmentRequest;

public class AdjustmentRequestSpecifications {
	public static Specification<AdjustmentRequest> activated(final Boolean activated) {
		return (root, query, cb) -> {
			return cb.equal(root.<Boolean>get("activated"), activated);
		};
	}
	
	public static Specification<AdjustmentRequest> reason(final String reason) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("reason"), reason);
		};
	}
	
	public static Specification<AdjustmentRequest> status(final String status) {
		return (root, query, cb) -> {
			return cb.equal(root.<String>get("status"), status);
		};
	}
}
