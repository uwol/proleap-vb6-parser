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
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.ArrayElementCall;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class ArrayElementCallImpl extends CallImpl implements ArrayElementCall {

	protected boolean isSettingCall;

	protected Variable variable;

	public ArrayElementCallImpl(final String name, final Module module, final VbScope superScope, final ParseTree ctx) {
		super(name, module, superScope, ctx);
	}

	@Override
	public CallType getCallType() {
		return CallType.ArrayElementCall;
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
