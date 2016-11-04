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
import io.proleap.vb6.parser.metamodel.call.ApiProcedureCall;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProcedure;

public class ApiProcedureCallImpl extends CallImpl implements ApiProcedureCall {

	protected final ApiProcedure apiProcedure;

	public ApiProcedureCallImpl(final String name, final ApiProcedure apiProcedure, final Module module,
			final Scope superScope, final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.apiProcedure = apiProcedure;
	}

	@Override
	public ApiProcedure getApiProcedure() {
		return apiProcedure;
	}

	@Override
	public CallType getCallType() {
		return CallType.ApiProcedureCall;
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
