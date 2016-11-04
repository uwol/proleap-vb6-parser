/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Select;
import io.proleap.vb6.parser.metamodel.SelectCase;
import io.proleap.vb6.parser.metamodel.SelectCaseCond;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class SelectCaseImpl extends VbScopedElementImpl implements SelectCase {

	protected final SC_CaseContext ctx;

	protected Select select;

	protected SelectCaseCond selectCaseCond;

	public SelectCaseImpl(final Module module, final VbScope superScope, final SC_CaseContext ctx) {
		super(module, superScope, ctx);

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
