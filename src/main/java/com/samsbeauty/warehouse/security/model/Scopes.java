package com.samsbeauty.warehouse.security.model;

public enum Scopes {
	REFRESH_TOKEN;
	
	public String authority() {
		return "ROLE_" + this.name();
	}
}
