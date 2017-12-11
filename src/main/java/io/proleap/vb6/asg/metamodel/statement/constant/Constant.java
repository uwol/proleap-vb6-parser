/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.constant;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.asg.metamodel.Declaration;
import io.proleap.vb6.asg.metamodel.VisibilityElement;
import io.proleap.vb6.asg.metamodel.call.ConstantCall;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Declares constants for use in place of literal values.
 */
public interface Constant extends TypedElement, Declaration, VisibilityElement, Statement {

	void addConstantCall(ConstantCall constantCall);

	List<ConstantCall> getConstantCalls();

	@Override
	ConstSubStmtContext getCtx();

	ValueStmt getValueStmt();

	boolean isModuleConstant();

	void setValueStmt(ValueStmt value);
}
