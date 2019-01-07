/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.sub.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.SubCall;
import io.proleap.vb6.asg.metamodel.impl.ProcedureImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;

public class SubImpl extends ProcedureImpl implements Sub {

	protected final SubStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.SUB;

	protected final List<SubCall> subCalls = new ArrayList<SubCall>();

	public SubImpl(final String name, final VisibilityEnum visibility, final Module module, final SubStmtContext ctx) {
		super(name, visibility, module, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addSubCall(final SubCall subCall) {
		subCalls.add(subCall);
	}

	@Override
	public List<Call> getCalls() {
		return new ArrayList<>(getSubCalls());
	}

	@Override
	public SubStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
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
