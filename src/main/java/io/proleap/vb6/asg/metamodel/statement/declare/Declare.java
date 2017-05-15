/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.declare;

import io.proleap.vb6.VisualBasic6Parser.DeclareStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Used at module level to declare references to external procedures in a
 * dynamic-link library (DLL).
 */
public interface Declare extends Statement {

	@Override
	DeclareStmtContext getCtx();

}
