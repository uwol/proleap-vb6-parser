/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import io.proleap.vb6.VisualBasic6BaseVisitor;
import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.asg.resolver.impl.NameResolverImpl;

/**
 * Determines the module name as declared in the VB_Name attribute.
 */
public class VbModuleNameAnalyzerVisitorImpl extends VisualBasic6BaseVisitor<String> {

	public final String VB_NAME = "VB_Name";

	@Override
	protected String aggregateResult(final String aggregate, final String nextResult) {
		if (nextResult != null) {
			return nextResult;
		}

		return aggregate;
	}

	@Override
	public String visitAttributeStmt(final VisualBasic6Parser.AttributeStmtContext ctx) {
		final String name = new NameResolverImpl().determineName(ctx);

		// if the module name is declared
		if (VB_NAME.toLowerCase().equals(name.toLowerCase())) {
			final String attributeValue = ctx.literal(0).getText().replace("\"", "");
			return attributeValue;
		} else {
			return visitChildren(ctx);
		}
	}
}
