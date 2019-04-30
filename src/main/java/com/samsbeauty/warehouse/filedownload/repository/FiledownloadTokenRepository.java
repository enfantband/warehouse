package com.samsbeauty.warehouse.filedownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samsbeauty.warehouse.filedownload.model.FiledownloadToken;
public interface FiledownloadTokenRepository extends JpaRepository<FiledownloadToken, Long> {
	@Query("SELECT f FROM FiledownloadToken f WHERE f.expiryDate >= now() and token = ?1")
	FiledownloadToken getOneByToken(String token);
}