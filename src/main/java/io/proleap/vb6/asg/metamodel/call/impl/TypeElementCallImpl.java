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
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.call.TypeElementCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class TypeElementCallImpl extends CallImpl implements TypeElementCall {

	protected final TypeElement typeElement;

	public TypeElementCallImpl(final String name, final TypeElement typeElement, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.typeElement = typeElement;
	}

	@Override
	public CallType getCallType() {
		return CallType.TYPE_ELEMENT_CALL;
	}

	@Override
	public Type getType() {
		final Type result;

		if (typeElement != null) {
			result = typeElement.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public TypeElement getTypeElement() {
		return typeElement;
	}

	@Override
	public String toString() {
		return super.toString() + ", typeElement=[" + typeElement + "]";
	}
}
