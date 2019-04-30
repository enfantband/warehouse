package com.samsbeauty.old.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.warehouse.printing.model.PrintContent;

@Controller
@RequestMapping(value="/api/labelprint")
public class PrintingBarcodeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/api/labelprint/printlabel", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage sendToPrintBarcodeLabel(
			@RequestBody PrintContent printContent
			) {
		logger.error("---------- printing label function is not implemented yet ---------");
		return new ReturnMessage("SUCCESS", true);
	}
}
