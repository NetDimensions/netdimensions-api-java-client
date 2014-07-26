package com.netdimensions.client.servlet;

import java.security.SecureRandom;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.google.common.io.BaseEncoding;

public final class OAuthSessionListener implements HttpSessionListener {
	static final String ATTRIBUTE_NAME_STATE = "com.netdimensions.client.servlet.STATE";

	@Override
	public final void sessionCreated(final HttpSessionEvent se) {
	}

	@Override
	public final void sessionDestroyed(final HttpSessionEvent se) {
		se.getSession().setAttribute(ATTRIBUTE_NAME_STATE, BaseEncoding.base16().lowerCase().encode(randomBytes()));
	}

	private static byte[] randomBytes() {
		final byte[] result = new byte[20];
		new SecureRandom().nextBytes(result);
		return result;
	}
}
