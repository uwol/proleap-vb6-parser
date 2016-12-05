/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.Module;

public class VbTypeAssignmentVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbTypeAssignmentVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitArgCall(@NotNull final VisualBasic6Parser.ArgCallContext ctx) {
		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitForNextStmt(@NotNull final VisualBasic6Parser.ForNextStmtContext ctx) {
		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLetStmt(@NotNull final VisualBasic6Parser.LetStmtContext ctx) {
		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRedimSubStmt(@NotNull final VisualBasic6Parser.RedimSubStmtContext ctx) {
		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSetStmt(@NotNull final VisualBasic6Parser.SetStmtContext ctx) {
		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAssign(@NotNull final VisualBasic6Parser.VsAssignContext ctx) {
		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return visitChildren(ctx);
	}

}
