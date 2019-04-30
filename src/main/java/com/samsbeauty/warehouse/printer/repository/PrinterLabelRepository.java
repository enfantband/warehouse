package com.samsbeauty.warehouse.printer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.samsbeauty.warehouse.printer.model.PrinterLabel;
public interface PrinterLabelRepository extends JpaRepository<PrinterLabel, Long>, JpaSpecificationExecutor<PrinterLabel> {

}