/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.asg.metamodel.Literal;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.type.Type;

public class LiteralImpl extends ScopedElementImpl implements Literal {

	protected final LiteralContext ctx;

	protected final Type type;

	protected final String value;

	public LiteralImpl(final Type type, final String value, final Module module, final Scope scope,
			final LiteralContext ctx) {
		super(module.getProgram(), module, scope, ctx);

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
