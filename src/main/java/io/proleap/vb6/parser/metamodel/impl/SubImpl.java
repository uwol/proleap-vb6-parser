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

import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Sub;
import io.proleap.vb6.parser.metamodel.call.SubCall;

public class SubImpl extends ProcedureImpl implements Sub {

	protected final SubStmtContext ctx;

	protected final List<SubCall> subCalls = new ArrayList<SubCall>();

	public SubImpl(final String name, final Module module, final SubStmtContext ctx) {
		super(name, module, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSubCall(final SubCall subCall) {
		subCalls.add(subCall);
	}

	@Override
	public SubStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<SubCall> getSubCalls() {
		return subCalls;
	}

	@Override
	public boolean hasReturnType() {
		return false;
	}

}
