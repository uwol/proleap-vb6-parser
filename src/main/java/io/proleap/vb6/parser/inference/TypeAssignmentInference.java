/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.inference;

import io.proleap.vb6.VisualBasic6Parser;

public interface TypeAssignmentInference {

	void addTypeAssignment(VisualBasic6Parser.ArgCallContext ctx);

	void addTypeAssignment(VisualBasic6Parser.ForNextStmtContext ctx);

	void addTypeAssignment(VisualBasic6Parser.LetStmtContext ctx);

	void addTypeAssignment(VisualBasic6Parser.RedimSubStmtContext ctx);

	void addTypeAssignment(VisualBasic6Parser.SetStmtContext ctx);

	void addTypeAssignment(VisualBasic6Parser.VsAssignContext ctx);
}
