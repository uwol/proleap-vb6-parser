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

import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.PropertySet;
import io.proleap.vb6.parser.metamodel.call.PropertySetCall;

public class PropertySetImpl extends ProcedureImpl implements PropertySet {

	protected final PropertySetStmtContext ctx;

	protected final List<PropertySetCall> propertySetCalls = new ArrayList<PropertySetCall>();

	public PropertySetImpl(final String name, final Module module, final PropertySetStmtContext ctx) {
		super(name, module, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addPropertySetCall(final PropertySetCall propertySetCall) {
		propertySetCalls.add(propertySetCall);
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
	public boolean hasReturnType() {
		return false;
	}
}
