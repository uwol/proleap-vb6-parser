/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.constant.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.call.ConstantCall;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ConstantImpl extends ScopedElementImpl implements Constant {

	protected final List<ConstantCall> constantCalls = new ArrayList<ConstantCall>();

	protected final ConstSubStmtContext ctx;

	protected final String name;

	protected final StatementType statementType = StatementTypeEnum.CONSTANT;

	protected final Type type;

	protected ValueStmt valueStmt;

	protected final VisibilityEnum visibility;

	public ConstantImpl(final String name, final VisibilityEnum visibility, final Type type, final Module module,
			final Scope scope, final ConstSubStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.name = name;
		this.type = type;
		this.visibility = visibility;
	}

	@Override
	public void addConstantCall(final ConstantCall constantCall) {
		constantCalls.add(constantCall);
	}

	@Override
	public List<ConstantCall> getConstantCalls() {
		return constantCalls;
	}

	@Override
	public ConstSubStmtContext getCtx() {
		return ctx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public VisibilityEnum getVisibility() {
		return visibility;
	}

	@Override
	public boolean isModuleConstant() {
		return scope != null && scope.equals(module);
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
