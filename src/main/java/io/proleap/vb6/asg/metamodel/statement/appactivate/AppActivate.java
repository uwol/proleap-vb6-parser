/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.appactivate;

import io.proleap.vb6.VisualBasic6Parser.AppActivateStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Activates an application window.
 */
public interface AppActivate extends Statement {

	@Override
	AppActivateStmtContext getCtx();

}
