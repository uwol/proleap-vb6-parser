/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.registry;

import io.proleap.vb6.asg.metamodel.type.BaseType;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.type.Type;

public interface TypeRegistry {

	BaseType getBaseType(String name);

	ComplexType getComplexType(String name);

	Type getType(String name);

	void registerType(Type type);
}
