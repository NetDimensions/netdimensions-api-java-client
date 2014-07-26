package com.netdimensions.client.servlet;

import javax.servlet.ServletRequest;

import com.netdimensions.client.Client;

// Noninstantiable utility class
public final class Servlets {
	// Suppress default constructor for noninstantiability
	private Servlets() {
		throw new AssertionError();
	}

	public static Client client(final ServletRequest req) {
		final Object client = req.getAttribute("com.netdimensions.client.servlet.CLIENT");
		if (client instanceof Client) {
			return (Client) client;
		} else {
			throw new IllegalStateException("Unauthorized");
		}
	}
}
