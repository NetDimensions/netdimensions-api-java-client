package com.netdimensions.client;

public final class Request<T> {
	private Request() {
	}

	public static Request<User> me() {
		return new Request<User>();
	}
}
