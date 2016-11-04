/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.registry;

import io.proleap.vb6.parser.metamodel.oop.BaseType;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;
import io.proleap.vb6.parser.metamodel.oop.Type;

public interface TypeRegistry {

	BaseType getBaseType(String name);

	ComplexType getComplexType(String name);

	Type getType(String name);

	void registerType(Type type);
}
