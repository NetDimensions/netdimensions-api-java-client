package com.netdimensions.client.reports;

import java.io.File;
import java.io.IOException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import com.netdimensions.client.Client;
import com.netdimensions.client.Credentials;
import com.netdimensions.client.Report;
import com.netdimensions.client.Request;

public final class BatchReportDownloader {
	public static void main(final String[] args) throws IOException {
		final Client client = new Client(args[0], Credentials.basic(args[1], args[2]));
		final ImmutableList<Report> reports = client.send(Request.batchReports());
		for (final Report report : args.length > 4 ? Iterables.limit(reports, Integer.parseInt(args[4])) : reports) {
			final String pathname = report.pathname();
			Files.asByteSink(new File(args[3], pathname.substring(pathname.lastIndexOf('\\') + 1))).write(client.send(Request.batchReport(pathname)));
		}
	}
}
