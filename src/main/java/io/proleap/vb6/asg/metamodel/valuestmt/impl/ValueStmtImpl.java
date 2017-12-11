/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public abstract class ValueStmtImpl extends ScopedElementImpl implements ValueStmt {

	protected final ValueStmtContext ctx;

	protected final List<ValueStmt> subValueStmts = new ArrayList<ValueStmt>();

	public ValueStmtImpl(final Module module, final Scope scope, final ValueStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

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
