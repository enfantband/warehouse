package com.samsbeauty.warehouse.picking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="picking_group_company")
public class PickingGroupCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picking_group_company_id")
	private Long pickingGroupCompanyId;
	
	@Column(name="company_code")
	private String companyCode;
	
	@ManyToOne
	@JoinColumn(name="picking_group_id")
	private PickingGroup pickingGroup;
	
	public String getCompanyCode() {
		return companyCode;
	}
	
	public PickingGroupCompany() {
		
	}
	
	public PickingGroupCompany(String companyCode, PickingGroup pickingGroup) {
		this.companyCode = companyCode;
		this.pickingGroup = pickingGroup;
	}
	
	public Long getPickingGropuCompanyId() {
		return this.pickingGroupCompanyId;
	}
	
	public static PickingGroupCompanyBuilder builder(PickingGroup pickingGroup) {
		return new PickingGroupCompanyBuilder(pickingGroup);
	}
	
	public static class PickingGroupCompanyBuilder {
		
		private String companyCode;
		private PickingGroup pickingGroup;
		
		public PickingGroupCompanyBuilder(PickingGroup pickingGroup) {
			this.pickingGroup = pickingGroup;
		}
		
		public PickingGroupCompanyBuilder setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
			return this;
		}
		
		public PickingGroupCompanyBuilder setPickingGroup(PickingGroup pickingGroup) {
			this.pickingGroup = pickingGroup;
			return this;
		}
		
		public PickingGroupCompany createPickingGroupCompany() {
			return new PickingGroupCompany(companyCode, pickingGroup);
		}
	}
}
