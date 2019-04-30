package com.samsbeauty.warehouse.printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.printer.model.PrinterLabel;
import com.samsbeauty.warehouse.printer.service.PrinterLabelService;

@RestController
@RequestMapping("/api/printer/label")
public class PrinterLabelController {

	@Autowired
	private PrinterLabelService printerLabelService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<PrinterLabel>> getAll() {
		return new ResponseEntity<List<PrinterLabel>>(printerLabelService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	PrinterLabel add(@RequestBody @Valid final PrinterLabel printerLabel) {
		PrinterLabel newPrinterLabel = new PrinterLabel(printerLabel.getLabelName(), printerLabel.getLabelType());
		return printerLabelService.save(newPrinterLabel);
	}

	@RequestMapping(method=RequestMethod.PUT)
	PrinterLabel update(@RequestBody @Valid final PrinterLabel printerLabel) {
		PrinterLabel updatePrinterLabel = printerLabelService.getOne(printerLabel.getLabelId());
		updatePrinterLabel.update(printerLabel.getLabelName(), printerLabel.getLabelType());		
		return printerLabelService.save(updatePrinterLabel);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{labelId}")
	PrinterLabel delete(@PathVariable Long labelId) {
		PrinterLabel deletePrinterLabel = printerLabelService.getOne(labelId);
		deletePrinterLabel.deactivate();
		return  printerLabelService.save(deletePrinterLabel);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/ids/{labelIds}")
	ResponseEntity<List<PrinterLabel>> deleteByIds(@PathVariable String labelIds) {
		List<String> ids = Arrays.asList(labelIds.split(","));
		List<PrinterLabel> deletedList = new ArrayList<>();
		for(String id : ids) {
			Long labelId = Long.valueOf(id);
			PrinterLabel printerLabel = printerLabelService.getOne(labelId);
			printerLabel.deactivate();
			deletedList.add(printerLabel);
			printerLabelService.save(printerLabel);
		}
		return new ResponseEntity<List<PrinterLabel>>(deletedList, HttpStatus.OK);
	}

}