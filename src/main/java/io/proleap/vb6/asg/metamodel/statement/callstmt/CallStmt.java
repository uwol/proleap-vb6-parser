/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.callstmt;

import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InBlockContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.Statement;

public interface CallStmt extends Statement, Call {

	@Override
	ImplicitCallStmt_InBlockContext getCtx();
}
