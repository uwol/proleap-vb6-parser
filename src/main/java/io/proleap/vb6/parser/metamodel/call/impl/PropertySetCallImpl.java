/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.PropertySet;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.PropertySetCall;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class PropertySetCallImpl extends CallImpl implements PropertySetCall {

	protected PropertySet propertySet;

	public PropertySetCallImpl(final String name, final PropertySet propertySet, final Module module,
			final VbScope superScope, final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.propertySet = propertySet;
	}

	@Override
	public CallType getCallType() {
		return CallType.PropertySetCall;
	}

	@Override
	public PropertySet getPropertySet() {
		return propertySet;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + ", propertySet=[" + propertySet + "]";
	}
}
