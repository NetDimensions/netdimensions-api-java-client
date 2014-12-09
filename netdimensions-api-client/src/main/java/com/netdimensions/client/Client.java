package com.netdimensions.client;

import java.io.IOException;

public final class Client {
	final String talentSuiteBaseUrl;
	final Credentials credentials;

	public Client(final String talentSuiteBaseUrl, final Credentials credentials) {
		this.talentSuiteBaseUrl = talentSuiteBaseUrl;
		this.credentials = credentials;
	}

	public final <T> T send(final Request<T> req) throws IOException {
		return req.sendWith(this);
	}
}
