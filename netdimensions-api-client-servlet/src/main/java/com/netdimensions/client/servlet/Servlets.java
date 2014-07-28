package com.netdimensions.client.servlet;

import static com.google.common.base.Charsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.common.collect.ImmutableList;
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

	private static JSONObject post(final String url, final Iterable<Parameter> parameters) throws IOException, MalformedURLException {
		final URLConnection con = new URL(url).openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		try (final OutputStream out = con.getOutputStream()) {
			out.write(Parameter.toString(parameters).getBytes(UTF_8));
		}
		try (final InputStream inputStream = con.getInputStream()) {
			return new JSONObject(new JSONTokener(inputStream));
		}
	}

	static void handleRedirect(final ServletContext servletContext, final HttpServletRequest request, final HttpServletResponse response) throws IOException,
			MalformedURLException {
		final State state = State.from(request.getParameter("state"));
		if (csrfToken(request).equals(state.csrfToken)) {
			request.getSession().setAttribute(
					ATTRIBUTE_NAME_ACCESS_TOKEN,
					post(
							talentSuiteBaseUrl(servletContext) + "servlet/ekp/token",
							ImmutableList.of(new Parameter("grant_type", "authorization_code"), new Parameter("code", request.getParameter("code")),
									Parameter.redirectUri(request), Parameter.CLIENT_ID)).getString("access_token"));
			response.sendRedirect(state.target);
		} else {
			throw new IllegalStateException("Bad CSRF token: expected " + csrfToken(request));
		}
	}
}
