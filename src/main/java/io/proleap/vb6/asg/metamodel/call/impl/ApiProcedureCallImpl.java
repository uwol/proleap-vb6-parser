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
import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.metamodel.call.ApiProcedureCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ApiProcedureCallImpl extends CallImpl implements ApiProcedureCall {

	protected final ApiProcedure apiProcedure;

	public ApiProcedureCallImpl(final String name, final ApiProcedure apiProcedure, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.apiProcedure = apiProcedure;
	}

	@Override
	public ApiProcedure getApiProcedure() {
		return apiProcedure;
	}

	@Override
	public CallType getCallType() {
		return CallType.API_PROCEDURE_CALL;
	}

	@Override
	public Type getType() {
		final Type result;

		if (apiProcedure != null) {
			result = apiProcedure.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", apiProcedure=[" + apiProcedure + "]";
	}

}
