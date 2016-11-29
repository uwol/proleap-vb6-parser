/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.ModuleConfigElementContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.ModuleConfigElement;
import io.proleap.vb6.parser.metamodel.Scope;

public class ModuleConfigElementImpl extends ScopedElementImpl implements ModuleConfigElement {

	protected final ModuleConfigElementContext ctx;

	protected final String name;

	public ModuleConfigElementImpl(final String name, final Module module, final Scope scope,
			final ModuleConfigElementContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public ModuleConfigElementContext getCtx() {
		return ctx;
	}

	@Override
	public String getName() {
		return name;
	}

}
