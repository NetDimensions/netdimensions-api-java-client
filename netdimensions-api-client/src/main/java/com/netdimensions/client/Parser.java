package com.netdimensions.client;

import java.io.IOException;
import java.io.InputStream;

abstract class Parser<T> {
	abstract T parse(InputStream in) throws IOException;
}
