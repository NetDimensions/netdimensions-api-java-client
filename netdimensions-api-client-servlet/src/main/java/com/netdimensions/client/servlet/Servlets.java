package com.netdimensions.client.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.netdimensions.client.Client;

// Noninstantiable utility class
public final class Servlets {
	static final String ATTRIBUTE_NAME_CSRF_TOKEN = "com.netdimensions.client.servlet.CSRF_TOKEN";
	static final String REDIRECTION_URI_PATH = "/cb";
	static final String ATTRIBUTE_NAME_ACCESS_TOKEN = "com.netdimensions.client.servlet.ACCESS_TOKEN";
	static final String ATTRIBUTE_NAME_CLIENT = "com.netdimensions.client.servlet.CLIENT";

	// Suppress default constructor for noninstantiability
	private Servlets() {
		throw new AssertionError();
	}

	public static Client client(final HttpServletRequest req) {
		final Object attr = req.getAttribute(ATTRIBUTE_NAME_CLIENT);
		if (attr instanceof Client) {
			return (Client) attr;
		} else {
			throw new IllegalStateException("No authorization found; is the authorization filter mapped for the requested resource?");
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

	static String talentSuiteBaseUrl(final ServletContext servletContext) {
		return servletContext.getInitParameter("com.netdimensions.client.servlet.TALENT_SUITE_BASE_URL");
	}
}
