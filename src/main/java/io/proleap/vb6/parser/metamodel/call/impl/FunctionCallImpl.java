/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Function;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.FunctionCall;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class FunctionCallImpl extends CallImpl implements FunctionCall {

	protected Function function;

	public FunctionCallImpl(final String name, final Function function, final Module module, final VbScope superScope,
			final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.function = function;
	}

	@Override
	public CallType getCallType() {
		return CallType.FunctionCall;
	}

	@Override
	public Function getFunction() {
		return function;
	}

	@Override
	public Type getType() {
		final Type result;

		if (function != null) {
			result = function.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", function=[" + function + "]";
	}
}
