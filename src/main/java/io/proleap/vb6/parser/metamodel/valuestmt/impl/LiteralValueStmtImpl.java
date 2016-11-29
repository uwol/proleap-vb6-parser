/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.VsLiteralContext;
import io.proleap.vb6.parser.metamodel.Literal;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.type.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.LiteralValueStmt;

public class LiteralValueStmtImpl extends ValueStmtImpl implements LiteralValueStmt {

	protected Literal literal;

	public LiteralValueStmtImpl(final Module module, final Scope scope, final VsLiteralContext ctx) {
		super(module, scope, ctx);
	}

	@Override
	public Literal getLiteral() {
		return literal;
	}

	@Override
	public Type getType() {
		return literal.getType();
	}

	@Override
	public Literal getValue() {
		return literal;
	}

	@Override
	public void setLiteral(final Literal literal) {
		this.literal = literal;
	}

	@Override
	public String toString() {
		return literal.toString();
	}
}
