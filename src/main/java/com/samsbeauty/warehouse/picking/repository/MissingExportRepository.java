package com.samsbeauty.warehouse.picking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samsbeauty.warehouse.picking.model.MissingExport;
public interface MissingExportRepository extends JpaRepository<MissingExport, Long> {

}