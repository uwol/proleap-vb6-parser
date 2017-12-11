/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.VisualBasic6BaseVisitor;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.util.ANTLRUtils;
import io.proleap.vb6.asg.visitor.ParserVisitor;

public abstract class AbstractVbParserVisitorImpl extends VisualBasic6BaseVisitor<Boolean> implements ParserVisitor {

	protected Module module;

	public AbstractVbParserVisitorImpl(final Module module) {
		this.module = module;
	}

	protected Scope findScope(final ParseTree ctx) {
		final ASGElementRegistry registry = module.getProgram().getASGElementRegistry();
		return ANTLRUtils.findParent(Scope.class, ctx, registry);
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = module.getProgram().getASGElementRegistry().getASGElement(ctx);
		return result;
	}
}
