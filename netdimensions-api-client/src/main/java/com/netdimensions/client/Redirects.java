package com.netdimensions.client;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

// Noninstantiable utility class
public final class Redirects {
	// Suppress default constructor for noninstantiability
	private Redirects() {
		throw new AssertionError();
	}

	public static final String checkoutUrl(final String baseUrl, final Iterable<String> itemIds, final String onSuccess, final String onFailure) {
		return Field.url(
				baseUrl + "servlet/ekp/checkout",
				Optional.presentInstances(ImmutableList.of(Optional.of(new Field("cart", Joiner.on(' ').join(itemIds))),
						Field.fromNullableValue("onSuccess", onSuccess), Field.fromNullableValue("onFailure", onFailure))));
	}
}
