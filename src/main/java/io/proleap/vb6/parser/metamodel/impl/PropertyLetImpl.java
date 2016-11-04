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

import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.PropertyLet;
import io.proleap.vb6.parser.metamodel.call.PropertyLetCall;

public class PropertyLetImpl extends ProcedureImpl implements PropertyLet {

	protected final PropertyLetStmtContext ctx;

	protected final List<PropertyLetCall> propertyLetCalls = new ArrayList<PropertyLetCall>();

	public PropertyLetImpl(final String name, final Module module, final PropertyLetStmtContext ctx) {
		super(name, module, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addPropertyLetCall(final PropertyLetCall propertyLetCall) {
		propertyLetCalls.add(propertyLetCall);
	}

	@Override
	public PropertyLetStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<PropertyLetCall> getPropertyLetCalls() {
		return propertyLetCalls;
	}

	@Override
	public boolean hasReturnType() {
		return false;
	}
}