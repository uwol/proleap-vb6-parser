/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.inference;

import java.util.Set;

import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.type.Type;

public interface TypeInference {

	Type inferType(Type declaredType, Type defType, Set<Type> assignedTypes);

	Type inferTypeFromDefType(Module module, String variableName);
}
