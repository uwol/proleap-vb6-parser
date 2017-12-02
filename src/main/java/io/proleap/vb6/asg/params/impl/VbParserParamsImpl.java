/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.params.impl;

import java.nio.charset.Charset;

import io.proleap.vb6.asg.params.VbParserParams;

public class VbParserParamsImpl implements VbParserParams {

	protected Charset charset = Charset.defaultCharset();

	@Override
	public Charset getCharset() {
		return charset;
	}

	@Override
	public void setCharset(final Charset charset) {
		this.charset = charset;
	}
}
