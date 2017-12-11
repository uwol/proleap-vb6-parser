/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
