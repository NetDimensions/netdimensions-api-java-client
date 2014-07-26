package com.netdimensions.client.servlet;

import java.util.List;

import com.google.common.base.Splitter;

final class State {
	final String csrfToken;
	final String target;

	State(final String csrfToken, final String target) {
		this.csrfToken = csrfToken;
		this.target = target;
	}

	@Override
	public final String toString() {
		return csrfToken + "," + target;
	}

	static State from(final String string) {
		final List<String> substrings = Splitter.on(',').limit(2).splitToList(string);
		return new State(substrings.get(0), substrings.get(1));
	}
}