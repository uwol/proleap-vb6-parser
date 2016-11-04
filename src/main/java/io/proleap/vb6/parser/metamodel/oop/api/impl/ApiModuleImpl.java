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

import io.proleap.vb6.parser.metamodel.oop.api.ApiModule;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProcedure;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProperty;

public class ApiModuleImpl implements ApiModule {

	protected final boolean isCollection;

	protected final String name;

	protected final Map<String, ApiProcedure> procedures = new HashMap<String, ApiProcedure>();

	protected final Map<String, ApiProperty> properties = new HashMap<String, ApiProperty>();

	public ApiModuleImpl(final String name, final boolean isCollection) {
		this.name = name;
		this.isCollection = isCollection;
	}

	@Override
	public ApiProcedure getApiProcedure(final String name) {
		final String key = getKey(name);
		return procedures.get(key);
	}

	@Override
	public ApiProperty getApiProperty(final String name) {
		final String key = getKey(name);
		return properties.get(key);
	}

	private String getKey(final String name) {
		return name.toLowerCase();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}

	@Override
	public void registerApiProcedure(final ApiProcedure apiProcedure) {
		final String key = getKey(apiProcedure.getName());
		procedures.put(key, apiProcedure);
	}

	@Override
	public void registerApiProperty(final ApiProperty apiProperty) {
		final String key = getKey(apiProperty.getName());
		properties.put(key, apiProperty);
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
