package com.netdimensions.client.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class OAuthFilter implements Filter {
	@Override
	public final void destroy() {
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (Servlets.REDIRECTION_URI_PATH.equals(httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()))) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(Servlets.TALENT_SUITE_BASE_URL
					+ "servlet/ekp/authorize?"
					+ Parameter.toString(new Parameter("response_type", "code"), Parameter.CLIENT_ID, Parameter.redirectUri(httpRequest), new Parameter(
							"state", new State(Servlets.csrfToken(httpRequest), Servlets.requestUrl(httpRequest)).toString())));
		}
	}

	@Override
	public final void init(final FilterConfig filterConfig) {
	}
}
