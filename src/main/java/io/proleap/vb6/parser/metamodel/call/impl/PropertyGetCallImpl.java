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
import io.proleap.vb6.parser.metamodel.PropertyGet;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.PropertyGetCall;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class PropertyGetCallImpl extends CallImpl implements PropertyGetCall {

	protected PropertyGet propertyGet;

	public PropertyGetCallImpl(final String name, final PropertyGet propertyGet, final Module module,
			final VbScope superScope, final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.propertyGet = propertyGet;
	}

	@Override
	public CallType getCallType() {
		return CallType.PropertyGetCall;
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
