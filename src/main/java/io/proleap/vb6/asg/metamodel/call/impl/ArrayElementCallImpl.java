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
import io.proleap.vb6.asg.metamodel.call.ArrayElementCall;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;

public class ArrayElementCallImpl extends VariableCallImpl implements ArrayElementCall {

	public ArrayElementCallImpl(final String name, final Variable variable, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(name, variable, module, scope, ctx);
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
}
