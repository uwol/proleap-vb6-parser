/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

/**
 * A value assignment to a procedure arg. In VB6 reference lingo such an
 * assignment is called arg call.
 */
public interface ArgValueAssignment extends ScopedElement, TypedElement {

	Arg getArg();

	ValueStmt getAssignedValueStmt();

	@Override
	ArgCallContext getCtx();

	void setArg(Arg arg);

	void setAssignedValueStmt(ValueStmt assignedValueStmt);
}
