/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationCall;
import io.proleap.vb6.asg.resolver.impl.TypeNameSanitizerImpl;

public class ApiEnumerationImpl implements ApiEnumeration {

	protected final List<ApiEnumerationCall> apiEnumerationCalls = new ArrayList<ApiEnumerationCall>();

	protected final Map<String, ApiEnumerationConstant> constants = new HashMap<String, ApiEnumerationConstant>();

	protected final String name;

	public ApiEnumerationImpl(final String name) {
		this.name = name;
	}

	@Override
	public void addApiEnumerationCall(final ApiEnumerationCall apiEnumerationCall) {
		apiEnumerationCalls.add(apiEnumerationCall);
	}

	@Override
	public List<ApiEnumerationCall> getApiEnumerationCalls() {
		return apiEnumerationCalls;
	}

	@Override
	public ApiEnumerationConstant getApiEnumerationConstant(final String name) {
		final String key = getKey(name);
		return constants.get(key);
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = new TypeNameSanitizerImpl().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isCollection() {
		return false;
	}

	@Override
	public void registerApiEnumerationConstant(final ApiEnumerationConstant apiEnumerationConstant) {
		final String key = getKey(apiEnumerationConstant.getName());
		constants.put(key, apiEnumerationConstant);
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
