/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.open;

import io.proleap.vb6.VisualBasic6Parser.OpenStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Enables input/output (I/O) to a file.
 */
public interface Open extends Statement {

	@Override
	OpenStmtContext getCtx();

}
