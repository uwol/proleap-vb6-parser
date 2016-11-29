/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.StandardModule;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.call.ModuleCall;
import io.proleap.vb6.parser.metamodel.type.Type;

public class ModuleCallImpl extends CallImpl implements ModuleCall {

	public StandardModule calledModule;

	public ModuleCallImpl(final String name, final StandardModule calledModule, final Module module,
			final Scope scope, final ParseTree ctx) {
		super(name, module, scope, ctx);

		this.calledModule = calledModule;
	}

	@Override
	public StandardModule getCalledModule() {
		return calledModule;
	}

	@Override
	public CallType getCallType() {
		return CallType.ModuleCall;
	}

	@Override
	public Type getType() {
		return calledModule;
	}

}
