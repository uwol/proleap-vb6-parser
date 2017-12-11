/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.date;

import io.proleap.vb6.VisualBasic6Parser.DateStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Sets the current system date.
 */
public interface Date extends Statement {

	@Override
	DateStmtContext getCtx();

}
