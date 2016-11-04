/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScopedElement;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.impl.ScopedElementImpl;

public abstract class VbScopedElementImpl extends ScopedElementImpl implements VbScopedElement {

	protected final Module module;

	public VbScopedElementImpl(final Module module, final Scope superScope, final ParseTree ctx) {
		super(superScope, ctx);

		this.module = module;
	}

	@Override
	public Module getModule() {
		return module;
	}

}
