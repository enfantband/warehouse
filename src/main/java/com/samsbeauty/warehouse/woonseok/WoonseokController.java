package com.samsbeauty.warehouse.woonseok;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.woonseok.model.Woonseok;
import com.samsbeauty.warehouse.woonseok.service.WoonseokService;

@RestController
@RequestMapping("/api/woonseok")
public class WoonseokController {

	@Autowired
	private WoonseokService woonseokService;
	@Autowired
	private WarehouseEmployeeService warehouseEmployeeService;

	@Value("${page.default.num}")
	protected Integer DEFAULT_NUM_PER_PAGE;

	// Retrieve All Resources
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<Woonseok>> getAll() {		

		List<Woonseok> requests = new ArrayList<>();
		System.out.println("CALL GET ALL WOOSEOK JPA ");
		requests = woonseokService.getAll();
		
		return new ResponseEntity<>(requests, HttpStatus.OK);
	}

}