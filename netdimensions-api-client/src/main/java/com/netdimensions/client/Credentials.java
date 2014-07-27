package com.netdimensions.client;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;

public abstract class Credentials {
	private Credentials() {
	}

	@Override
	public abstract String toString();

	public static Credentials bearer(final String accessToken) {
		return new Credentials() {
			@Override
			public final String toString() {
				return "Bearer " + accessToken;
			}
		};
	}

	public static Credentials basic(final String userId, final String password) {
		return new Credentials() {
			@Override
			public final String toString() {
				return "Basic " + BaseEncoding.base64().encode((userId + ":" + password).getBytes(Charsets.UTF_8));
			}
		};
	}
}
