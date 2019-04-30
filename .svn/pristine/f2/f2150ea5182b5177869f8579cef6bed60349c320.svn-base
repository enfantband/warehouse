package com.samsbeauty.warehouse.picking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.samsbeauty.warehouse.picking.model.MissingExport;

public interface MissingExportService {
	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")	
	Page<MissingExport> getAll(Integer pageNumber, Integer pageSize, Sort sort);

	@PreAuthorize("hasAuthority('admin.picking.read') or hasAuthority('all.picking.read')")
	MissingExport getOne(Long exportId);

	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	MissingExport save(MissingExport missingExport);
	@PreAuthorize("hasAuthority('admin.picking.write') or hasAuthority('all.picking.write')")
	void delete(Long exportId);
}