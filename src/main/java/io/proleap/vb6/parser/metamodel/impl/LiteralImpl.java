/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.parser.metamodel.Literal;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.type.Type;

public class LiteralImpl extends ScopedElementImpl implements Literal {

	protected final LiteralContext ctx;

	protected final Type type;

	protected final String value;

	public LiteralImpl(final Type type, final String value, final Module module, final Scope scope,
			final LiteralContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
		this.type = type;
		this.value = value;
	}

	@Override
	public LiteralContext getCtx() {
		return ctx;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=[" + value + "]";
	}
}
