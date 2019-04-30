package com.samsbeauty.warehouse.printer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.printer.model.PrinterLabel;

@PreAuthorize("isAuthenticated()")
public interface PrinterLabelService {
	@PreAuthorize("hasAuthority('admin.setting.read') or hasAuthority('all.setting.read')")
	Page<PrinterLabel> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	@PreAuthorize("hasAuthority('admin.setting.read') or hasAuthority('all.setting.read')")
	List<PrinterLabel> getAll();
	@PreAuthorize("hasAuthority('admin.setting.read') or hasAuthority('all.setting.read')")
	PrinterLabel getOne(Long labelId);
	@PreAuthorize("hasAuthority('admin.setting.write') or hasAuthority('all.setting.write')")
	PrinterLabel save(PrinterLabel printerLabel);
	@PreAuthorize("hasAuthority('admin.setting.write') or hasAuthority('all.setting.write')")
	void delete(Long labelId);
}