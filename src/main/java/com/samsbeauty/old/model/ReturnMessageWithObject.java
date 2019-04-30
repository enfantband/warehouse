package com.samsbeauty.old.model;

public class ReturnMessageWithObject<T> extends ReturnMessage {
	private T object;

	public T getObject() {
		return object;
	}

	public void setObject(T value) {
		this.object = value;
	}
}