/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public interface Set extends VbScopedElement {

	@Override
	SetStmtContext getCtx();

	Call getLeftHandCall();

	ValueStmt getRightHandValueStmt();

	boolean isSettingReturnVariable();

	void setLeftHandCall(Call leftHandCall);

	void setRightHandValueStmt(ValueStmt rightHandValueStmt);

}
