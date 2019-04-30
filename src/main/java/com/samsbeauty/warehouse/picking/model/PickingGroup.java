package com.samsbeauty.warehouse.picking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.samsbeauty.warehouse.Views;

@Entity
@Table(name="picking_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PickingGroup implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6027777509157282307L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picking_group_id")
	private Long pickingGroupId;

	@Column
	private String description;

	@Column
	@JsonView({Views.Picker.class})
	private String name;
	
	@Column(name="product_type")
	@Enumerated(EnumType.STRING)
	@JsonView({Views.Picker.class})
	private ProductType productType;
	
	@Column(name="filter_order")
	private Double filterOrder;

	//bi-directional many-to-one association to PickingGroupCompany
	@OneToMany(mappedBy="pickingGroup", fetch=FetchType.EAGER)
	private List<PickingGroupCompany> pickingGroupCompanies = new ArrayList<PickingGroupCompany>();

	//bi-directional many-to-one association to PickingJob
	@JsonIgnore
	@OneToMany(mappedBy="pickingGroup")
	private List<PickingJob> pickingJobs;
	
	
	public PickingGroup() {
	}
	
	public PickingGroup(String name, String description, ProductType productType, Double filterOrder) {
		super();
		this.description = description;
		this.name = name;
		this.productType = productType;
		this.filterOrder = filterOrder;
	}

	public void update(String name, String description, ProductType productType, Double filterOrder) {
		this.name = name;
		this.description = description;
		this.productType = productType;
		this.filterOrder = filterOrder;
	}

	public Long getPickingGroupId() {
		return pickingGroupId;
	}

	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	
	public ProductType getProductType() {
		return productType;
	}
	
	public Double getFilterOrder() {
		return filterOrder;
	}

	public List<PickingGroupCompany> getPickingGroupCompanies() {
		return pickingGroupCompanies;
	}
	public List<PickingJob> getPickingJobs() {
		return pickingJobs;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("pickingGroupId = ");
		sb.append(pickingGroupId);
		sb.append(System.lineSeparator());
		sb.append("description = ");
		sb.append(description);
		sb.append(System.lineSeparator());
		sb.append("name = ");
		sb.append(name);
		sb.append(System.lineSeparator());
		sb.append("productType = ");
		sb.append(productType);
		sb.append(System.lineSeparator());
		sb.append("filterOrder = ");
		sb.append(filterOrder);
		sb.append(System.lineSeparator());
		return sb.toString();
	}
	
	public static PickingGroupBuilder builder() {
		return new PickingGroupBuilder();
	}
	
	public static class PickingGroupBuilder {
		private String description;
		private String name;
		private ProductType productType;
		private Double filterOrder;
		
		public PickingGroupBuilder() {			
		}
		
		public PickingGroupBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public PickingGroupBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		public PickingGroupBuilder setProductType(ProductType productType) {
			this.productType = productType;
			return this;
		}
		
		public PickingGroupBuilder setFilterOrder(Double filterOrder) {
			this.filterOrder = filterOrder;
			return this;
		}
		
		public PickingGroup createPickingGroup() {
			return new PickingGroup(name, description, productType, filterOrder);
		}
		
	}
	
}