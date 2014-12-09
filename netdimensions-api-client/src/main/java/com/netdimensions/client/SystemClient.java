package com.netdimensions.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Iterables;
import com.google.common.net.MediaType;

public final class SystemClient {
	private final String baseUrl;
	private final Credentials credentials;

	public SystemClient(final String baseUrl, final String adminUserId, final String key) {
		this.baseUrl = baseUrl;
		this.credentials = Credentials.basic(adminUserId, key);
	}

	public static void main(final String[] args) throws IOException {
		final String baseUrl = args[0];
		final String adminUserId = args[1];
		final String key = args[2];
		final String userId = args[3];
		final String newPassword = args[4];
		new SystemClient(baseUrl, adminUserId, key).send(SystemRequest.updateUser(userId, newPassword));
	}

	public final void send(final SystemRequest req) throws IOException {
		final URLConnection con = new URL(baseUrl + "contentHandler/usersCsv").openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Authorization", credentials.toString());
		con.setRequestProperty("Content-Type", MediaType.CSV_UTF_8.toString());
		try (final OutputStream out = con.getOutputStream()) {
			out.write(encodeCsvFields(new Field("Action", "U"), new Field("UserId", req.userId), new Field("Password", req.password)).getBytes(Charsets.UTF_8));
			out.flush();
		}
		try (final InputStream in = con.getInputStream()) {
		}
	}

	/**
	 * @deprecated Use {@link #send(SystemRequest)} with {@link SystemRequest#updateUser(String, String)} instead.
	 */
	@Deprecated
	public final void updateUser(final String userId, final String password) throws IOException {
		send(SystemRequest.updateUser(userId, password));
	}

	private static String encodeCsvFields(final Field... fields) {
		final Builder<String> names = ImmutableList.builder();
		final Builder<String> values = ImmutableList.builder();
		for (final Field field : fields) {
			names.add(field.name);
			values.add(field.value);
		}
		return encodeCsvValues(names.build()) + encodeCsvValues(values.build());
	}

	private static String encodeCsvValues(final Iterable<String> values) {
		final StringBuilder result = new StringBuilder();
		if (!Iterables.isEmpty(values)) {
			result.append(encodeCsv(values.iterator().next()));
			for (final String s : Iterables.skip(values, 1)) {
				result.append(',');
				result.append(encodeCsv(s));
			}
		}
		return result.append("\r\n").toString();
	}

	private static String encodeCsv(final String s) {
		return "\"" + s.replace("\"", "\"\"") + "\"";
	}
}
