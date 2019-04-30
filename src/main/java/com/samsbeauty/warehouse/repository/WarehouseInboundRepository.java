package com.samsbeauty.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samsbeauty.warehouse.model.WarehouseInbound;
public interface WarehouseInboundRepository extends JpaRepository<WarehouseInbound, Long> {

}