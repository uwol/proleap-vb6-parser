/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.oop.impl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.vb6.parser.metamodel.impl.SemanticGraphElementImpl;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.ScopedElement;

public abstract class ScopedElementImpl extends SemanticGraphElementImpl implements ScopedElement {

	private final static Logger LOG = LogManager.getLogger(ScopedElementImpl.class);

	protected final Scope superScope;

	public ScopedElementImpl(final Scope superScope, final ParseTree ctx) {
		super(ctx);

		this.superScope = superScope;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Scope> T findSuperScope(final Class<T> type) {
		T result = null;

		Scope currentScope = this instanceof Scope ? (Scope) this : superScope;

		while (result == null && currentScope != null) {
			if (type.isAssignableFrom(currentScope.getClass())) {
				result = (T) currentScope;
			}

			currentScope = currentScope.getSuperScope();
		}

		return result;
	}

	@Override
	public Scope getSuperScope() {
		return superScope;
	}

}
