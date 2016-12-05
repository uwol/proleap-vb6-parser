/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SC_CondContext;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public interface SelectCaseCond extends ScopedElement, TypedElement {

	public enum SelectCaseCondType {
		ELSE, IS, TO, VALUE;
	}

	void addValueStmt(ValueStmt valueStmt);

	@Override
	SC_CondContext getCtx();

	SelectCase getSelectCase();

	SelectCaseCondType getSelectCaseCondType();

	List<ValueStmt> getValueStmts();

	void setSelectCase(SelectCase selectCase);
}
