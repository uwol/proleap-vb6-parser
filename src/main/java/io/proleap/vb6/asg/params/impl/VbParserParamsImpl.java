/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.params.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.proleap.vb6.asg.params.VbParserParams;

public class VbParserParamsImpl implements VbParserParams {

	protected Charset charset = StandardCharsets.UTF_8;

	protected boolean ignoreSyntaxErrors;

	@Override
	public Charset getCharset() {
		return charset;
	}

	@Override
	public boolean getIgnoreSyntaxErrors() {
		return ignoreSyntaxErrors;
	}

	@Override
	public void setCharset(final Charset charset) {
		this.charset = charset;
	}

	@Override
	public void setIgnoreSyntaxErrors(final boolean ignoreSyntaxErrors) {
		this.ignoreSyntaxErrors = ignoreSyntaxErrors;
	}
}
