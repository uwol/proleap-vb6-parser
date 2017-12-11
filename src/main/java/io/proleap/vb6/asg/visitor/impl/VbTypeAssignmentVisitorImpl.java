/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.asg.inference.impl.TypeAssignmentInferenceImpl;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;

public class VbTypeAssignmentVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbTypeAssignmentVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitArgCall(final VisualBasic6Parser.ArgCallContext ctx) {
		final Program program = module.getProgram();
		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, program);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitForNextStmt(final VisualBasic6Parser.ForNextStmtContext ctx) {
		final Program program = module.getProgram();
		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, program);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLetStmt(final VisualBasic6Parser.LetStmtContext ctx) {
		final Program program = module.getProgram();
		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, program);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRedimSubStmt(final VisualBasic6Parser.RedimSubStmtContext ctx) {
		final Program program = module.getProgram();
		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, program);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSetStmt(final VisualBasic6Parser.SetStmtContext ctx) {
		final Program program = module.getProgram();
		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, program);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAssign(final VisualBasic6Parser.VsAssignContext ctx) {
		final Program program = module.getProgram();
		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, program);

		return visitChildren(ctx);
	}
}
