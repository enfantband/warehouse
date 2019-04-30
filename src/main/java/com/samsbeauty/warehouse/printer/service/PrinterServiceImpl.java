package com.samsbeauty.warehouse.printer.service;

import static com.samsbeauty.warehouse.printer.specification.PrinterSpecifications.activated;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsbeauty.warehouse.printer.model.Printer;
import com.samsbeauty.warehouse.printer.repository.PrinterRepository;

@Service
@Transactional
public class PrinterServiceImpl implements PrinterService {
	@Autowired
	private PrinterRepository printerRepository;

	public Page<Printer> getAll(Integer pageNumber, Integer pageSize, Sort sort) {		
		return printerRepository.findAll(where(activated()), new PageRequest(pageNumber-1, pageSize, sort));
	}
	public List<Printer> getAll() {
		return printerRepository.findAll(where(activated()));
	}
	public Printer getOne(Long printerId) {
		return printerRepository.getOne(printerId);
	}
	public Printer save(Printer printer) {
		return printerRepository.save(printer);
	}
	public void delete(Long printerId) {
		printerRepository.delete(printerId);
	}
}