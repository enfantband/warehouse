package com.samsbeauty.warehouse.printer.specification;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.printer.model.PrinterLabel;

public class PrinterLabelSpecifications {

	public static Specification<PrinterLabel> activated() {
		return (root, query, cb) -> {
			return cb.equal(root.<Boolean>get("activated"), Boolean.TRUE);
		};
	}
}
