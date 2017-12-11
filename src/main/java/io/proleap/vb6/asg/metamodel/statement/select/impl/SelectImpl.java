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

import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopeImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class SelectImpl extends ScopeImpl implements Select {

	protected final SelectCaseStmtContext ctx;

	protected List<SelectCase> selectCases = new ArrayList<SelectCase>();

	protected final StatementType statementType = StatementTypeEnum.SELECT;

	protected ValueStmt valueStmt;

	public SelectImpl(final Module module, final Scope scope, final SelectCaseStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSelectCase(final SelectCase selectCase) {
		selectCases.add(selectCase);
	}

	@Override
	public SelectCaseStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<SelectCase> getSelectCases() {
		return selectCases;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		final Type result;

		final Type valueStmtType = valueStmt.getType();

		if (valueStmtType != null) {
			result = valueStmtType;
		} else {
			Type firstSelectCaseType = null;

			for (final SelectCase selectCase : selectCases) {
				final Type currentSelectCaseType = selectCase.getType();

				if (currentSelectCaseType != null) {
					firstSelectCaseType = currentSelectCaseType;
					break;
				}
			}

			result = firstSelectCaseType;
		}

		return result;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

}
