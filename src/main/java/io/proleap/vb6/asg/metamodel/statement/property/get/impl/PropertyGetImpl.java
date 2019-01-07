/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.property.get.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.asg.inference.impl.TypeInferenceImpl;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.PropertyGetCall;
import io.proleap.vb6.asg.metamodel.impl.ProcedureImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.type.Type;

public class PropertyGetImpl extends ProcedureImpl implements PropertyGet {

	protected final PropertyGetStmtContext ctx;

	protected boolean isDeclaredAsArray;

	protected final List<PropertyGetCall> propertyGetCalls = new ArrayList<PropertyGetCall>();

	protected final StatementType statementType = StatementTypeEnum.PROPERTY_GET;

	protected final Type type;

	/*
	 * LinkedHashSet, so that entries are ordered by their insertion order, giving
	 * precedence to types declared near to the assigned variable
	 */
	protected Set<Type> typesOfAssignedValues = new LinkedHashSet<Type>();

	public PropertyGetImpl(final String name, final VisibilityEnum visibility, final Type type, final Module module,
			final PropertyGetStmtContext ctx) {
		super(name, visibility, module, ctx);

		this.ctx = ctx;
		this.type = type;
	}

	@Override
	public void addPropertyGetCall(final PropertyGetCall propertyGetCall) {
		propertyGetCalls.add(propertyGetCall);
	}

	@Override
	public void addTypeOfAssignedValue(final Type type) {
		if (type != null) {
			typesOfAssignedValues.add(type);
		}
	}

	@Override
	public List<Call> getCalls() {
		return new ArrayList<>(getPropertyGetCalls());
	}

	@Override
	public PropertyGetStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<PropertyGetCall> getPropertyGetCalls() {
		return propertyGetCalls;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		final Type defType = new TypeInferenceImpl().inferTypeFromDefType(module, name);
		final Type result = new TypeInferenceImpl().inferType(type, defType, typesOfAssignedValues);
		return result;
	}

	@Override
	public Set<Type> getTypesOfAssignedValues() {
		return typesOfAssignedValues;
	}

	@Override
	public boolean hasReturnType() {
		return true;
	}

	@Override
	public boolean isDeclaredAsArray() {
		return isDeclaredAsArray;
	}

	@Override
	public void setDeclaredAsArray(final boolean isDeclaredAsArray) {
		this.isDeclaredAsArray = isDeclaredAsArray;
	}

}
