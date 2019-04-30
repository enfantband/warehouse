package com.samsbeauty.warehouse.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.samsbeauty.warehouse.exception.rest.WrongParameterException;
import com.samsbeauty.warehouse.filedownload.model.FiledownloadToken;
import com.samsbeauty.warehouse.filedownload.service.FiledownloadTokenService;
import com.samsbeauty.warehouse.picking.model.MissingExport;
import com.samsbeauty.warehouse.picking.model.PackingExport;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobExport;
import com.samsbeauty.warehouse.picking.model.PickingJobGroup;
import com.samsbeauty.warehouse.picking.service.PickingJobGroupService;
import com.samsbeauty.warehouse.picking.service.PickingJobService;

@Controller
public class FiledownloadController {
	@Value("${pickinglist.pdf.dir}")
	private String pickingJobDir;
	
	@Autowired private PickingJobService pickingJobService;
	@Autowired private PickingJobGroupService pickingJobGroupService;
	@Autowired private FiledownloadTokenService filedownloadTokenService;
	
	@RequestMapping(value="/download/picking/{pickingJobId}", method=RequestMethod.GET)
	public void downloadPickingFile(HttpServletResponse response, @PathVariable("pickingJobId") Long pickingJobId, @RequestParam(name="token", required=true) String token) throws WrongParameterException, IOException {
		FiledownloadToken fileToken = filedownloadTokenService.getOneByToken(token);
		if(fileToken == null) {
			throw new WrongParameterException("Wrong request!! Please try it again later");
		}
		filedownloadTokenService.delete(fileToken.getTokenId());
		
		PickingJob pickingJob = pickingJobService.getOne(pickingJobId);
		PickingJobExport pickingJobExport = pickingJob.getPickingJobExport();
		
		String filePath = pickingJobDir + "/" + pickingJobExport.getFolder() + "/" + pickingJobExport.getFilename();
		File file = new File(filePath);
		if(!file.exists()) {
			throw new WrongParameterException("Wrong request!!");
		}
		
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		response.setContentType(mimeType);
		
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		response.setContentLength((int) file.length());
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value="/download/packing/{pickingJobGroupId}", method=RequestMethod.GET)
	public void downloadPackingFile(HttpServletResponse response, @PathVariable("pickingJobGroupId") Long pickingJobGroupId, @RequestParam(name="token", required=true) String token) throws WrongParameterException, IOException {
		FiledownloadToken fileToken = filedownloadTokenService.getOneByToken(token);
		if(fileToken == null) {
			throw new WrongParameterException("Wrong request!! Please try it again later");
		}
		filedownloadTokenService.delete(fileToken.getTokenId());
		
		PickingJobGroup pickingJobGroup = pickingJobGroupService.getOne(pickingJobGroupId);
		PackingExport packingExport = pickingJobGroup.getPackingExport();
		
		String filePath = pickingJobDir + "/" + packingExport.getFolder() + "/" + packingExport.getFilename();
		File file = new File(filePath);
		if(!file.exists()) {
			throw new WrongParameterException("Wrong request!!");
		}
		
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		response.setContentType(mimeType);
		
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		response.setContentLength((int) file.length());
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value="/download/missing/{pickingJobId}", method=RequestMethod.GET)
	public void downloadMissingFile(HttpServletResponse response, @PathVariable("pickingJobId") Long pickingJobId, @RequestParam(name="token", required=true) String token) throws WrongParameterException, IOException {
		FiledownloadToken fileToken = filedownloadTokenService.getOneByToken(token);
		if(fileToken == null) {
			throw new WrongParameterException("Wrong request!! Please try it again later");
		}
		filedownloadTokenService.delete(fileToken.getTokenId());
		
		PickingJob pickingJob = pickingJobService.getOne(pickingJobId);
		MissingExport missingExport = pickingJob.getMissingExport();
		
		String filePath = pickingJobDir + "/" + missingExport.getFolder() + "/" + missingExport.getFilename();
		File file = new File(filePath);
		if(!file.exists()) {
			throw new WrongParameterException("Wrong request!!");
		}
		
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		response.setContentType(mimeType);
		
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		response.setContentLength((int) file.length());
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}
