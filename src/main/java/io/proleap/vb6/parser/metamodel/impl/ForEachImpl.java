/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.parser.metamodel.ForEach;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.ScopedElement;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class ForEachImpl extends ScopeImpl implements ForEach {

	protected final ForEachStmtContext ctx;

	protected Variable elementVariable;

	protected ValueStmt in;

	public ForEachImpl(final Module module, final Scope scope, final ForEachStmtContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ForEachStmtContext getCtx() {
		return ctx;
	}

	@Override
	public Variable getElementVariable() {
		return elementVariable;
	}

	@Override
	public ValueStmt getIn() {
		return in;
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else if (elementVariable != null && elementVariable.getName().equals(name)) {
			result = new ArrayList<ScopedElement>();
			result.add(elementVariable);
		} else {
			result = super.getScopedElementsInScope(name);
		}

		return result;
	}

	@Override
	public void setElementVariable(final Variable elementVariable) {
		this.elementVariable = elementVariable;
	}

	@Override
	public void setIn(final ValueStmt in) {
		this.in = in;
	}
}
