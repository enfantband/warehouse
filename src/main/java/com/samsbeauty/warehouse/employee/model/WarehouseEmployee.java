package com.samsbeauty.warehouse.employee.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.samsbeauty.warehouse.model.Warehouse;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;
import com.samsbeauty.warehouse.role.model.Role;

@Entity
@Table(name="warehouse_employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarehouseEmployee implements Serializable {	
	private static final long serialVersionUID = -6034148829110857406L;
	
	public static final String SYSTEM_GID = "9e8yApv323535460";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="warehouse_employee_id")
	private Long warehouseEmployeeId;

	private Boolean activated;

	@Column(name="gid")
	private String gid;

	@Column(name="name")
	private String name;
	
	@Column(name="password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="report_color")
	private String reportColor;
	
	@JsonProperty
	//bi-directional many-to-many association to Role
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="warehouse_employee_role"
		, joinColumns={
			@JoinColumn(name="warehouse_employee_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="regBy")
	private List<Warehouse> registeredWarehouses;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="modBy")
	private List<Warehouse> modifiedWarehouses;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="warehouseEmployee")
	@JsonIgnore
	private List<PickingJobTimelineReport> pickingJobTimelineReports;
		
	@Override 
	public boolean equals(Object obj) {
		if(! (obj instanceof WarehouseEmployee)) {
			return false;
		}
		
		WarehouseEmployee that = (WarehouseEmployee) obj;
		
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(gid, that.getGid());
		return eb.isEquals();
 		
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(gid);
		return hcb.toHashCode();
	}
	
	public WarehouseEmployee() {
	}
	
	public String getPassword() {
		return password;
	}

	public WarehouseEmployee(Boolean activated, String gid, String password, String name, String reportColor) {
		super();
		this.activated = activated;
		this.gid = gid;
		this.password = password;
		this.name = name;
		this.reportColor = reportColor;
	}
	
	public void update(String password, String name, String reportColor) {
		this.password = password;
		this.name = name;
		this.reportColor = reportColor;
	}

	public void addRole(Role role) {
		roles.add(role);
	}
	public void removeRole(Role role) {
		if(roles.contains(role)) {
			roles.remove(role);
		}
	}
	public void removeRoles(Set<Role> roles) {
		roles.removeAll(roles);
	}
	public void removeAllRoles() {
		roles.clear();
	}

	public Long getWarehouseEmployeeId() {
		return this.warehouseEmployeeId;
	}

	public Boolean getActivated() {
		return this.activated;
	}

	public String getGid() {
		return this.gid;
	}

	public String getName() {
		return this.name;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}
	
	public String getReportColor() {
		return this.reportColor;
	}
	
	public void deactivate() {
		this.activated = false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("gid=");
		sb.append(gid);
		sb.append(System.lineSeparator());
		sb.append("password=");
		sb.append(password);
		sb.append(System.lineSeparator());
		sb.append("name=");
		sb.append(name);
		sb.append(System.lineSeparator());
		sb.append("reportColor=");
		sb.append(reportColor);
		sb.append(System.lineSeparator());
		return sb.toString();
	}
	
	public static WarehouseEmployeeBuilder builder(String gid, String password) {
		return new WarehouseEmployeeBuilder(gid, password);
	}
	
	public static class WarehouseEmployeeBuilder {
		private Boolean activated;
		private String gid;
		private String password;
		private String name;
		private String reportColor;
		public WarehouseEmployeeBuilder(String gid, String password) {
			this.gid = gid;
			this.password = password;
		}
		public WarehouseEmployeeBuilder setActivated(Boolean activated) {
			this.activated = activated;
			return this;
		}
		
		public WarehouseEmployeeBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public WarehouseEmployeeBuilder setReportColor(String reportColor) {
			this.reportColor = reportColor;
			return this;
		}
		
		public WarehouseEmployee createWarehouseEmployee() {
			return new WarehouseEmployee(activated, gid, password, name, reportColor);
		}		
	}
}