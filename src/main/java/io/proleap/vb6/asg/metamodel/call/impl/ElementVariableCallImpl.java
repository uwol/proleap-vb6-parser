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
import io.proleap.vb6.asg.metamodel.call.ElementVariableCall;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ElementVariableCallImpl extends CallImpl implements ElementVariableCall {

	protected final ElementVariable elementVariable;

	public ElementVariableCallImpl(final String name, final ElementVariable elementVariable, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.elementVariable = elementVariable;
	}

	@Override
	public CallType getCallType() {
		return CallType.ELEMENT_VARIABLE_CALL;
	}

	@Override
	public ElementVariable getElementVariable() {
		return elementVariable;
	}

	@Override
	public Type getType() {
		final Type result;

		if (elementVariable != null) {
			result = elementVariable.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", elementVariable=[" + elementVariable + "]";
	}
}
