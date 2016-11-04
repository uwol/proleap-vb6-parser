/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.parser.metamodel.VbScopedElement;
import io.proleap.vb6.parser.metamodel.oop.TypedElement;

public interface ValueStmt extends VbScopedElement, TypedElement {

	void addSubValueStmt(ValueStmt subValueStmt);

	@Override
	ValueStmtContext getCtx();

	List<ValueStmt> getSubValueStmts();
}
