package com.netdimensions.client;

import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

// Noninstantiable utility class
final class Json {
	// Suppress default constructor to enforce noninstantiability
	private Json() {
		throw new AssertionError();
	}

	static String stringOrNull(final JSONObject jo, final String key) {
		return jo.optString(key, null);
	}

	static JSONObject parseJsonObject(final InputStream inputStream) {
		return new JSONObject(new JSONTokener(inputStream));
	}
}
