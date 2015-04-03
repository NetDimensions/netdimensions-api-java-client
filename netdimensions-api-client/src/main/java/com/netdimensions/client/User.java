package com.netdimensions.client;

import static com.netdimensions.client.Json.stringOrNull;

import org.json.JSONObject;

public final class User {
	private final JSONObject jo;

	User(final JSONObject jo) {
		this.jo = jo;
	}

	public final String id() {
		return stringOrNull(jo, "id");
	}

	public final String family() {
		return stringOrNull(jo, "family");
	}

	public final String given() {
		return stringOrNull(jo, "given");
	}

	public final String email() {
		return stringOrNull(jo, "email");
	}

	public final String address1() {
		return stringOrNull(jo, "address1");
	}

	public final String address2() {
		return stringOrNull(jo, "address2");
	}

	public final String city() {
		return stringOrNull(jo, "city");
	}

	public final String provinceState() {
		return stringOrNull(jo, "provinceState");
	}

	public final String postalCodeZip() {
		return stringOrNull(jo, "postalCodeZip");
	}

	public final Country country() {
		final JSONObject country = jo.optJSONObject("country");
		return country == null ? null : new Country(country);
	}

	public final String phone() {
		return stringOrNull(jo, "phone");
	}

	public final String prefix() {
		return stringOrNull(jo, "prefix");
	}

	public final String jobTitle() {
		return stringOrNull(jo, "jobTitle");
	}
}
