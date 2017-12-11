/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Executes one of several groups of statements, depending on the value of an
 * expression.
 */
public interface Select extends Scope, Statement, TypedElement {

	void addSelectCase(SelectCase selectCase);

	@Override
	SelectCaseStmtContext getCtx();

	List<SelectCase> getSelectCases();

	ValueStmt getValueStmt();

	void setValueStmt(ValueStmt valueStmt);
}
