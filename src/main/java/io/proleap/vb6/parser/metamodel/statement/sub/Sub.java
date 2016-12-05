/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.sub;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.parser.metamodel.Procedure;
import io.proleap.vb6.parser.metamodel.call.SubCall;
import io.proleap.vb6.parser.metamodel.statement.Statement;

/**
 * Declares the name, arguments, and code that form the body of a Sub procedure.
 */
public interface Sub extends Procedure, Statement {

	void addSubCall(SubCall subCall);

	@Override
	SubStmtContext getCtx();

	List<SubCall> getSubCalls();
}
