/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.registry.api.impl;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.api.ApiEnumeration;
import io.proleap.vb6.parser.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.parser.registry.api.ApiEnumerationRegistry;

public class ApiEnumerationRegistryImpl implements ApiEnumerationRegistry {

	final Map<String, ApiEnumeration> apiEnumerations = new HashMap<String, ApiEnumeration>();

	@Override
	public ApiEnumeration getApiEnumeration(final String name) {
		final String key = getKey(name);
		return apiEnumerations.get(key);
	}

	@Override
	public ApiEnumerationConstant getApiEnumerationConstant(final String name) {
		for (final ApiEnumeration apiEnumeration : apiEnumerations.values()) {
			if (apiEnumeration.getApiEnumerationConstant(name) != null) {
				return apiEnumeration.getApiEnumerationConstant(name);
			}
		}

		return null;
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = VbParserContext.getInstance().getTypeNameSanitizer().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public void registerApiEnumeration(final ApiEnumeration apiEnumeration) {
		final String key = getKey(apiEnumeration.getName());
		apiEnumerations.put(key, apiEnumeration);
	}
}
