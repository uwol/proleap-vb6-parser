/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.parser.metamodel.call.FunctionCall;
import io.proleap.vb6.parser.metamodel.oop.AssignableTypedElement;

public interface Function extends Procedure, AssignableTypedElement {

	void addFunctionCall(FunctionCall functionCall);

	@Override
	FunctionStmtContext getCtx();

	List<FunctionCall> getFunctionCalls();

	boolean isDeclaredAsArray();

	void setDeclaredAsArray(boolean isArray);
}
