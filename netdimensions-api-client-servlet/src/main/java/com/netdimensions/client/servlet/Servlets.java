package com.netdimensions.client.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.netdimensions.client.Client;

// Noninstantiable utility class
public final class Servlets {
	static final String ATTRIBUTE_NAME_CSRF_TOKEN = "com.netdimensions.client.servlet.CSRF_TOKEN";
	static final String TALENT_SUITE_BASE_URL = "https://preview.netdimensions.com/preview/";
	static final String REDIRECTION_URI_PATH = "/cb";

	// Suppress default constructor for noninstantiability
	private Servlets() {
		throw new AssertionError();
	}

	public static Client client(final ServletRequest req) {
		final Object client = req.getAttribute("com.netdimensions.client.servlet.CLIENT");
		if (client instanceof Client) {
			return (Client) client;
		} else {
			throw new IllegalStateException("Unauthorized");
		}
	}

	static String csrfToken(final HttpServletRequest request) {
		return (String) request.getSession().getAttribute(ATTRIBUTE_NAME_CSRF_TOKEN);
	}

	static String requestUrl(final HttpServletRequest request) {
		return request.getRequestURL().toString() + (request.getQueryString() == null ? "" : ("?" + request.getQueryString()));
	}
}
