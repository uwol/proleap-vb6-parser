/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
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
