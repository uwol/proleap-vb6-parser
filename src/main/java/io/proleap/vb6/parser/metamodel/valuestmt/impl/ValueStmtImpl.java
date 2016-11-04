/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.impl.VbScopedElementImpl;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public abstract class ValueStmtImpl extends VbScopedElementImpl implements ValueStmt {

	protected final ValueStmtContext ctx;

	protected final List<ValueStmt> subValueStmts = new ArrayList<ValueStmt>();

	public ValueStmtImpl(final Module module, final Scope superScope, final ValueStmtContext ctx) {
		super(module, superScope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSubValueStmt(final ValueStmt valueStmt) {
		subValueStmts.add(valueStmt);
	}

	@Override
	public boolean equals(final Object obj) {
		final boolean result;

		if (super.equals(obj)) {
			result = true;
		} else if (obj instanceof ValueStmt) {
			final ValueStmt otherValueStmt = (ValueStmt) obj;

			final boolean identicalClass = obj.getClass().equals(this.getClass());

			if (!identicalClass) {
				result = false;
			} else {
				boolean equalSubValueStmts = true;

				for (int i = 0; i < subValueStmts.size(); i++) {
					final ValueStmt ownSubCall = subValueStmts.get(i);
					final ValueStmt otherSubCall = otherValueStmt.getSubValueStmts().get(i);

					equalSubValueStmts = equalSubValueStmts && ownSubCall.equals(otherSubCall);
				}

				result = equalSubValueStmts;
			}
		} else {
			result = false;
		}

		return result;
	}

	@Override
	public ValueStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<ValueStmt> getSubValueStmts() {
		return subValueStmts;
	}

	@Override
	public String toString() {
		return "type=[" + getType() + "]";
	}
}
