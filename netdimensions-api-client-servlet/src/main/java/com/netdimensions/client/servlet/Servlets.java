package com.netdimensions.client.servlet;

import javax.servlet.http.HttpServletRequest;

import com.netdimensions.client.Client;
import com.netdimensions.client.Credentials;

// Noninstantiable utility class
public final class Servlets {
	static final String ATTRIBUTE_NAME_CSRF_TOKEN = "com.netdimensions.client.servlet.CSRF_TOKEN";
	static final String TALENT_SUITE_BASE_URL = "https://preview.netdimensions.com/preview/";
	static final String REDIRECTION_URI_PATH = "/cb";
	static final String ATTRIBUTE_NAME_ACCESS_TOKEN = "com.netdimensions.client.servlet.ACCESS_TOKEN";

	// Suppress default constructor for noninstantiability
	private Servlets() {
		throw new AssertionError();
	}

	public static Client client(final HttpServletRequest req) {
		final String accessToken = accessToken(req);
		if (accessToken == null) {
			throw new IllegalStateException("Unauthorized");
		} else {
			return new Client(TALENT_SUITE_BASE_URL, Credentials.bearer(accessToken));
		}
	}

	static String csrfToken(final HttpServletRequest request) {
		return (String) request.getSession().getAttribute(ATTRIBUTE_NAME_CSRF_TOKEN);
	}

	static String requestUrl(final HttpServletRequest request) {
		return request.getRequestURL().toString() + (request.getQueryString() == null ? "" : ("?" + request.getQueryString()));
	}

	static String accessToken(final HttpServletRequest request) {
		return (String) request.getSession().getAttribute(ATTRIBUTE_NAME_ACCESS_TOKEN);
	}
}
