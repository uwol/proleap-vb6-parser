/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SC_CondExprContext;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public interface SelectCaseCondExpression extends ScopedElement, TypedElement {

	public enum SelectCaseCondExpressionType {
		IS, TO, VALUE;
	}

	void addValueStmt(ValueStmt valueStmt);

	@Override
	SC_CondExprContext getCtx();

	SelectCaseCond getSelectCaseCond();

	SelectCaseCondExpressionType getSelectCaseCondExpressionType();

	List<ValueStmt> getValueStmts();

	void setSelectCaseCond(SelectCaseCond selectCaseCond);
}
