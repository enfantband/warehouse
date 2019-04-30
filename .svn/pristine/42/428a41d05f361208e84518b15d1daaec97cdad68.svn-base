package com.samsbeauty.warehouse.exception.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.samsbeauty.old.model.ErrorMessage;
import com.samsbeauty.warehouse.picking.exception.WrongItemScanException;
import com.samsbeauty.warehouse.picking.exception.WrongTimelineException;

@ControllerAdvice
@RestController
public class ExceptionController {
	@ExceptionHandler(DuplicatePrivilegeToRoleException.class)
	ResponseEntity<ErrorMessage> duplicatePrivilegeToRole(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WrongParameterException.class)
	ResponseEntity<ErrorMessage> wrongParameter(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PickingJobNotFoundException.class)
	ResponseEntity<ErrorMessage> pickingJobNotFound(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WrongDateFormatException.class)
	ResponseEntity<ErrorMessage> wrongDateFormat(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WarehouseEmployeeNotFoundException.class)
	ResponseEntity<ErrorMessage> warehouseEmployeeNotFound(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PickingJobTimelineNotFoundException.class)
	ResponseEntity<ErrorMessage> pickingJobTimelineNotFoundException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WarehouseGroupNotFoundException.class)
	ResponseEntity<ErrorMessage> warehouseGroupNotFoundException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidProductBarcodeException.class)
	ResponseEntity<ErrorMessage> invalidProductBarcodeException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LocationNotFoundException.class)
	ResponseEntity<ErrorMessage> wrongLocationBarcodeException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WrongTimelineException.class)
	ResponseEntity<ErrorMessage> wrongTimelineException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WrongItemScanException.class)
	ResponseEntity<ErrorMessage> wrongItemScanException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TooManyResultsException.class)
	ResponseEntity<ErrorMessage> tooManyResultsException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExistingWarehouseItemException.class)
	ResponseEntity<ErrorMessage> existingWarehouseItemException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	ResponseEntity<ErrorMessage> invalidRequestException(HttpServletRequest req, Exception e) {
		ErrorMessage error = new ErrorMessage(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorMessage> (error, HttpStatus.BAD_REQUEST);
	}
}
