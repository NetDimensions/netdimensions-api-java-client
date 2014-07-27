package com.netdimensions.client.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.ImmutableList;
import com.netdimensions.client.Client;
import com.netdimensions.client.Credentials;

public final class OAuthFilter implements Filter {
	private FilterConfig filterConfig;

	@Override
	public final void destroy() {
		filterConfig = null;
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final String accessToken = Servlets.accessToken(httpRequest);
		if (accessToken != null) {
			chain.doFilter(new HttpServletRequestWrapper(httpRequest) {
				@Override
				public final Object getAttribute(final String name) {
					return Servlets.ATTRIBUTE_NAME_CLIENT.equals(name) ? new Client(Servlets.talentSuiteBaseUrl(filterConfig.getServletContext()), Credentials
							.bearer(accessToken)) : super.getAttribute(name);
				}
			}, response);
		} else if (Servlets.REDIRECTION_URI_PATH.equals(httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()))) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(Servlets.talentSuiteBaseUrl(filterConfig.getServletContext())
					+ "servlet/ekp/authorize?"
					+ Parameter.toString(ImmutableList.of(new Parameter("response_type", "code"), Parameter.CLIENT_ID, Parameter.redirectUri(httpRequest),
							new Parameter("state", new State(Servlets.csrfToken(httpRequest), Servlets.requestUrl(httpRequest)).toString()))));
		}
	}

	@Override
	public final void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
