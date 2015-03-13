package com.netdimensions.client;

import static com.netdimensions.client.Json.parseJsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.io.ByteStreams;

public abstract class Request<T> {
	private Request() {
	}

	abstract T sendWith(final Client client) throws IOException;

	public static Request<User> me() {
		return get("me", ImmutableList.<Field> of(), new Parser<User>() {
			@Override
			final User parse(final InputStream in) {
				return new User(parseJsonObject(in));
			}
		});
	}

	public static Request<ImmutableList<User>> users(final String userId) {
		return get("users", ImmutableList.of(new Field("userId", userId)), new Parser<ImmutableList<User>>() {
			@Override
			final ImmutableList<User> parse(final InputStream in) {
				final JSONArray arr = parseJsonObject(in).getJSONArray("users");
				final Builder<User> builder = ImmutableList.builder();
				for (int i = 0; i < arr.length(); i++) {
					builder.add(new User(arr.getJSONObject(i)));
				}
				return builder.build();
			}
		});
	}

	public static Request<ImmutableList<Report>> batchReports() {
		return get("batchReports", ImmutableList.<Field> of(), new Parser<ImmutableList<Report>>() {
			@Override
			final ImmutableList<Report> parse(final InputStream in) {
				final JSONArray arr = parseJsonObject(in).getJSONArray("reports");
				final Builder<Report> builder = ImmutableList.builder();
				for (int i = 0; i < arr.length(); i++) {
					builder.add(new Report(arr.getJSONObject(i)));
				}
				return builder.build();
			}
		});
	}

	public static Request<byte[]> batchReport(final String pathname) {
		return get("batchReport", ImmutableList.of(new Field("pathname", pathname)), new Parser<byte[]>() {
			@Override
			final byte[] parse(final InputStream in) throws IOException {
				return ByteStreams.toByteArray(in);
			}
		});
	}

	private static <T> Request<T> get(final String action, final ImmutableList<Field> parameters, final Parser<T> responseEntityParser) {
		return new Request<T>() {
			final T sendWith(final Client client) throws IOException {
				final URLConnection connection = new URL(Field.url(client.talentSuiteBaseUrl + "api/" + action, parameters)).openConnection();
				connection.setRequestProperty("Authorization", client.credentials.toString());
				try (final InputStream inputStream = connection.getInputStream()) {
					return responseEntityParser.parse(inputStream);
				}
			}
		};
	}
}
