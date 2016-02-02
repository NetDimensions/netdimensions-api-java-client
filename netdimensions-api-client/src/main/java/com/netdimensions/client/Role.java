package com.netdimensions.client;

import static com.netdimensions.client.Json.stringOrNull;

import org.json.JSONObject;

public final class Role {
	private final JSONObject jo;

	Role(final JSONObject jo) {
		this.jo = jo;
	}

	public final String id() {
		return stringOrNull(jo, "id");
	}
}
