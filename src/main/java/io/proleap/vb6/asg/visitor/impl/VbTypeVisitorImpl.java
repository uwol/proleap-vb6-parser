/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;

/**
 * Visitor for collecting type / enumeration declarations in the AST.
 */
public class VbTypeVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbTypeVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitEnumerationStmt(final VisualBasic6Parser.EnumerationStmtContext ctx) {
		module.addEnumeration(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnumerationStmt_Constant(final VisualBasic6Parser.EnumerationStmt_ConstantContext ctx) {
		final EnumerationStmtContext enumerationCtx = (EnumerationStmtContext) ctx.getParent();
		final Enumeration enumeration = (Enumeration) getASGElement(enumerationCtx);

		enumeration.addEnumerationConstant(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTypeStmt(final VisualBasic6Parser.TypeStmtContext ctx) {
		module.addType(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTypeStmt_Element(final VisualBasic6Parser.TypeStmt_ElementContext ctx) {
		module.addTypeElement(ctx);

		return visitChildren(ctx);
	}
}
