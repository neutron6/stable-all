package com.rsn.utils;

public enum ErrorCodes {

	LIC_NOT_FOUND("User LIC Not found.");

	private final String description;

	private ErrorCodes(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}

}
