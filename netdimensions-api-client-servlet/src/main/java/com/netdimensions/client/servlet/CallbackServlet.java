package com.netdimensions.client.servlet;

import static com.google.common.base.Charsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

public final class CallbackServlet extends HttpServlet {
	private static final long serialVersionUID = -3981798664347194529L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		final State state = State.from(req.getParameter("state"));
		if (Servlets.csrfToken(req).equals(state.csrfToken)) {
			final URLConnection con = new URL(Servlets.TALENT_SUITE_BASE_URL + "servlet/ekp/token").openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			try (final OutputStream out = con.getOutputStream()) {
				out.write(Parameter.toString(new Parameter("grant_type", "authorization_code"), new Parameter("code", req.getParameter("code")),
						Parameter.redirectUri(req), Parameter.CLIENT_ID).getBytes(UTF_8));
			}
			final String response;
			try (final InputStream in = con.getInputStream()) {
				response = new String(ByteStreams.toByteArray(in), UTF_8);
			}
			resp.setContentType("text/plain");
			resp.getWriter().append(response);
		} else {
			throw new IllegalStateException("Bad CSRF token: expected " + Servlets.csrfToken(req));
		}
	}
}
