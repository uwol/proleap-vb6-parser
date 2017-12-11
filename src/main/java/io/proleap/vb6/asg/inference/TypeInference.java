/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.inference;

import java.util.Set;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.type.Type;

public interface TypeInference {

	Type inferType(Type declaredType, Type defType, Set<Type> assignedTypes);

	Type inferTypeFromDefType(Module module, String variableName);
}
