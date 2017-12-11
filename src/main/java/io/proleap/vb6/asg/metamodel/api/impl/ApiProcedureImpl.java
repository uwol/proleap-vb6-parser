/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.api.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.metamodel.call.ApiProcedureCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ApiProcedureImpl implements ApiProcedure {

	protected final List<ApiProcedureCall> apiProcedureCalls = new ArrayList<ApiProcedureCall>();

	protected final String name;

	protected final Type type;

	public ApiProcedureImpl(final String name, final Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public void addApiProcedureCall(final ApiProcedureCall apiProcedureCall) {
		apiProcedureCalls.add(apiProcedureCall);
	}

	@Override
	public List<ApiProcedureCall> getApiProcedureCalls() {
		return apiProcedureCalls;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
