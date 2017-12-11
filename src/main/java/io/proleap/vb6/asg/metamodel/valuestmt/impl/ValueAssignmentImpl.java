/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ValueAssignmentImpl extends ValueStmtImpl implements ValueAssignment {

	protected Arg arg;

	protected Call assignedVariableCall;

	protected Constant constant;

	protected final VsAssignContext ctx;

	protected PropertyLet propertyLet;

	protected PropertySet propertySet;

	protected ValueStmt rightHandValueStmt;

	protected Variable variable;

	public ValueAssignmentImpl(final Module module, final Scope scope, final VsAssignContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Arg getArg() {
		return arg;
	}

	@Override
	public Call getAssignedVariableCall() {
		return assignedVariableCall;
	}

	@Override
	public Constant getConstant() {
		return constant;
	}

	@Override
	public VsAssignContext getCtx() {
		return ctx;
	}

	@Override
	public PropertyLet getPropertyLet() {
		return propertyLet;
	}

	@Override
	public PropertySet getPropertySet() {
		return propertySet;
	}

	@Override
	public ValueStmt getRightHandValueStmt() {
		return rightHandValueStmt;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public Variable getVariable() {
		return variable;
	}

	@Override
	public void setArg(final Arg arg) {
		this.arg = arg;
	}

	@Override
	public void setAssignedVariableCall(final Call assignedVariableCall) {
		this.assignedVariableCall = assignedVariableCall;
	}

	@Override
	public void setConstant(final Constant constant) {
		this.constant = constant;
	}

	@Override
	public void setPropertyLet(final PropertyLet propertyLet) {
		this.propertyLet = propertyLet;
	}

	@Override
	public void setPropertySet(final PropertySet propertySet) {
		this.propertySet = propertySet;
	}

	@Override
	public void setRightHandValueStmt(final ValueStmt rightHandValueStmt) {
		this.rightHandValueStmt = rightHandValueStmt;
	}

	@Override
	public void setVariable(final Variable variable) {
		this.variable = variable;
	}

}
