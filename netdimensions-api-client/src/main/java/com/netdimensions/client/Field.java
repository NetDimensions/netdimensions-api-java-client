package com.netdimensions.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

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

	static String url(final String action, final Iterable<Field> parameters) {
		return action + "?" + Joiner.on('&').join(parameters);
	}

	static Optional<Field> fromNullableValue(final String name, final String value) {
		return value == null ? Optional.<Field> absent() : Optional.of(new Field(name, value));
	}

	private static String encode(final String s) {
		try {
			return URLEncoder.encode(s, Charsets.UTF_8.name());
		} catch (final UnsupportedEncodingException e) {
			throw new AssertionError("UTF-8 must be supported by JVM");
		}
	}
}
