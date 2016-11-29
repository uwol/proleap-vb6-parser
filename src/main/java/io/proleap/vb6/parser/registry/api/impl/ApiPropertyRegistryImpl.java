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
import io.proleap.vb6.parser.metamodel.api.ApiProperty;
import io.proleap.vb6.parser.registry.api.ApiPropertyRegistry;

public class ApiPropertyRegistryImpl implements ApiPropertyRegistry {

	final Map<String, ApiProperty> apiProperties = new HashMap<String, ApiProperty>();

	@Override
	public ApiProperty getApiProperty(final String name) {
		final String key = getKey(name);
		return apiProperties.get(key);
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = VbParserContext.getInstance().getTypeNameSanitizer().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public void registerApiProperty(final ApiProperty apiProperty) {
		final String key = getKey(apiProperty.getName());
		apiProperties.put(key, apiProperty);
	}
}
