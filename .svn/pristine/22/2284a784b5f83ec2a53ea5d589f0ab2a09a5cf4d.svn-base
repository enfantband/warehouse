package com.samsbeauty.warehouse.role.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.menu.model.Menu;

@Entity
@Table(name="role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements Serializable {

	private static final long serialVersionUID = -118291178926741514L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private Long roleId;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-many association to Menu
	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private List<Menu> menus;

	//bi-directional many-to-many association to Picker
	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private List<WarehouseEmployee> warehouseEmployees;

	//bi-directional many-to-many association to Privilege
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="role_privilege"
		, joinColumns={
			@JoinColumn(name="role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="privilege_id")
			}
		)
	private List<Privilege> privileges;
	
	private Boolean activated;

	public Role() {
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
		this.activated = true;
	}
	
	public void update(String roleName) {
		this.roleName = roleName;
	}
	
	public Role deactivate() {
		this.activated = false;
		return this;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}
	
	public Boolean isActivated() {
		return activated;
	}

	public List<WarehouseEmployee> getWarehouseEmployees() {
		return this.warehouseEmployees;
	}

	public List<Privilege> getPrivileges() {
		return this.privileges;
	}
	
	public boolean hasPrivilege(Privilege privilege) {
		for(Privilege p : this.privileges) {
			if(p.getPrivilege().equals(privilege.getPrivilege())) {
				return true;
			}
		}
		return false;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(! (obj instanceof Role)) {
			return false;
		}
		
		Role that = (Role) obj;
		
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(roleName, that.getRoleName());
		eb.append(roleId, that.getRoleId());
		return eb.isEquals();
 		
	}
}
