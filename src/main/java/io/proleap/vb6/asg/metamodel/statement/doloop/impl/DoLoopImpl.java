/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.doloop.impl;

import io.proleap.vb6.VisualBasic6Parser.DoLoopStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.doloop.DoLoop;

public class DoLoopImpl extends ScopedElementImpl implements DoLoop {

	protected final DoLoopStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.DO_LOOP;

	public DoLoopImpl(final Module module, final Scope scope, final DoLoopStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public DoLoopStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
