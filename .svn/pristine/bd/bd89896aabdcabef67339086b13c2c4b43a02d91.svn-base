package com.samsbeauty.warehouse.filedownload;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.xmp.impl.Base64;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.employee.service.WarehouseEmployeeService;
import com.samsbeauty.warehouse.exception.rest.WarehouseEmployeeNotFoundException;
import com.samsbeauty.warehouse.filedownload.model.FiledownloadToken;
import com.samsbeauty.warehouse.filedownload.service.FiledownloadTokenService;

@RestController
@RequestMapping("/api/filedownloadToken")
public class FiledownloadTokenController {

	@Autowired private FiledownloadTokenService filedownloadTokenService;
	@Autowired private WarehouseEmployeeService warehouseEmployeeService;

	@RequestMapping(method=RequestMethod.POST)
	FiledownloadToken requestToken(HttpServletRequest request) throws WarehouseEmployeeNotFoundException {
		String employeeGid = (String) request.getAttribute("GID");
		System.out.println("employee gid is " + employeeGid);
		WarehouseEmployee employeeInfo = warehouseEmployeeService.getOneByGid(employeeGid);
		if(employeeInfo == null) {
			throw new WarehouseEmployeeNotFoundException("Employee does not exist");
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 60);		
		
		String key = Base64.encode(employeeGid + "_" + System.currentTimeMillis());
		FiledownloadToken newFiledownloadToken = FiledownloadToken
				.builder()
				.setExpiryDate(cal.getTime())
				.setRequestBy(employeeInfo)
				.setToken(key)
				.createFiledownloadToken();
		//Implement here
		return filedownloadTokenService.save(newFiledownloadToken);
	}
	
	

	@RequestMapping(method=RequestMethod.PUT)
	FiledownloadToken update(@RequestBody @Valid final FiledownloadToken filedownloadToken) {
		FiledownloadToken updateFiledownloadToken = filedownloadTokenService.getOne(filedownloadToken.getTokenId());
		//Implement here
		return updateFiledownloadToken;
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{tokenId}")
	FiledownloadToken delete(@PathVariable Long tokenId) {
		FiledownloadToken deleteFiledownloadToken = filedownloadTokenService.getOne(tokenId);
		filedownloadTokenService.delete(tokenId);
		return  deleteFiledownloadToken;
	}

}