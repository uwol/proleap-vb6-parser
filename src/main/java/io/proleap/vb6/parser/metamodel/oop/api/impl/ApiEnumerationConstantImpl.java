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

import io.proleap.vb6.parser.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.oop.api.ApiEnumeration;
import io.proleap.vb6.parser.metamodel.oop.api.ApiEnumerationConstant;

public class ApiEnumerationConstantImpl implements ApiEnumerationConstant {

	protected final ApiEnumeration apiEnumeration;

	protected final List<ApiEnumerationConstantCall> apiEnumerationConstantCalls = new ArrayList<ApiEnumerationConstantCall>();

	protected final String name;

	public ApiEnumerationConstantImpl(final String name, final ApiEnumeration apiEnumeration) {
		this.name = name;
		this.apiEnumeration = apiEnumeration;
	}

	@Override
	public void addApiEnumerationConstantCall(final ApiEnumerationConstantCall apiEnumerationConstantCall) {
		apiEnumerationConstantCalls.add(apiEnumerationConstantCall);
	}

	@Override
	public ApiEnumeration getApiEnumeration() {
		return apiEnumeration;
	}

	@Override
	public List<ApiEnumerationConstantCall> getApiEnumerationConstantCalls() {
		return apiEnumerationConstantCalls;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return apiEnumeration;
	}

	@Override
	public String toString() {
		return "name=[" + name + "], apiEnumeration=[" + apiEnumeration + "]";
	}
}
