package com.samsbeauty.warehouse.role.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="privilege")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Privilege implements Serializable {
	private static final long serialVersionUID = 5241690778523935793L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="privilege_id")
	private Long privilegeId;

	private String privilege;

	//bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany(mappedBy="privileges")
	private List<Role> roles;
	
	private Boolean activated;
	
	public Privilege() {
		
	}
	
	public Privilege(String privilege) {
		this.privilege = privilege;
		this.activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void update(String privilege) {
		this.privilege = privilege;
	}
	
	public Privilege deactivate() {
		this.activated = false;
		return this;
	}
	
	public Long getPrivilegeId() {
		return privilegeId;
	}
	
	public String getPrivilege() {
		return privilege;
	}
}
