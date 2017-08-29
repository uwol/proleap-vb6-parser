/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.foreach.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.impl.ScopeImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ForEachImpl extends ScopeImpl implements ForEach {

	protected final ForEachStmtContext ctx;

	protected Call elementCall;

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
	public Call getElementCall() {
		return elementCall;
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
		} else if (elementCall == null) {
			result = super.getScopedElementsInScope(name);
		} else {
			final Call unwrappedCall = elementCall.unwrap();
			final CallType callType = unwrappedCall.getCallType();

			if (!CallType.VARIABLE_CALL.equals(callType)) {
				result = super.getScopedElementsInScope(name);
			} else {
				final VariableCall variableCall = (VariableCall) unwrappedCall;
				final Variable variable = variableCall.getVariable();
				final boolean sameName = variable.getName().toLowerCase().equals(name.toLowerCase());

				if (!sameName) {
					result = super.getScopedElementsInScope(name);
				} else {
					result = new ArrayList<ScopedElement>();
					result.add(variable);
				}
			}
		}

		return result;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setElementCall(final Call elementCall) {
		this.elementCall = elementCall;
	}

	@Override
	public void setIn(final ValueStmt in) {
		this.in = in;
	}
}
