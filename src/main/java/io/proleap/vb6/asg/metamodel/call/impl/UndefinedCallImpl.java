/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.type.Type;

public class UndefinedCallImpl extends CallImpl implements Call {

	protected final Type type;

	public UndefinedCallImpl(final String name, final Type type, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.type = type;
	}

	@Override
	public CallType getCallType() {
		return CallType.UNDEFINED_CALL;
	}

	@Override
	public Type getType() {
		return type;
	}

}
