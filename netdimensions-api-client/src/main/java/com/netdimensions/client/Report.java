package com.netdimensions.client;

import org.json.JSONObject;

public final class Report {
	private final JSONObject jsonObject;

	public Report(final JSONObject jsonObject) {
		this.jsonObject = jsonObject;
		// TODO Auto-generated constructor stub
	}

	public final String pathname() {
		return Json.stringOrNull(jsonObject, "pathname");
	}
}
