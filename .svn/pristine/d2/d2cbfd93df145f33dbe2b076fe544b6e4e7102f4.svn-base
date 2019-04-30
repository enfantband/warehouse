package com.samsbeauty.warehouse.picking.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.picking.model.PickingJobExport;
import com.samsbeauty.warehouse.picking.repository.PickingJobExportRepository;

@Service
@Transactional
public class PickingJobExportServiceImpl implements PickingJobExportService {
	@Autowired
	private PickingJobExportRepository pickingJobExportRepository;

	public Page<PickingJobExport> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return pickingJobExportRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public PickingJobExport getOne(Long exportId) {
		return pickingJobExportRepository.getOne(exportId);
	}
	public PickingJobExport save(PickingJobExport pickingJobExport) {
		return pickingJobExportRepository.save(pickingJobExport);
	}
	public void delete(Long exportId) {
		pickingJobExportRepository.delete(exportId);
	}
}