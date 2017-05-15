/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.close;

import io.proleap.vb6.VisualBasic6Parser.CloseStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Concludes input/output (I/O) to a file opened using the Open statement.
 */
public interface Close extends Statement {

	@Override
	CloseStmtContext getCtx();

}
