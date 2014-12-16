package com.netdimensions.client;

public abstract class SystemRequest {
	final String action;
	final UserRecord user;

	private SystemRequest(final String action, final UserRecord user) {
		this.action = action;
		this.user = user;
	}

	public static SystemRequest createUser(final UserRecord user) {
		return new SystemRequest("A", user) {
		};
	}

	public static SystemRequest updateUser(final UserRecord user) {
		return new SystemRequest("U", user) {
		};
	}
}
