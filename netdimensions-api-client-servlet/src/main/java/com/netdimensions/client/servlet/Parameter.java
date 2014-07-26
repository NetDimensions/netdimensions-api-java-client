package com.netdimensions.client.servlet;

import com.google.common.net.UrlEscapers;

final class Parameter {
	private final String name;
	private final String value;

	Parameter(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	public final String toString() {
		return escape(name) + "=" + escape(value);
	}

	private static String escape(final String string) {
		return UrlEscapers.urlFormParameterEscaper().escape(string);
	}
}