/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import io.proleap.vb6.asg.metamodel.type.ComplexType;

/**
 * Used at module level to define a user-defined data type containing one or
 * more elements.
 */
public interface Type extends ScopedElement, ComplexType, VisibilityElement {

	void addTypeElement(TypeElement typeElement);

	TypeElement getTypeElement(String name);
}
