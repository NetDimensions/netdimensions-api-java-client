package com.netdimensions.client;

public enum UserStatus {
	ACTIVE("active"), SUSPENDED("suspend"), CLOSED("close");
	final String code;

	UserStatus(final String code) {
		this.code = code;
	}

	@Override
	public final String toString() {
		return code;
	}
}
