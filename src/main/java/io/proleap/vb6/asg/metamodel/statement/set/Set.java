/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.set;

import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Assigns an object reference to a variable or property.
 */
public interface Set extends Statement {

	@Override
	SetStmtContext getCtx();

	Call getLeftHandCall();

	ValueStmt getRightHandValueStmt();

	boolean isSettingReturnVariable();

	void setLeftHandCall(Call leftHandCall);

	void setRightHandValueStmt(ValueStmt rightHandValueStmt);

}
