/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.parser.metamodel.Let;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.call.Call.CallType;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class LetImpl extends ScopedElementImpl implements Let {

	protected final LetStmtContext ctx;

	protected Call leftHandCall;

	protected ValueStmt rightHandValueStmt;

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
