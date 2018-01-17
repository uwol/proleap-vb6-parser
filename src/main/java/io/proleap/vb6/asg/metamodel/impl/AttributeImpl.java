/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.asg.metamodel.Attribute;
import io.proleap.vb6.asg.metamodel.Literal;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.type.Type;

public class AttributeImpl extends ScopedElementImpl implements Attribute {

	protected final AttributeStmtContext ctx;

	protected Literal literal;

	protected final String name;

	protected final Type type;

	public AttributeImpl(final String name, final Type type, final Module module, final Scope scope,
			final AttributeStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.name = name;
		this.type = type;
	}

	@Override
	public AttributeStmtContext getCtx() {
		return ctx;
	}

	@Override
	public Literal getLiteral() {
		return literal;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setLiteral(final Literal literal) {
		this.literal = literal;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
