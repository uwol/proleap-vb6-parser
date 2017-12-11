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
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.ReturnValueCall;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

public class ReturnValueCallImpl extends CallImpl implements ReturnValueCall {

	protected Procedure procedure;

	public ReturnValueCallImpl(final String name, final Procedure procedure, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.procedure = procedure;
	}

	@Override
	public CallType getCallType() {
		return CallType.RETURN_VALUE_CALL;
	}

	@Override
	public Procedure getProcedure() {
		return procedure;
	}

	@Override
	public Type getType() {
		final Type result;

		if (procedure != null && procedure instanceof TypedElement) {
			result = ((TypedElement) procedure).getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", procedure=[" + procedure + "]";
	}
}
