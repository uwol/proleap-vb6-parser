/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select.impl;

import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.asg.metamodel.type.Type;

public class SelectCaseImpl extends ScopedElementImpl implements SelectCase {

	protected final SC_CaseContext ctx;

	protected Select select;

	protected SelectCaseCond selectCaseCond;

	public SelectCaseImpl(final Module module, final Scope scope, final SC_CaseContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public SC_CaseContext getCtx() {
		return ctx;
	}

	@Override
	public Select getSelect() {
		return select;
	}

	@Override
	public SelectCaseCond getSelectCaseCond() {
		return selectCaseCond;
	}

	@Override
	public Type getType() {
		final Type result = selectCaseCond.getType();
		return result;
	}

	@Override
	public boolean hasBlock() {
		final boolean result = ctx.block() != null;
		return result;
	}

	@Override
	public void setSelect(final Select select) {
		this.select = select;
	}

	@Override
	public void setSelectCaseCond(final SelectCaseCond selectCaseCond) {
		this.selectCaseCond = selectCaseCond;
	}
}
