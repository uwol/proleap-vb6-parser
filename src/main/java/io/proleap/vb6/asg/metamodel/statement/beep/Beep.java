/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.beep;

import io.proleap.vb6.VisualBasic6Parser.BeepStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Sounds a tone through the computer's speaker.
 */
public interface Beep extends Statement {

	@Override
	BeepStmtContext getCtx();

}
