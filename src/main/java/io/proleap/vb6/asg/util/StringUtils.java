package io.proleap.vb6.asg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

	public static List<String> readLines(final InputStream inputStream, final Charset charset) throws IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.lines().collect(Collectors.toList());
	}

	public static String toString(final InputStream inputStream, final Charset charset) throws IOException {
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
	}
}
