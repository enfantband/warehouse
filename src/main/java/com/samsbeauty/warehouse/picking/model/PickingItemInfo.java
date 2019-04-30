package com.samsbeauty.warehouse.picking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.samsbeauty.warehouse.Views;

@Table(name="picking_item_info")
@Entity
public class PickingItemInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3998746078601138330L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picking_item_info_id")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private Long pickingItemInfoId;
	
	@Column(name="generated_barcode")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private String generatedBarcode;
	
	@Column(name="product_barcode")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private String productBarcode;
	
	@Column(name="order_no")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private String orderNo;
	
	@Column(name="product_group")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private String productGroup;
	
	@Column
	@JsonView({Views.Picker.class, Views.Admin.class})
	private String title;
	
	@Column(name="image_url")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private String imageUrl;
	
	@Column(name="order_quantity")
	@JsonView({Views.Picker.class, Views.Admin.class})
	private Integer orderQuantity;
	
	@Transient
	@JsonView({Views.Picker.class, Views.Admin.class})
	private Integer pickedQuantity;
		 
	@Transient
	@JsonView({Views.Picker.class, Views.Admin.class})
	private Integer missedQuantity;
		
	@ManyToOne
	@JoinColumn(name="picking_job_id")	
	@JsonIgnore
	private PickingJob pickingJob;
	
	public PickingItemInfo() {
		
	}
	
	public PickingItemInfo(
			Long pickingItemInfoId, 
			String generatedBarcode, 
			String productGroup, 
			Integer orderQuantity, 
			PickingJob pickingJob, 
			String productBarcode, 
			String orderNo, 
			String title, 
			String imageUrl, 
			Integer pickedQuantity,
			Integer missedQuantity) {
		super();
		this.pickingItemInfoId = pickingItemInfoId;
		this.generatedBarcode = generatedBarcode;
		this.productGroup = productGroup;
		this.orderQuantity = orderQuantity;
		this.pickingJob = pickingJob;
		this.productBarcode = productBarcode;
		this.orderNo = orderNo;
		this.title = title;
		this.imageUrl = imageUrl;
		this.pickedQuantity = pickedQuantity;
		this.missedQuantity = missedQuantity;
	}
	
	public Long getPickingItemInfoId() {
		return pickingItemInfoId;
	}

	public String getGeneratedBarcode() {
		return generatedBarcode;
	}

	public String getProductBarcode() {
		return productBarcode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public String getTitle() {
		return title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	
	public Integer getPickedQuantity() {
		return pickedQuantity;
	}
	
	public Integer getMissedQuantity() {
		return missedQuantity;
	}
	
	public PickingJob getPickingJob() {
		return pickingJob;
	}

	public static PickingItemInfoBuilder builder(PickingJob pickingJob) {
		return new PickingItemInfoBuilder(pickingJob);
	}
	public static class PickingItemInfoBuilder {		
		private Long pickingItemInfoId;
		private String generatedBarcode;
		private String productBarcode;
		private String productGroup;
		private Integer orderQuantity;		
		private PickingJob pickingJob;
		private String title;
		private String orderNo;
		private String imageUrl;
		private Integer pickedQuantity;
		private Integer missedQuantity;
		
		public PickingItemInfoBuilder(PickingJob pickingJob) {
			this.pickingJob = pickingJob;
		}
		
		public PickingItemInfoBuilder setPickingItemInfoId(Long pickingItemInfoId) {
			this.pickingItemInfoId = pickingItemInfoId;
			return this;
		}
		
		public PickingItemInfoBuilder setGeneratedBarcode(String generatedBarcode) {
			this.generatedBarcode = generatedBarcode;
			return this;
		}
		public PickingItemInfoBuilder setProductGroup(String productGroup) {
			this.productGroup = productGroup;
			return this;
		}
		public PickingItemInfoBuilder setOrderQuantity(Integer orderQuantity) {
			this.orderQuantity = orderQuantity;
			return this;
		}
		
		public PickingItemInfoBuilder setPickingJob(PickingJob pickingJob) {
			this.pickingJob = pickingJob;
			return this;
		}
		public PickingItemInfoBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		public PickingItemInfoBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}
		public PickingItemInfoBuilder setProductBarcode(String productBarcode) {
			this.productBarcode = productBarcode;
			return this;
		}
		public PickingItemInfoBuilder setOrderNo(String orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		public PickingItemInfoBuilder setPickedQuantity(Integer pickedQuantity) {
			this.pickedQuantity = pickedQuantity;
			return this;
		}
		public PickingItemInfoBuilder setMissedQuantity(Integer missedQuantity) {
			this.missedQuantity = missedQuantity;
			return this;
		}
		
		
		public PickingItemInfo createPickingItemInfo() {
			return new PickingItemInfo(pickingItemInfoId, generatedBarcode, productGroup, orderQuantity, pickingJob, productBarcode, orderNo, title, imageUrl, pickedQuantity, missedQuantity);
		}
	}
}
