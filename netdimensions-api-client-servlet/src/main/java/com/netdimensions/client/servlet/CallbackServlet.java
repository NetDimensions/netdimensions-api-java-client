package com.netdimensions.client.servlet;

import static com.google.common.base.Charsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.common.collect.ImmutableList;

public final class CallbackServlet extends HttpServlet {
	private static final long serialVersionUID = -3981798664347194529L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		final State state = State.from(req.getParameter("state"));
		if (Servlets.csrfToken(req).equals(state.csrfToken)) {
			req.getSession().setAttribute(
					Servlets.ATTRIBUTE_NAME_ACCESS_TOKEN,
					post(
							Servlets.talentSuiteBaseUrl(getServletContext()) + "servlet/ekp/token",
							ImmutableList.of(new Parameter("grant_type", "authorization_code"), new Parameter("code", req.getParameter("code")),
									Parameter.redirectUri(req), Parameter.CLIENT_ID)).getString("access_token"));
			resp.sendRedirect(state.target);
		} else {
			throw new IllegalStateException("Bad CSRF token: expected " + Servlets.csrfToken(req));
		}
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
}
