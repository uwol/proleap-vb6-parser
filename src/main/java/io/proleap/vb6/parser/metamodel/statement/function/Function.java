/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.function;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.parser.metamodel.Procedure;
import io.proleap.vb6.parser.metamodel.call.FunctionCall;
import io.proleap.vb6.parser.metamodel.statement.Statement;
import io.proleap.vb6.parser.metamodel.type.AssignableTypedElement;

/**
 * Declares the name, arguments, and code that form the body of a Function
 * procedure.
 */
public interface Function extends Procedure, Statement, AssignableTypedElement {

	void addFunctionCall(FunctionCall functionCall);

	@Override
	FunctionStmtContext getCtx();

	List<FunctionCall> getFunctionCalls();

	boolean isDeclaredAsArray();

	void setDeclaredAsArray(boolean isArray);
}
