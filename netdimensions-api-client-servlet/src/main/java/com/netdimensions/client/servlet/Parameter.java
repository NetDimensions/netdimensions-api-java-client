package com.netdimensions.client.servlet;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Joiner;
import com.google.common.net.UrlEscapers;

final class Parameter {
	private final String name;
	private final String value;
	static final Parameter CLIENT_ID = new Parameter("client_id", "anonymous");

	Parameter(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public final String toString() {
		return escape(name) + "=" + escape(value);
	}

	static Parameter redirectUri(final HttpServletRequest request) {
		return new Parameter("redirect_uri", URI.create(Servlets.requestUrl(request)).resolve(request.getContextPath() + Servlets.REDIRECTION_URI_PATH)
				.toString());
	}

	static String toString(final Iterable<Parameter> parameters) {
		return Joiner.on('&').join(parameters);
	}

	private static String escape(final String string) {
		return UrlEscapers.urlFormParameterEscaper().escape(string);
	}
}