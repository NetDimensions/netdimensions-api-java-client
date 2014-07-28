package com.netdimensions.client;

import org.json.JSONObject;

// Noninstantiable utility class
final class Json {
	// Suppress default constructor to enforce noninstantiability
	private Json() {
		throw new AssertionError();
	}

	static String stringOrNull(final JSONObject jo, final String key) {
		return jo.optString(key, null);
	}
}
