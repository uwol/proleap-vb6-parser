/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.ScopedElement;
import io.proleap.vb6.parser.metamodel.type.TypedElement;

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
