/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.oop.api.impl;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.oop.api.ApiEnumeration;
import io.proleap.vb6.parser.metamodel.oop.api.ApiEnumerationConstant;

public class ApiEnumerationImpl implements ApiEnumeration {

	protected final Map<String, ApiEnumerationConstant> constants = new HashMap<String, ApiEnumerationConstant>();

	protected final String name;

	public ApiEnumerationImpl(final String name) {
		this.name = name;
	}

	@Override
	public ApiEnumerationConstant getApiEnumerationConstant(final String name) {
		final String key = getKey(name);
		return constants.get(key);
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = VbParserContext.getInstance().getTypeNameSanitizer().sanitize(name);
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
