/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.close.impl;

import io.proleap.vb6.VisualBasic6Parser.CloseStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.close.Close;

public class CloseImpl extends ScopedElementImpl implements Close {

	protected final CloseStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.CLOSE;

	public CloseImpl(final Module module, final Scope scope, final CloseStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public CloseStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
