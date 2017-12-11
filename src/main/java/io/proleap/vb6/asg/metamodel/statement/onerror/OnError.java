/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
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
