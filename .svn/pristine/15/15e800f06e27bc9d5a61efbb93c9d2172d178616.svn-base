package com.samsbeauty.warehouse.printer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.printer.model.Printer;

@PreAuthorize("isAuthenticated()")
public interface PrinterService {
	Page<Printer> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	List<Printer> getAll();
	Printer getOne(Long printerId);
	@PreAuthorize("hasAuthority('admin.setting.write')")
	Printer save(Printer printer);
	@PreAuthorize("hasAuthority('admin.setting.write')")
	void delete(Long printerId);
}