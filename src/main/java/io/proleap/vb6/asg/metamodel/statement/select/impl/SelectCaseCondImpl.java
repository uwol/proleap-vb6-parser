/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SC_CondContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.type.Type;

public class SelectCaseCondImpl extends ScopedElementImpl implements SelectCaseCond {

	protected final SC_CondContext ctx;

	protected SelectCase selectCase;

	protected final List<SelectCaseCondExpression> selectCaseCondExpressions = new ArrayList<SelectCaseCondExpression>();

	protected final SelectCaseCondType selectCaseCondType;

	public SelectCaseCondImpl(final SelectCaseCondType selectCaseCondType, final Module module, final Scope scope,
			final SC_CondContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.selectCaseCondType = selectCaseCondType;
	}

	@Override
	public void addSelectCaseCondExpression(final SelectCaseCondExpression selectCaseCondExpression) {
		selectCaseCondExpressions.add(selectCaseCondExpression);
	}

	@Override
	public SC_CondContext getCtx() {
		return ctx;
	}

	@Override
	public SelectCase getSelectCase() {
		return selectCase;
	}

	@Override
	public List<SelectCaseCondExpression> getSelectCaseCondExpressions() {
		return selectCaseCondExpressions;
	}

	@Override
	public SelectCaseCondType getSelectCaseCondType() {
		return selectCaseCondType;
	}

	@Override
	public Type getType() {
		final Type result;

		if (SelectCaseCondType.ELSE.equals(selectCaseCondType)) {
			result = null;
		} else if (!selectCaseCondExpressions.isEmpty()) {
			result = selectCaseCondExpressions.get(0).getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public void setSelectCase(final SelectCase selectCase) {
		this.selectCase = selectCase;
	}
}
