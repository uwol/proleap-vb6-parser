/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.FunctionCall;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.type.Type;

public class FunctionCallImpl extends CallImpl implements FunctionCall {

	protected Function function;

	public FunctionCallImpl(final String name, final Function function, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.function = function;
	}

	@Override
	public CallType getCallType() {
		return CallType.FUNCTION_CALL;
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
