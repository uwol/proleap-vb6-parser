/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.whilestmt;

import io.proleap.vb6.VisualBasic6Parser.WhileWendStmtContext;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Executes a series of statements as long as a given condition is True.
 */
public interface While extends Scope, Statement {

	ValueStmt getCondition();

	@Override
	WhileWendStmtContext getCtx();

	void setCondition(ValueStmt condition);

}
