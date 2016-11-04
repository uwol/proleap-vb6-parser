/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.parser.metamodel.oop.TypedElement;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public interface Select extends VbScope, TypedElement {

	void addSelectCase(SelectCase selectCase);

	@Override
	SelectCaseStmtContext getCtx();

	List<SelectCase> getSelectCases();

	ValueStmt getValueStmt();

	void setValueStmt(final ValueStmt valueStmt);
}
