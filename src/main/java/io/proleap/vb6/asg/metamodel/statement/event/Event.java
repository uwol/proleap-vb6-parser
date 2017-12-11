/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.event;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.EventStmtContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.VisibilityElement;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Declares a user-defined event.
 */
public interface Event extends VisibilityElement, Statement {

	Arg addArg(ArgContext ctx);

	@Override
	EventStmtContext getCtx();
}
