/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Select;
import io.proleap.vb6.parser.metamodel.SelectCase;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class SelectImpl extends VbScopeImpl implements Select {

	protected final SelectCaseStmtContext ctx;

	protected List<SelectCase> selectCases = new ArrayList<SelectCase>();

	protected ValueStmt valueStmt;

	public SelectImpl(final Module module, final VbScope superScope, final SelectCaseStmtContext ctx) {
		super(module, superScope, ctx);

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
