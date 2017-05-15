/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
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
