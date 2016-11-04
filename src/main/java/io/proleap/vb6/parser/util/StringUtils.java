/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.util;

public class StringUtils {

	public static boolean geq(final Character ch1, final Character ch2) {
		return Character.toLowerCase(ch1) >= Character.toLowerCase(ch2);
	}

	public static boolean isDouble(final String str) {
		try {
			final double d = Double.parseDouble(str);
		} catch (final NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(final String str) {
		try {
			final int i = Integer.parseInt(str);
		} catch (final NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean leq(final Character ch1, final Character ch2) {
		final boolean result;

		if (ch1 == null) {
			result = false;
		} else if (ch2 == null) {
			result = false;
		} else {
			result = Character.toLowerCase(ch1) <= Character.toLowerCase(ch2);
		}

		return result;
	}

	public static Double parseDouble(final String str) {
		try {
			final Double d = Double.parseDouble(str);
			return d;
		} catch (final NumberFormatException nfe) {
			return null;
		}
	}

	public static Integer parseInteger(final String str) {
		try {
			final Integer i = Integer.parseInt(str);
			return i;
		} catch (final NumberFormatException nfe) {
			return null;
		}
	}
}
