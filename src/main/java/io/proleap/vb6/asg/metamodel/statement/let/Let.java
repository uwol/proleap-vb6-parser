/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.let;

import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

/**
 * Assigns the value of an expression to a variable or property.
 */
public interface Let extends Statement {

	@Override
	LetStmtContext getCtx();

	Call getLeftHandCall();

	ValueStmt getRightHandValueStmt();

	boolean isSettingReturnVariable();

	void setLeftHandCall(Call leftHandCall);

	void setRightHandValueStmt(ValueStmt rightHandValueStmt);

}
