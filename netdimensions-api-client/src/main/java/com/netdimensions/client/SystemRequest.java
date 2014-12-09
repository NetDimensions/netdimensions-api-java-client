package com.netdimensions.client;

public abstract class SystemRequest {
	final String userId;
	final String password;

	private SystemRequest(final String userId, final String password) {
		this.userId = userId;
		this.password = password;
	}

	public static SystemRequest updateUser(final String userId, final String password) {
		return new SystemRequest(userId, password) {
		};
	}
}
