/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.property.set.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.PropertySetCall;
import io.proleap.vb6.asg.metamodel.impl.ProcedureImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;

public class PropertySetImpl extends ProcedureImpl implements PropertySet {

	protected final PropertySetStmtContext ctx;

	protected final List<PropertySetCall> propertySetCalls = new ArrayList<PropertySetCall>();

	protected final StatementType statementType = StatementTypeEnum.PROPERTY_SET;

	public PropertySetImpl(final String name, final VisibilityEnum visibility, final Module module,
			final PropertySetStmtContext ctx) {
		super(name, visibility, module, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addPropertySetCall(final PropertySetCall propertySetCall) {
		propertySetCalls.add(propertySetCall);
	}

	@Override
	public List<Call> getCalls() {
		return new ArrayList<>(getPropertySetCalls());
	}

	@Override
	public PropertySetStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<PropertySetCall> getPropertySetCalls() {
		return propertySetCalls;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public boolean hasReturnType() {
		return false;
	}
}
