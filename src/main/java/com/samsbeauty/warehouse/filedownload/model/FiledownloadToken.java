package com.samsbeauty.warehouse.filedownload.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

@Entity
@Table(name="filedownload_token")
public class FiledownloadToken {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="token_id")
	private Long tokenId;
	
	@Column
	private String token;
	
	@Column(name="expiry_date")
	private Date expiryDate;
	
	@ManyToOne
	@JoinColumn(name="request_by")
	private WarehouseEmployee requestBy;
	
	public Long getTokenId() {
		return tokenId;
	}

	public String getToken() {
		return token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public WarehouseEmployee getRequestBy() {
		return requestBy;
	}
	
	public FiledownloadToken() {}

	public FiledownloadToken(Long tokenId, String token, Date expiryDate, WarehouseEmployee requestBy) {
		super();
		this.tokenId = tokenId;
		this.token = token;
		this.expiryDate = expiryDate;
		this.requestBy = requestBy;
	}
	
	public static FiledownloadTokenBuilder builder() { 
		return new FiledownloadTokenBuilder();
	}
	
	public static class FiledownloadTokenBuilder {
		private Long tokenId;
		private String token;
		private Date expiryDate;
		private WarehouseEmployee requestBy;
		
		public FiledownloadTokenBuilder setTokenId(Long tokenId) {
			this.tokenId = tokenId;
			return this;
		}
		
		public FiledownloadTokenBuilder setToken(String token) {
			this.token = token;
			return this;
		}
		
		public FiledownloadTokenBuilder setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}
		
		public FiledownloadTokenBuilder setRequestBy(WarehouseEmployee requestBy) {
			this.requestBy = requestBy;
			return this;
		}
		
		public FiledownloadToken createFiledownloadToken() {
			return new FiledownloadToken(tokenId, token, expiryDate, requestBy);
		}
		
		
	}
}
