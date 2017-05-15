/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.doloop;

import io.proleap.vb6.VisualBasic6Parser.DoLoopStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Repeats a block of statements while a condition is True or until a condition
 * becomes True.
 */
public interface DoLoop extends Statement {

	@Override
	DoLoopStmtContext getCtx();

}
