/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.function;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.call.FunctionCall;
import io.proleap.vb6.asg.metamodel.type.AssignableTypedElement;

/**
 * Declares the name, arguments, and code that form the body of a Function
 * procedure.
 */
public interface Function extends Procedure, AssignableTypedElement {

	void addFunctionCall(FunctionCall functionCall);

	@Override
	FunctionStmtContext getCtx();

	List<FunctionCall> getFunctionCalls();

	boolean isDeclaredAsArray();

	void setDeclaredAsArray(boolean isArray);
}
