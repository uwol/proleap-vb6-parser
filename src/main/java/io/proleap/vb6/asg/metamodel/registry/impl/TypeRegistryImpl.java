/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.registry.impl;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.asg.metamodel.registry.TypeRegistry;
import io.proleap.vb6.asg.metamodel.type.BaseType;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.impl.ComplexTypeImpl;
import io.proleap.vb6.asg.resolver.impl.TypeNameSanitizerImpl;

public class TypeRegistryImpl implements TypeRegistry {

	protected final Map<String, Type> types = new HashMap<String, Type>();

	protected void assureComplexType(final String name) {
		final ComplexType complexType = new ComplexTypeImpl(name, false);
		registerType(complexType);
	}

	@Override
	public BaseType getBaseType(final String name) {
		final String key = getKey(name);
		return (BaseType) types.get(key);
	}

	@Override
	public ComplexType getComplexType(final String name) {
		final String key = getKey(name);

		assureComplexType(name);

		return (ComplexType) types.get(key);
	}

	private String getKey(final String name) {
		final String sanitizedTypeName = new TypeNameSanitizerImpl().sanitize(name);
		return sanitizedTypeName.toLowerCase();
	}

	@Override
	public Type getType(final String name) {
		final String key = getKey(name);

		Type result = types.get(key);

		if (result == null) {
			result = getComplexType(name);
		}

		return result;
	}

	@Override
	public void registerType(final Type type) {
		final String key = getKey(type.getName());

		if (!types.containsKey(key)) {
			types.put(key, type);
		}
	}
}
