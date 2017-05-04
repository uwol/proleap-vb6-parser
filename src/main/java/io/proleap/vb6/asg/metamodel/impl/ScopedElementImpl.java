/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.ScopedElement;

public abstract class ScopedElementImpl extends ASGElementImpl implements ScopedElement {

	protected Module module;

	protected final Scope scope;

	public ScopedElementImpl(final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(ctx);

		this.scope = scope;
		this.module = module;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Scope> T findScope(final Class<T> type) {
		T result = null;

		Scope currentScope = this instanceof Scope ? (Scope) this : scope;

		while (result == null && currentScope != null) {
			if (type.isAssignableFrom(currentScope.getClass())) {
				result = (T) currentScope;
			}

			currentScope = currentScope.getScope();
		}

		return result;
	}

	@Override
	public Module getModule() {
		return module;
	}

	@Override
	public Scope getScope() {
		return scope;
	}

}
