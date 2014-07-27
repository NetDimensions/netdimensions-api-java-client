package com.netdimensions.client;

import org.json.JSONObject;

public final class User {
	private final JSONObject jo;

	User(final JSONObject jo) {
		this.jo = jo;
	}

	public final String id() {
		return stringOrNull("id");
	}

	public final String family() {
		return stringOrNull("family");
	}

	public final String given() {
		return stringOrNull("given");
	}

	public final String email() {
		return stringOrNull("email");
	}

	private final String stringOrNull(final String key) {
		return jo.optString(key, null);
	}
}
