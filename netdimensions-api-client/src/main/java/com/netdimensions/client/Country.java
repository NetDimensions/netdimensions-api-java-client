package com.netdimensions.client;

import org.json.JSONObject;

public final class Country {
	private final JSONObject jo;

	Country(final JSONObject jo) {
		this.jo = jo;
	}

	public final String name() {
		return Json.stringOrNull(jo, "name");
	}
}
