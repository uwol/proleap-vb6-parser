/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.event;

import io.proleap.vb6.VisualBasic6Parser.EventStmtContext;
import io.proleap.vb6.asg.metamodel.VisibilityElement;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Declares a user-defined event.
 */
public interface Event extends VisibilityElement, Statement {

	@Override
	EventStmtContext getCtx();

}
