/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.redim;

import io.proleap.vb6.asg.metamodel.NamedElement;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

/**
 * Used at procedure level to reallocate storage space for dynamic array
 * variables.
 */
public interface ReDim extends TypedElement, NamedElement, Statement {

	Variable getVariable();
}
