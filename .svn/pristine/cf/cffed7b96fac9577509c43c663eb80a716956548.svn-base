package com.samsbeauty.warehouse.printer.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.printer.model.Printer;

public class PrinterSpecifications {
	public static Specification<Printer> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<Boolean>get("activated"), Boolean.TRUE);
		};
	}
}
