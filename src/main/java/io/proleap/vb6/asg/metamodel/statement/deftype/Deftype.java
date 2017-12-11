/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.deftype;

import io.proleap.vb6.VisualBasic6Parser.DeftypeStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Used at module level to set the default data type for variables, arguments
 * passed to procedures, and the return type for Function and Property Get
 * procedures whose names start with the specified characters.
 */
public interface Deftype extends Statement {

	@Override
	DeftypeStmtContext getCtx();

}
