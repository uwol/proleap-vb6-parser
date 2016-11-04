/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.registry.impl;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.oop.BaseType;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.oop.impl.ComplexTypeImpl;
import io.proleap.vb6.parser.registry.TypeRegistry;

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
		final String sanitizedTypeName = VbParserContext.getInstance().getTypeNameSanitizer().sanitize(name);
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
