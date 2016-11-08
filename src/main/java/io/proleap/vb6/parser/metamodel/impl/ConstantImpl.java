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

import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.parser.metamodel.Constant;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.ConstantCall;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class ConstantImpl extends VbScopedElementImpl implements Constant {

	protected final List<ConstantCall> constantCalls = new ArrayList<ConstantCall>();

	protected final ConstSubStmtContext ctx;

	protected final String name;

	protected final VbScope scope;

	protected final Type type;

	protected ValueStmt valueStmt;

	public ConstantImpl(final String name, final Type type, final Module module, final VbScope superScope,
			final ConstSubStmtContext ctx) {
		super(module, superScope, ctx);

		this.ctx = ctx;
		this.name = name;
		scope = superScope;
		this.type = type;
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
	public VbScope getSuperScope() {
		return scope;
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
	public boolean isModuleConstant() {
		return superScope != null && superScope.equals(module);
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
