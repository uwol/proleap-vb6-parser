/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.exit;

import io.proleap.vb6.VisualBasic6Parser.ExitStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Exits a block of DoLoop, For...Next, Function, Sub, or Property code.
 */
public interface Exit extends Statement {

	public enum ExitType {
		Do, For, Function, Property, Sub;
	}

	@Override
	ExitStmtContext getCtx();

	ExitType getExitType();
}
