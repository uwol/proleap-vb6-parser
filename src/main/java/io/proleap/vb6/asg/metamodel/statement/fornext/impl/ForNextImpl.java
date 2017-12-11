/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.fornext.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
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
import io.proleap.vb6.asg.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ForNextImpl extends ScopeImpl implements ForNext {

	protected Call counterCall;

	protected final ForNextStmtContext ctx;

	protected ValueStmt from;

	protected final StatementType statementType = StatementTypeEnum.FOR_NEXT;

	protected ValueStmt step;

	protected ValueStmt to;

	public ForNextImpl(final Module module, final Scope scope, final ForNextStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getCounterCall() {
		return counterCall;
	}

	@Override
	public ForNextStmtContext getCtx() {
		return ctx;
	}

	@Override
	public ValueStmt getFrom() {
		return from;
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else if (counterCall == null) {
			result = super.getScopedElementsInScope(name);
		} else {
			final Call unwrappedCall = counterCall.unwrap();
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
	public ValueStmt getStep() {
		return step;
	}

	@Override
	public ValueStmt getTo() {
		return to;
	}

	@Override
	public void setCounterCall(final Call counterCall) {
		this.counterCall = counterCall;
	}

	@Override
	public void setFrom(final ValueStmt from) {
		this.from = from;
	}

	@Override
	public void setStep(final ValueStmt step) {
		this.step = step;
	}

	@Override
	public void setTo(final ValueStmt to) {
		this.to = to;
	}
}
