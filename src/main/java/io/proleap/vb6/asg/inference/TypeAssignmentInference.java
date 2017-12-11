/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.inference;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.asg.metamodel.Program;

public interface TypeAssignmentInference {

	void addTypeAssignment(ArgCallContext ctx, Program program);

	void addTypeAssignment(ForNextStmtContext ctx, Program program);

	void addTypeAssignment(LetStmtContext ctx, Program program);

	void addTypeAssignment(RedimSubStmtContext ctx, Program program);

	void addTypeAssignment(SetStmtContext ctx, Program program);

	void addTypeAssignment(VsAssignContext ctx, Program program);
}
