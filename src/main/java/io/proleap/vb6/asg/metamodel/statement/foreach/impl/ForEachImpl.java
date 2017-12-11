/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.foreach.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.impl.ScopeImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ForEachImpl extends ScopeImpl implements ForEach {

	protected final ForEachStmtContext ctx;

	protected ElementVariable elementVariable;

	protected ValueStmt in;

	protected final StatementType statementType = StatementTypeEnum.FOR_EACH;

	public ForEachImpl(final Module module, final Scope scope, final ForEachStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ForEachStmtContext getCtx() {
		return ctx;
	}

	@Override
	public ElementVariable getElementVariable() {
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
		} else if (elementVariable == null) {
			result = super.getScopedElementsInScope(name);
		} else {
			final boolean sameName = elementVariable.getName().toLowerCase().equals(name.toLowerCase());

			if (!sameName) {
				result = super.getScopedElementsInScope(name);
			} else {
				result = new ArrayList<ScopedElement>();
				result.add(elementVariable);
			}
		}

		return result;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setElementVariable(final ElementVariable elementVariable) {
		this.elementVariable = elementVariable;
	}

	@Override
	public void setIn(final ValueStmt in) {
		this.in = in;
	}
}
