package com.samsbeauty.warehouse.menu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samsbeauty.warehouse.role.model.Role;


@Entity
@Table(name="menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Menu implements Serializable {
	private static final long serialVersionUID = -3687302002416038122L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="menu_id")
	private Long menuId;

	private Boolean activated;

	private String icon;
	
	private String description;

	private String name;

	private Double ordered;
	
	private String url;	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date")
	private Date regDate;

	//bi-directional many-to-many association to Role
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="menu_role", joinColumns={
			@JoinColumn(name="menu_id")
	}, inverseJoinColumns = {
			@JoinColumn(name="role_id")
	})
	private Set<Role> roles = new HashSet<>();

	public Menu() {
	}
	
	

	public Menu(Boolean activated, String description, String name, String icon, Double ordered, String url, Date regDate) {
		super();
		this.activated = activated;
		this.description = description;
		this.icon = icon;
		this.name = name;
		this.ordered = ordered;
		this.url = url;
		this.regDate = regDate;
	}
	
	public void update(String name, String description, String url, String icon, Double ordered) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.icon = icon;
		this.ordered = ordered;
	}
		
	public void deactivate() {
		this.activated = false;
	}

	public void addRole(Role role) {
		roles.add(role);
	}
	public void removeAllRoles() {
		roles.clear();
	}
	public Long getMenuId() {
		return this.menuId;
	}

	public Boolean getActivated() {
		return this.activated;
	}
	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public String getUrl() {
		return this.url;
	}
	
	public String getIcon() {
		return this.icon;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}
	
	public Double getOrdered() {
		return ordered;
	}
	
	public Date getRegDate() {
		return this.regDate;
	}
	
	public static MenuBuilder builder() {
		return new MenuBuilder();
	}
	
	public static class MenuBuilder {
		private Boolean activated;
		private String description;
		private String name;
		private String icon;
		private Double ordered;
		private String url;
		private Date regDate;
		public MenuBuilder() {
			
		}
		
		public MenuBuilder setActivated(Boolean activated) {
			this.activated = activated;
			return this;
		}
		
		public MenuBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public MenuBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public MenuBuilder setIcon(String icon) {
			this.icon = icon;
			return this;
		}
		
		public MenuBuilder setUrl(String url) {
			this.url = url;
			return this;
		}
		public MenuBuilder setOrdered(Double ordered) {
			this.ordered = ordered;
			return this;
		}
		public MenuBuilder setRegDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}
		
		public Menu createMenu() {
			return new Menu(activated, description, name, icon, ordered, url, regDate);
		}
	}

}