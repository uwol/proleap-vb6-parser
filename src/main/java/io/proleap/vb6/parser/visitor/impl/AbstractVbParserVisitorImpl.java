/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.VisualBasic6BaseVisitor;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.ASGElement;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.registry.ASGElementRegistry;
import io.proleap.vb6.parser.util.ANTLRUtils;
import io.proleap.vb6.parser.visitor.ParserVisitor;

public abstract class AbstractVbParserVisitorImpl extends VisualBasic6BaseVisitor<Boolean> implements ParserVisitor {

	protected Module module;

	public AbstractVbParserVisitorImpl(final Module module) {
		this.module = module;
	}

	protected VbScope findScope(final ParseTree ctx) {
		final ASGElementRegistry registry = VbParserContext.getInstance().getASGElementRegistry();
		return ANTLRUtils.findParent(VbScope.class, ctx, registry);
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = VbParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

}
