/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.asg.inference.impl.TypeInferenceImpl;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.ProcedureDeclaration;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.ArgCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.event.Event;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ArgImpl extends ScopedElementImpl implements Arg {

	protected final List<ArgCall> argCalls = new ArrayList<ArgCall>();

	protected final ArgContext ctx;

	protected Call defaultValueCall;

	protected Event event;

	protected final boolean isOptional;

	protected final String name;

	protected Procedure procedure;

	protected ProcedureDeclaration procedureDeclaration;

	protected final Type type;

	/*
	 * LinkedHashSet, so that entries are ordered by their insertion order, giving
	 * precedence to types declared near to the assigned variable
	 */
	protected Set<Type> typesOfAssignedValues = new LinkedHashSet<Type>();

	public ArgImpl(final String name, final Type type, final boolean isOptional, final Module module, final Scope scope,
			final ArgContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.isOptional = isOptional;
		this.name = name;
		this.type = type;
	}

	@Override
	public void addArgCall(final ArgCall argCall) {
		argCalls.add(argCall);
	}

	@Override
	public void addTypeOfAssignedValue(final Type type) {
		if (type != null) {
			typesOfAssignedValues.add(type);
		}
	}

	@Override
	public List<ArgCall> getArgCalls() {
		return argCalls;
	}

	@Override
	public ArgContext getCtx() {
		return ctx;
	}

	@Override
	public Call getDefaultValueCall() {
		return defaultValueCall;
	}

	@Override
	public Event getEvent() {
		return event;
	}

	@Override
	public Module getModule() {
		return module;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Procedure getProcedure() {
		return procedure;
	}

	@Override
	public ProcedureDeclaration getProcedureDeclaration() {
		return procedureDeclaration;
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
	public boolean isCollection() {
		final Type type = getType();

		final boolean result;

		if (type == null) {
			result = false;
		} else {
			result = type.isCollection();
		}

		return result;
	}

	@Override
	public boolean isOptional() {
		return isOptional;
	}

	@Override
	public void setDefaultValueCall(final Call defaultValueCall) {
		this.defaultValueCall = defaultValueCall;
	}

	@Override
	public void setEvent(final Event event) {
		this.event = event;
	}

	@Override
	public void setProcedure(final Procedure procedure) {
		this.procedure = procedure;
	}

	@Override
	public void setProcedureDeclaration(final ProcedureDeclaration procedureDeclaration) {
		this.procedureDeclaration = procedureDeclaration;
	}

	@Override
	public String toString() {
		return "name=[" + name + "], type=[" + type + "]";
	}
}
