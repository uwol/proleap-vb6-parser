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

import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.metamodel.registry.api.ApiPropertyRegistry;
import io.proleap.vb6.asg.resolver.impl.TypeNameSanitizerImpl;

public class ApiPropertyRegistryImpl implements ApiPropertyRegistry {

	final Map<String, ApiProperty> apiProperties = new HashMap<String, ApiProperty>();

	@Override
	public ApiProperty getApiProperty(final String name) {
		final String key = getKey(name);
		return apiProperties.get(key);
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = new TypeNameSanitizerImpl().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public void registerApiProperty(final ApiProperty apiProperty) {
		final String key = getKey(apiProperty.getName());
		apiProperties.put(key, apiProperty);
	}
}
