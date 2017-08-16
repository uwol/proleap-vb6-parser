/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.explicitcallstmt;

import io.proleap.vb6.VisualBasic6Parser.ExplicitCallStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.Statement;

public interface ExplicitCallStmt extends Statement, Call {

	@Override
	ExplicitCallStmtContext getCtx();
}
