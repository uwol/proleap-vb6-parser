/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.exit.impl;

import io.proleap.vb6.VisualBasic6Parser.ExitStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.parser.metamodel.statement.StatementType;
import io.proleap.vb6.parser.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.parser.metamodel.statement.exit.Exit;

public class ExitImpl extends ScopedElementImpl implements Exit {

	protected final ExitStmtContext ctx;

	protected final ExitType exitType;

	protected final StatementType statementType = StatementTypeEnum.Exit;

	public ExitImpl(final ExitType exitType, final Module module, final Scope scope, final ExitStmtContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
		this.exitType = exitType;
	}

	@Override
	public ExitStmtContext getCtx() {
		return ctx;
	}

	@Override
	public ExitType getExitType() {
		return exitType;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
