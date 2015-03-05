package com.netdimensions.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Iterables;

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

	static String encodeCsvFields(final Iterable<Field> fields) {
		final Builder<String> names = ImmutableList.builder();
		final Builder<String> values = ImmutableList.builder();
		for (final Field field : fields) {
			names.add(field.name);
			values.add(field.value);
		}
		return encodeCsvValues(names.build()) + encodeCsvValues(values.build());
	}

	private static String encodeCsvValues(final Iterable<String> values) {
		final StringBuilder result = new StringBuilder();
		if (!Iterables.isEmpty(values)) {
			result.append(encodeCsv(values.iterator().next()));
			for (final String s : Iterables.skip(values, 1)) {
				result.append(',');
				result.append(encodeCsv(s));
			}
		}
		return result.append("\r\n").toString();
	}

	private static String encodeCsv(final String s) {
		return "\"" + s.replace("\"", "\"\"") + "\"";
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
