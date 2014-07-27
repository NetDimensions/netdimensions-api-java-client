package com.netdimensions.client;

import org.json.JSONObject;

public abstract class Request<T> {
	final String action;

	private Request(final String action) {
		this.action = action;
	}

	abstract T result(JSONObject jo);

	public static Request<User> me() {
		return new Request<User>("me") {
			@Override
			final User result(final JSONObject jo) {
				return new User(jo);
			}
		};
	}
}
