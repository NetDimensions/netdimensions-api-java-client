package com.netdimensions.client.servlet;

import java.net.URI;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.ImmutableList;

public final class OAuthFilter implements Filter {
	@Override
	public final void destroy() {
	}

	@Override
	public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final String queryString = httpRequest.getQueryString();
		final String requestUrl = httpRequest.getRequestURL().toString() + (queryString == null ? "" : ("?" + queryString));
		ImmutableList.of(new Parameter("response_type", "code"), new Parameter("client_id", "anonymous"), new Parameter("redirect_uri", URI.create(requestUrl)
				.resolve(httpRequest.getContextPath() + "/cb").toString()),
				new Parameter("state", ((String) httpRequest.getSession().getAttribute(OAuthSessionListener.ATTRIBUTE_NAME_STATE)) + "," + requestUrl));
	}

	@Override
	public final void init(final FilterConfig filterConfig) {
	}
}
