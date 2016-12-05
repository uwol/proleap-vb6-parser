/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.onerror;

import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Enables error-handling routine and specifies the location of the routine
 * within a procedure; also to be used to disable an error-handling routine.
 */
public interface OnError extends Statement {

	LineLabel getLineLabel();
}
