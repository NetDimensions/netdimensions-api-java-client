package com.netdimensions.client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableList.Builder;

public final class UserRecord {
	private final String userId;
	private final UserField[] fields;

	public UserRecord(final String userId, final UserField... fields) {
		this.userId = userId;
		this.fields = fields;
	}

	final String toCsvString(final String action) {
		final Builder<Field> builder = ImmutableList.<Field> builder();
		for (final UserField f : fields) {
			builder.add(f.field);
		}
		return Field.encodeCsvFields(Iterables.concat(ImmutableList.of(new Field("Action", action), new Field("UserId", userId)), builder.build()));
	}
}