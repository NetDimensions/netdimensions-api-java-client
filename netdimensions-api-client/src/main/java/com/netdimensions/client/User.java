package com.netdimensions.client;

import org.json.JSONObject;

public final class User {
	private final JSONObject jo;

	User(final JSONObject jo) {
		this.jo = jo;
	}

	public final String id() {
		return Json.stringOrNull(jo, "id");
	}

	public final String family() {
		return Json.stringOrNull(jo, "family");
	}

	public final String given() {
		return Json.stringOrNull(jo, "given");
	}

	public final String email() {
		return Json.stringOrNull(jo, "email");
	}

	public final String address1() {
		return Json.stringOrNull(jo, "address1");
	}

	public final String address2() {
		return Json.stringOrNull(jo, "address2");
	}

	public final String city() {
		return Json.stringOrNull(jo, "city");
	}

	public final String provinceState() {
		return Json.stringOrNull(jo, "provinceState");
	}

	public final String postalCodeZip() {
		return Json.stringOrNull(jo, "postalCodeZip");
	}

	public final Country country() {
		final JSONObject country = jo.optJSONObject("country");
		return country == null ? null : new Country(country);
	}
}
