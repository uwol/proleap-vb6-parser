/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.parser.metamodel.oop.Declaration;
import io.proleap.vb6.parser.metamodel.oop.TypedElement;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public interface Constant extends TypedElement, Declaration, VbScopedElement {

	@Override
	ConstSubStmtContext getCtx();

	ValueStmt getValueStmt();

	boolean isModuleConstant();

	void setValueStmt(ValueStmt value);
}
