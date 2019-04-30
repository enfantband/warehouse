package com.samsbeauty.warehouse.picking.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.picking.model.PackingExport;
import com.samsbeauty.warehouse.picking.repository.PackingExportRepository;

@Service
@Transactional
public class PackingExportServiceImpl implements PackingExportService {
	@Autowired
	private PackingExportRepository packingExportRepository;

	public Page<PackingExport> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return packingExportRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public PackingExport getOne(Long exportId) {
		return packingExportRepository.getOne(exportId);
	}
	public PackingExport save(PackingExport packingExport) {
		return packingExportRepository.save(packingExport);
	}
	public void delete(Long exportId) {
		packingExportRepository.delete(exportId);
	}
}