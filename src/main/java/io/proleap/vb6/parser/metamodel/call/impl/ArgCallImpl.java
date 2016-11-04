/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.ArgCall;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class ArgCallImpl extends CallImpl implements ArgCall {

	protected Arg arg;

	public ArgCallImpl(final String name, final Arg arg, final Module module, final VbScope superScope,
			final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.arg = arg;
	}

	@Override
	public Arg getArg() {
		return arg;
	}

	@Override
	public CallType getCallType() {
		return CallType.ArgCall;
	}

	@Override
	public Type getType() {
		final Type result;

		if (arg != null) {
			result = arg.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", arg=[" + arg + "]";
	}
}
