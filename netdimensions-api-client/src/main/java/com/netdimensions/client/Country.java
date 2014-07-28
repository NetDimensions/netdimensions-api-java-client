package com.netdimensions.client;

import static com.netdimensions.client.Json.stringOrNull;

import org.json.JSONObject;

public final class Country {
	private final JSONObject jo;

	Country(final JSONObject jo) {
		this.jo = jo;
	}

	public final String name() {
		return stringOrNull(jo, "name");
	}

	public final String code() {
		return stringOrNull(jo, "code");
	}
}
