package com.netdimensions.client;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Iterables;

public final class UserRecord {
	private final String userId;
	private final String password;
	private final String familyName;
	private final String givenName;

	public UserRecord(final String userId, final String password, final String familyName, final String givenName) {
		this.userId = userId;
		this.password = password;
		this.familyName = familyName;
		this.givenName = givenName;
	}

	final String toCsvString(final String action) {
		return UserRecord.encodeCsvFields(Optional.presentInstances(ImmutableList.of(Optional.of(new Field("Action", action)),
				Optional.of(new Field("UserId", userId)), Field.fromNullableValue("Password", password), Field.fromNullableValue("FamilyName", familyName),
				Field.fromNullableValue("GivenName", givenName))));
	}

	private static String encodeCsvFields(final Iterable<Field> fields) {
		final Builder<String> names = ImmutableList.builder();
		final Builder<String> values = ImmutableList.builder();
		for (final Field field : fields) {
			names.add(field.name);
			values.add(field.value);
		}
		return UserRecord.encodeCsvValues(names.build()) + UserRecord.encodeCsvValues(values.build());
	}

	private static String encodeCsvValues(final Iterable<String> values) {
		final StringBuilder result = new StringBuilder();
		if (!Iterables.isEmpty(values)) {
			result.append(UserRecord.encodeCsv(values.iterator().next()));
			for (final String s : Iterables.skip(values, 1)) {
				result.append(',');
				result.append(UserRecord.encodeCsv(s));
			}
		}
		return result.append("\r\n").toString();
	}

	private static String encodeCsv(final String s) {
		return "\"" + s.replace("\"", "\"\"") + "\"";
	}
}