/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.oop.api.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.parser.metamodel.call.ApiProcedureCall;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProcedure;

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
