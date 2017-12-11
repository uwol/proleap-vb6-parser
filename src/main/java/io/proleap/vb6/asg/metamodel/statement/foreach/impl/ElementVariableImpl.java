/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.foreach.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.proleap.vb6.VisualBasic6Parser.AmbiguousIdentifierContext;
import io.proleap.vb6.asg.inference.impl.TypeInferenceImpl;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.ElementVariableCall;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ElementVariableImpl extends ScopedElementImpl implements ElementVariable {

	protected final AmbiguousIdentifierContext ctx;

	protected final List<ElementVariableCall> elementVariableCalls = new ArrayList<ElementVariableCall>();

	protected String name;

	protected final Type type;

	/*
	 * LinkedHashSet, so that entries are ordered by their insertion order, giving
	 * precedence to types declared near to the assigned variable
	 */
	protected Set<Type> typesOfAssignedValues = new LinkedHashSet<Type>();

	public ElementVariableImpl(final String name, final Type type, final Module module, final Scope scope,
			final AmbiguousIdentifierContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.name = name;
		this.type = type;
	}

	@Override
	public void addElementVariableCall(final ElementVariableCall elementVariableCall) {
		elementVariableCalls.add(elementVariableCall);
	}

	@Override
	public void addTypeOfAssignedValue(final Type type) {
		if (type != null) {
			typesOfAssignedValues.add(type);
		}
	}

	@Override
	public AmbiguousIdentifierContext getCtx() {
		return ctx;
	}

	@Override
	public List<ElementVariableCall> getElementVariableCalls() {
		return elementVariableCalls;
	}

	@Override
	public String getName() {
		return name;
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
	public String toString() {
		return "name=[" + name + "]";
	}
}
