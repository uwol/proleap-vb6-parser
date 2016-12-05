/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.let.impl;

import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class LetImpl extends ScopedElementImpl implements Let {

	protected final LetStmtContext ctx;

	protected Call leftHandCall;

	protected ValueStmt rightHandValueStmt;

	protected final StatementType statementType = StatementTypeEnum.Let;

	public LetImpl(final Module module, final Scope scope, final LetStmtContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public LetStmtContext getCtx() {
		return ctx;
	}

	@Override
	public Call getLeftHandCall() {
		return leftHandCall;
	}

	@Override
	public ValueStmt getRightHandValueStmt() {
		return rightHandValueStmt;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public boolean isSettingReturnVariable() {
		return CallType.ReturnValueCall.equals(leftHandCall.getCallType());
	}

	@Override
	public void setLeftHandCall(final Call leftHandCall) {
		this.leftHandCall = leftHandCall;
	}

	@Override
	public void setRightHandValueStmt(final ValueStmt rightHandValueStmt) {
		this.rightHandValueStmt = rightHandValueStmt;
	}

	@Override
	public String toString() {
		return "leftHandCall=[" + leftHandCall + "]";
	}

}
