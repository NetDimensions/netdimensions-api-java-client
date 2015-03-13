package com.netdimensions.client;

public abstract class SystemRequest {
	final String action;
	final UserRecord user;

	private SystemRequest(final String action, final UserRecord user) {
		this.action = action;
		this.user = user;
	}

	public static SystemRequest createUser(final String userId, final UserField... fields) {
		return createUser(new UserRecord(userId, fields));
	}

	public static SystemRequest updateUser(final String userId, final UserField... fields) {
		return createUser(new UserRecord(userId, fields));
	}

	/**
	 * @deprecated Use {@link #createUser(String, UserField...)}.
	 */
	@Deprecated
	public static SystemRequest createUser(final UserRecord user) {
		return new SystemRequest("A", user) {
		};
	}

	/**
	 * @deprecated Use {@link #updateUser(String, UserField...)}.
	 */
	@Deprecated
	public static SystemRequest updateUser(final UserRecord user) {
		return new SystemRequest("U", user) {
		};
	}
}
