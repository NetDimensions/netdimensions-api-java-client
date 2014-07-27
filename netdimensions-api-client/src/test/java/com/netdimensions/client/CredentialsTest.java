package com.netdimensions.client;

import org.junit.Assert;
import org.junit.Test;

public final class CredentialsTest {
	/**
	 * http://tools.ietf.org/html/rfc2617#section-2
	 */
	@Test
	public final void testBasic() {
		Assert.assertEquals("Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==", Credentials.basic("Aladdin", "open sesame").toString());
	}
}
