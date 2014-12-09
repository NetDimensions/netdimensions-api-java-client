package com.netdimensions.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.common.base.Charsets;

final class Field {
	final String name;
	final String value;

	Field(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public final String toString() {
		return encode(name) + "=" + encode(value);
	}

	private static String encode(final String s) {
		try {
			return URLEncoder.encode(s, Charsets.UTF_8.name());
		} catch (final UnsupportedEncodingException e) {
			throw new AssertionError("UTF-8 must be supported by JVM");
		}
	}
}
