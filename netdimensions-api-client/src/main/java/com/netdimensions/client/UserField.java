package com.netdimensions.client;

public final class UserField {
	final Field field;

	private UserField(final Field field) {
		this.field = field;
	}

	public static UserField password(final String value) {
		return new UserField(new Field("Password", value));
	}

	public static UserField familyName(final String value) {
		return new UserField(new Field("FamilyName", value));
	}

	public static UserField givenName(final String value) {
		return new UserField(new Field("GivenName", value));
	}

	public static UserField status(final UserStatus value) {
		return new UserField(new Field("Status", value.code));
	}

	public static UserField jobTitle(final String value) {
		return new UserField(new Field("Job Title", value));
	}

	public static UserField attributeExtension(final String name, final String value) {
		return new UserField(new Field("UA-" + name, value));
	}
}
