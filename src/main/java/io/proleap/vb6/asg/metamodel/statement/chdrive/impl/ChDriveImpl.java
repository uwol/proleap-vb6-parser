/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.chdrive.impl;

import io.proleap.vb6.VisualBasic6Parser.ChDriveStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.chdrive.ChDrive;

public class ChDriveImpl extends ScopedElementImpl implements ChDrive {

	protected final ChDriveStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.CH_DRIVE;

	public ChDriveImpl(final Module module, final Scope scope, final ChDriveStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ChDriveStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
