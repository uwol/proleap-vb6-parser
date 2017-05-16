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
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.call.ArrayElementCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ArrayElementCallImpl extends CallImpl implements ArrayElementCall {

	protected boolean isSettingCall;

	protected Variable variable;

	public ArrayElementCallImpl(final String name, final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);
	}

	@Override
	public CallType getCallType() {
		return CallType.ARRAY_ELEMENT_CALL;
	}

	@Override
	public Type getType() {
		final Type result;

		if (variable != null) {
			final Type variableType = variable.getType();

			if (VbBaseType.COLLECTION.equals(variableType)) {
				result = null;
			} else {
				result = variableType;
			}
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
	public void setVariable(final Variable variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "variable=[" + variable + "]";
	}
}
