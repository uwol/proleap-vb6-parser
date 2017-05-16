/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import io.proleap.vb6.asg.metamodel.type.ComplexType;

/**
 * Used at module level to define a user-defined data type containing one or
 * more elements.
 */
public interface Type extends ScopedElement, ComplexType {

	void addTypeElement(TypeElement typeElement);

	TypeElement getTypeElement(String name);
}
