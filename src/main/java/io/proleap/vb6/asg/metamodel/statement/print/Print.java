/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.print;

import io.proleap.vb6.VisualBasic6Parser.PrintStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Writes display-formatted data to a sequential file.
 */
public interface Print extends Statement {

	@Override
	PrintStmtContext getCtx();

}
