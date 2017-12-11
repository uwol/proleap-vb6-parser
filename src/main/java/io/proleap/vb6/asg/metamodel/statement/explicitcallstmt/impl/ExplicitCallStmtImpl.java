/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.explicitcallstmt.impl;

import io.proleap.vb6.VisualBasic6Parser.ExplicitCallStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.impl.CallDelegateImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.explicitcallstmt.ExplicitCallStmt;

public class ExplicitCallStmtImpl extends CallDelegateImpl implements ExplicitCallStmt {

	protected final ExplicitCallStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.EXPLICIT_CALL;

	public ExplicitCallStmtImpl(final Call delegate, final Module module, final Scope scope,
			final ExplicitCallStmtContext ctx) {
		super(delegate, module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ExplicitCallStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
