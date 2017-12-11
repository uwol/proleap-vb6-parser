/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.params;

import java.nio.charset.Charset;

public interface VbParserParams {

	/**
	 * @see https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
	 */
	Charset getCharset();

	boolean getIgnoreSyntaxErrors();

	void setCharset(Charset charset);

	void setIgnoreSyntaxErrors(boolean ignoreSyntaxErrors);
}
