package com.netdimensions.client.servlet;

import java.security.SecureRandom;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.google.common.io.BaseEncoding;

public final class OAuthSessionListener implements HttpSessionListener {
	@Override
	public final void sessionCreated(final HttpSessionEvent se) {
		se.getSession().setAttribute(Servlets.ATTRIBUTE_NAME_CSRF_TOKEN, BaseEncoding.base16().lowerCase().encode(randomBytes()));
	}

	@Override
	public final void sessionDestroyed(final HttpSessionEvent se) {
	}

	private static byte[] randomBytes() {
		final byte[] result = new byte[20];
		new SecureRandom().nextBytes(result);
		return result;
	}
}
