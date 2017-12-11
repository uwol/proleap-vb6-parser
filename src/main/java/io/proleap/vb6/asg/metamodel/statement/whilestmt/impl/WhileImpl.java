/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.whilestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.WhileWendStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopeImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.whilestmt.While;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class WhileImpl extends ScopeImpl implements While {

	protected ValueStmt condition;

	protected final WhileWendStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.WHILE;

	public WhileImpl(final Module module, final Scope scope, final WhileWendStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getCondition() {
		return condition;
	}

	@Override
	public WhileWendStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setCondition(final ValueStmt condition) {
		this.condition = condition;
	}

}
