/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.PropertyGetCall;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;

public class PropertyGetCallImpl extends CallImpl implements PropertyGetCall {

	protected List<ArgValueAssignment> argValueAssignments = new ArrayList<ArgValueAssignment>();

	protected PropertyGet propertyGet;

	public PropertyGetCallImpl(final String name, final PropertyGet propertyGet, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.propertyGet = propertyGet;
	}

	@Override
	public void addArgValueAssignment(final ArgValueAssignment argValueAssignment) {
		argValueAssignments.add(argValueAssignment);
	}

	@Override
	public List<ArgValueAssignment> getArgValueAssignments() {
		return argValueAssignments;
	}

	@Override
	public CallType getCallType() {
		return CallType.PROPERTY_GET_CALL;
	}

	@Override
	public PropertyGet getPropertyGet() {
		return propertyGet;
	}

	@Override
	public Type getType() {
		final Type result;

		if (propertyGet != null) {
			result = propertyGet.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", propertyGet=[" + propertyGet + "]";
	}
}
