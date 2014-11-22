package com.netdimensions.client;

import static com.netdimensions.client.Json.parseJsonObject;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.io.ByteStreams;

public abstract class Request<T> {
	final String action;
	final Parameter[] parameters;

	private Request(final String action, final Parameter... parameters) {
		this.action = action;
		this.parameters = parameters;
	}

	abstract T result(InputStream in) throws IOException;

	public static Request<User> me() {
		return new Request<User>("me") {
			@Override
			final User result(final InputStream in) {
				return new User(parseJsonObject(in));
			}
		};
	}

	public static Request<ImmutableList<Report>> batchReports() {
		return new Request<ImmutableList<Report>>("batchReports") {
			@Override
			final ImmutableList<Report> result(final InputStream in) {
				final JSONArray arr = parseJsonObject(in).getJSONArray("reports");
				final Builder<Report> builder = ImmutableList.builder();
				for (int i = 0; i < arr.length(); i++) {
					builder.add(new Report(arr.getJSONObject(i)));
				}
				return builder.build();
			}
		};
	}

	public static Request<byte[]> batchReport(final String pathname) {
		return new Request<byte[]>("batchReport", new Parameter("pathname", pathname)) {
			@Override
			final byte[] result(final InputStream in) throws IOException {
				return ByteStreams.toByteArray(in);
			}
		};
	}
}
