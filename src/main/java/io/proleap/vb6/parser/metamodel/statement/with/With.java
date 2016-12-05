/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.with;

import io.proleap.vb6.VisualBasic6Parser.WithStmtContext;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.statement.Statement;

/**
 * Executes a series of statements on a single object or a user-defined type.
 */
public interface With extends Scope, Statement {

	@Override
	WithStmtContext getCtx();

	Call getWithVariableCall();

	void setWithVariableCall(Call withVariableCall);
}
