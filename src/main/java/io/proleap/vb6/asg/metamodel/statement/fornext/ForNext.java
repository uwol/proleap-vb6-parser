/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.fornext;

import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Repeats a group of statements a specified number of times.
 */
public interface ForNext extends Scope, Statement {

	Call getCounterCall();

	@Override
	ForNextStmtContext getCtx();

	ValueStmt getFrom();

	ValueStmt getStep();

	ValueStmt getTo();

	void setCounterCall(Call counterCall);

	void setFrom(ValueStmt from);

	void setStep(ValueStmt step);

	void setTo(ValueStmt to);
}
