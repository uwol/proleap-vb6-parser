/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.with.impl;

import io.proleap.vb6.VisualBasic6Parser.WithStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.impl.ScopeImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.with.With;

public class WithImpl extends ScopeImpl implements With {

	protected final WithStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.With;

	protected Call withVariableCall;

	public WithImpl(final Module module, final Scope scope, final WithStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public WithStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Call getWithVariableCall() {
		return withVariableCall;
	}

	@Override
	public void setWithVariableCall(final Call withVariableCall) {
		this.withVariableCall = withVariableCall;
	}

}
