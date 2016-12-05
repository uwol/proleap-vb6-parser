/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.fornext;

import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Repeats a group of statements a specified number of times.
 */
public interface ForNext extends Scope, Statement {

	@Override
	ForNextStmtContext getCtx();

	ValueStmt getFrom();

	Variable getIteratorVariable();

	ValueStmt getStep();

	ValueStmt getTo();

	void setFrom(ValueStmt from);

	void setIteratorVariable(Variable variable);

	void setStep(ValueStmt step);

	void setTo(ValueStmt to);
}
