/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.ifstmt;

import io.proleap.vb6.parser.metamodel.ScopedElement;
import io.proleap.vb6.parser.metamodel.type.TypedElement;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

/**
 * Conditionally executes a group of statements, depending on the value of an
 * expression.
 */
public interface IfCondition extends TypedElement, ScopedElement {

	ValueStmt getValueStmt();

	void setValueStmt(ValueStmt valueStmt);
}
