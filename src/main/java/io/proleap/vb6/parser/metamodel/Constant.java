/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.parser.metamodel.call.ConstantCall;
import io.proleap.vb6.parser.metamodel.type.TypedElement;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

/**
 * Declares constants for use in place of literal values.
 */
public interface Constant extends TypedElement, Declaration, Statement {

	void addConstantCall(ConstantCall constantCall);

	List<ConstantCall> getConstantCalls();

	@Override
	ConstSubStmtContext getCtx();

	ValueStmt getValueStmt();

	boolean isModuleConstant();

	void setValueStmt(ValueStmt value);
}
