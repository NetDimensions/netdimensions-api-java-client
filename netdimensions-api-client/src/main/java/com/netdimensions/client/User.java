package com.netdimensions.client;

public final class User {
	private final String given;
	private final String family;

	private User(final String given, final String family) {
		this.given = given;
		this.family = family;
	}

	public final String given() {
		return given;
	}

	public final String family() {
		return family;
	}
}
