/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SC_CondContext;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

public interface SelectCaseCond extends ScopedElement, TypedElement {

	public enum SelectCaseCondType {
		ELSE, EXPRESSION;
	}

	void addSelectCaseCondExpression(SelectCaseCondExpression selectCaseCondExpression);

	@Override
	SC_CondContext getCtx();

	SelectCase getSelectCase();

	List<SelectCaseCondExpression> getSelectCaseCondExpressions();

	SelectCaseCondType getSelectCaseCondType();

	void setSelectCase(SelectCase selectCase);
}
