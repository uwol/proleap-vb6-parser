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

import io.proleap.vb6.VisualBasic6Parser.SC_CondExprContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class SelectCaseCondExpressionImpl extends ScopedElementImpl implements SelectCaseCondExpression {

	protected final SC_CondExprContext ctx;

	protected SelectCaseCond selectCaseCond;

	protected final SelectCaseCondExpressionType selectCaseCondExpressionType;

	protected final List<ValueStmt> valueStmts = new ArrayList<ValueStmt>();

	public SelectCaseCondExpressionImpl(final SelectCaseCondExpressionType selectCaseCondExpressionType,
			final Module module, final Scope scope, final SC_CondExprContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.selectCaseCondExpressionType = selectCaseCondExpressionType;
	}

	@Override
	public void addValueStmt(final ValueStmt valueStmt) {
		valueStmts.add(valueStmt);
	}

	@Override
	public SC_CondExprContext getCtx() {
		return ctx;
	}

	@Override
	public SelectCaseCond getSelectCaseCond() {
		return selectCaseCond;
	}

	@Override
	public SelectCaseCondExpressionType getSelectCaseCondExpressionType() {
		return selectCaseCondExpressionType;
	}

	@Override
	public Type getType() {
		final Type result;

		if (!valueStmts.isEmpty()) {
			result = valueStmts.get(0).getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public List<ValueStmt> getValueStmts() {
		return valueStmts;
	}

	@Override
	public void setSelectCaseCond(final SelectCaseCond selectCaseCond) {
		this.selectCaseCond = selectCaseCond;
	}
}
