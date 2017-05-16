/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.PropertyLetCall;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.type.Type;

public class PropertyLetCallImpl extends CallImpl implements PropertyLetCall {

	protected PropertyLet propertyLet;

	public PropertyLetCallImpl(final String name, final PropertyLet propertyLet, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.propertyLet = propertyLet;
	}

	@Override
	public CallType getCallType() {
		return CallType.PROPERTY_LET_CALL;
	}

	@Override
	public PropertyLet getPropertyLet() {
		return propertyLet;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + ", propertyLet=[" + propertyLet + "]";
	}
}
