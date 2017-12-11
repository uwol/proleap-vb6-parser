/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ArgValueAssignmentImpl extends ScopedElementImpl implements ArgValueAssignment {

	protected Arg arg;

	protected ValueStmt assignedValueStmt;

	protected final ArgCallContext ctx;

	public ArgValueAssignmentImpl(final Module module, final Scope scope, final ArgCallContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Arg getArg() {
		return arg;
	}

	@Override
	public ValueStmt getAssignedValueStmt() {
		return assignedValueStmt;
	}

	@Override
	public ArgCallContext getCtx() {
		return ctx;
	}

	@Override
	public Type getType() {
		return assignedValueStmt.getType();
	}

	@Override
	public void setArg(final Arg arg) {
		this.arg = arg;
	}

	@Override
	public void setAssignedValueStmt(final ValueStmt assignedValueStmt) {
		this.assignedValueStmt = assignedValueStmt;
	}

	@Override
	public String toString() {
		return super.toString() + ", arg=[" + arg + "], assignedValueStmt=[" + assignedValueStmt + "]";
	}

}
