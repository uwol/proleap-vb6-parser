/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class VariableCallImpl extends CallImpl implements VariableCall {

	protected final Variable variable;

	public VariableCallImpl(final String name, final Variable variable, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.variable = variable;
	}

	@Override
	public CallType getCallType() {
		return CallType.VARIABLE_CALL;
	}

	@Override
	public Type getType() {
		final Type result;

		if (variable != null) {
			result = variable.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public Variable getVariable() {
		return variable;
	}

	@Override
	public String toString() {
		return super.toString() + ", variable=[" + variable + "]";
	}
}
