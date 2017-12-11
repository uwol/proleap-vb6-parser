/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.registry.api.impl;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.registry.api.ApiEnumerationRegistry;
import io.proleap.vb6.asg.resolver.impl.TypeNameSanitizerImpl;

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
		final String sanitizedTypeName = new TypeNameSanitizerImpl().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public void registerApiEnumeration(final ApiEnumeration apiEnumeration) {
		final String key = getKey(apiEnumeration.getName());
		apiEnumerations.put(key, apiEnumeration);
	}
}
