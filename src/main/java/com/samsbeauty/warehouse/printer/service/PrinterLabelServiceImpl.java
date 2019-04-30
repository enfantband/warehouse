package com.samsbeauty.warehouse.printer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.printer.model.PrinterLabel;
import com.samsbeauty.warehouse.printer.repository.PrinterLabelRepository;


import static org.springframework.data.jpa.domain.Specifications.where;
import static com.samsbeauty.warehouse.printer.specification.PrinterLabelSpecifications.*;

@Service
@Transactional
public class PrinterLabelServiceImpl implements PrinterLabelService {
	@Autowired
	private PrinterLabelRepository printerLabelRepository;
	public Page<PrinterLabel> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return printerLabelRepository.findAll(where(activated()), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<PrinterLabel> getAll() {
		return printerLabelRepository.findAll(where(activated()));
	}
	public PrinterLabel getOne(Long labelId) {
		return printerLabelRepository.getOne(labelId);
	}
	public PrinterLabel save(PrinterLabel printerLabel) {
		return printerLabelRepository.save(printerLabel);
	}
	public void delete(Long labelId) {
		printerLabelRepository.delete(labelId);
	}
}