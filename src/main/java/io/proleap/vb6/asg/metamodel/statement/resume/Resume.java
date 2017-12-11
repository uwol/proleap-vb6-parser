/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.resume;

import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Resumes execution after an error-handling routine is finished.
 */
public interface Resume extends Statement {

	LineLabel getLineLabel();
}
