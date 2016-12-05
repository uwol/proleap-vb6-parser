/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.whilestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.WhileWendStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.impl.ScopeImpl;
import io.proleap.vb6.parser.metamodel.statement.StatementType;
import io.proleap.vb6.parser.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.parser.metamodel.statement.whilestmt.While;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class WhileImpl extends ScopeImpl implements While {

	protected ValueStmt condition;

	protected final WhileWendStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.While;

	public WhileImpl(final Module module, final Scope scope, final WhileWendStmtContext ctx) {
		super(module, scope, ctx);

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
