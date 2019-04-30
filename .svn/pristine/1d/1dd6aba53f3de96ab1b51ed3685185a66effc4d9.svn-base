package com.samsbeauty.warehouse.filedownload.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.samsbeauty.warehouse.filedownload.model.FiledownloadToken;

public interface FiledownloadTokenService {
	Page<FiledownloadToken> getAll(Integer pageNumber, Integer pageSize, Sort sort);
	FiledownloadToken getOne(Long tokenId);
	FiledownloadToken getOneByToken(String token);
	FiledownloadToken save(FiledownloadToken filedownloadToken);
	void delete(Long tokenId);
}