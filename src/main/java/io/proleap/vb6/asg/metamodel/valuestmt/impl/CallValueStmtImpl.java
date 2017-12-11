/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.CallValueStmt;

public class CallValueStmtImpl extends ValueStmtImpl implements CallValueStmt {

	protected Call call;

	public CallValueStmtImpl(final Call call, final Module module, final Scope scope,
			final ValueStmtContext ctx) {
		super(module, scope, ctx);

		this.call = call;
	}

	@Override
	public Call getCall() {
		return call;
	}

	@Override
	public Type getType() {
		final Type result;

		if (call != null) {
			result = call.getType();
		} else {
			result = null;
		}

		return result;
	}

}
