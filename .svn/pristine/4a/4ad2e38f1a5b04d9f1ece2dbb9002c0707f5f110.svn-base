package com.samsbeauty.warehouse.filedownload.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.samsbeauty.warehouse.filedownload.model.FiledownloadToken;
import com.samsbeauty.warehouse.filedownload.repository.FiledownloadTokenRepository;

@Service
@Transactional
public class FiledownloadTokenServiceImpl implements FiledownloadTokenService {
	@Autowired
	private FiledownloadTokenRepository filedownloadTokenRepository;

	public Page<FiledownloadToken> getAll(Integer pageNumber, Integer pageSize, Sort sort) {
		return filedownloadTokenRepository.findAll(new PageRequest(pageNumber-1, pageSize, sort));
	}
	public FiledownloadToken getOne(Long tokenId) {
		return filedownloadTokenRepository.getOne(tokenId);
	}
	public FiledownloadToken getOneByToken(String token) {
		return filedownloadTokenRepository.getOneByToken(token);
	}
	public FiledownloadToken save(FiledownloadToken filedownloadToken) {
		return filedownloadTokenRepository.save(filedownloadToken);
	}
	public void delete(Long tokenId) {
		filedownloadTokenRepository.delete(tokenId);
	}
}