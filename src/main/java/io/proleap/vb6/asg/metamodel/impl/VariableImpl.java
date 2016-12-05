/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class VariableImpl extends ScopedElementImpl implements Variable {

	protected final VariableSubStmtContext ctx;

	protected boolean isDeclaredAsArray;

	protected boolean isDeclaredAsStaticArray;

	protected boolean isRedimed;

	protected String name;

	protected final Type type;

	/*
	 * LinkedHashSet, so that entries are ordered by their insertion order,
	 * giving precedence to types declared near to the assigned variable
	 */
	protected Set<Type> typesOfAssignedValues = new LinkedHashSet<Type>();

	protected final List<VariableCall> variableCalls = new ArrayList<VariableCall>();

	public VariableImpl(final String name, final Type type, final Module module, final Scope scope,
			final VariableSubStmtContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
		this.name = name;
		this.type = type;
	}

	@Override
	public void addTypeOfAssignedValue(final Type type) {
		if (type != null) {
			typesOfAssignedValues.add(type);
		}
	}

	@Override
	public void addVariableCall(final VariableCall variableCall) {
		variableCalls.add(variableCall);
	}

	@Override
	public VariableSubStmtContext getCtx() {
		return ctx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		final Type defType = VbParserContext.getInstance().getTypeInference().inferTypeFromDefType(module, name);
		final Type result = VbParserContext.getInstance().getTypeInference().inferType(type, defType,
				typesOfAssignedValues);
		return result;
	}

	@Override
	public Set<Type> getTypesOfAssignedValues() {
		return typesOfAssignedValues;
	}

	@Override
	public List<VariableCall> getVariableCalls() {
		return variableCalls;
	}

	@Override
	public boolean isCollection() {
		final boolean result;

		if (isDeclaredAsArray) {
			result = true;
		} else {
			final Type type = getType();

			if (type == null) {
				result = false;
			} else {
				result = type.isCollection();
			}
		}

		return result;
	}

	@Override
	public boolean isDeclaredAsArray() {
		return isDeclaredAsArray;
	}

	@Override
	public boolean isDeclaredAsStaticArray() {
		return isDeclaredAsStaticArray;
	}

	@Override
	public boolean isModuleVariable() {
		return scope != null && scope.equals(module);
	}

	@Override
	public boolean isRedimed() {
		return isRedimed;
	}

	@Override
	public void setDeclaredAsArray(final boolean isDeclaredAsArray) {
		this.isDeclaredAsArray = isDeclaredAsArray;
	}

	@Override
	public void setDeclaredAsStaticArray(final boolean isDeclaredAsStaticArray) {
		this.isDeclaredAsStaticArray = isDeclaredAsStaticArray;
	}

	@Override
	public void setIsRedimed(final boolean isRedimed) {
		this.isRedimed = isRedimed;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
