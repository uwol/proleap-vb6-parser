/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.chdir;

import io.proleap.vb6.VisualBasic6Parser.ChDirStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Changes the current directory or folder.
 */
public interface ChDir extends Statement {

	@Override
	ChDirStmtContext getCtx();

}
