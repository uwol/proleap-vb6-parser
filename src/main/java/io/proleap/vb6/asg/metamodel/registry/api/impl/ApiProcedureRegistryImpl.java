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

import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.metamodel.registry.api.ApiProcedureRegistry;
import io.proleap.vb6.asg.resolver.impl.TypeNameSanitizerImpl;

public class ApiProcedureRegistryImpl implements ApiProcedureRegistry {

	final Map<String, ApiProcedure> apiProcedures = new HashMap<String, ApiProcedure>();

	@Override
	public ApiProcedure getApiProcedure(final String name) {
		final String key = getKey(name);
		return apiProcedures.get(key);
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = new TypeNameSanitizerImpl().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public void registerApiProcedure(final ApiProcedure apiProcedure) {
		final String key = getKey(apiProcedure.getName());
		apiProcedures.put(key, apiProcedure);
	}
}
