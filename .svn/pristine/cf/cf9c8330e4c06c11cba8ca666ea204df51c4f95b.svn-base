package com.samsbeauty.warehouse.picking.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.picking.model.MissingExport;
import com.samsbeauty.warehouse.picking.repository.MissingExportRepository;

@Service
@Transactional
public class MissingExportServiceImpl implements MissingExportService {
	@Autowired
	private MissingExportRepository missingExportRepository;

	public Page<MissingExport> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return missingExportRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public MissingExport getOne(Long exportId) {
		return missingExportRepository.getOne(exportId);
	}
	public MissingExport save(MissingExport missingExport) {
		return missingExportRepository.save(missingExport);
	}
	public void delete(Long exportId) {
		missingExportRepository.delete(exportId);
	}
}