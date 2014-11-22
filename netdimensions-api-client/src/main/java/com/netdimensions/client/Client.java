package com.netdimensions.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.common.base.Joiner;

public final class Client {
	private final String talentSuiteBaseUrl;
	private final Credentials credentials;

	public Client(final String talentSuiteBaseUrl, final Credentials credentials) {
		this.talentSuiteBaseUrl = talentSuiteBaseUrl;
		this.credentials = credentials;
	}

	public final <T> T send(final Request<T> req) throws IOException {
		final URLConnection connection = new URL(talentSuiteBaseUrl + "api/" + req.action + "?" + Joiner.on('&').join(req.parameters)).openConnection();
		connection.setRequestProperty("Authorization", credentials.toString());
		try (final InputStream inputStream = connection.getInputStream()) {
			return req.result(inputStream);
		}
	}
}
