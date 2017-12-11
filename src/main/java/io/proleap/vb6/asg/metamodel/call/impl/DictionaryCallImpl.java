/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import io.proleap.vb6.VisualBasic6Parser.DictionaryCallStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.DictionaryCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class DictionaryCallImpl extends CallImpl implements DictionaryCall {

	public DictionaryCallImpl(final String name, final Module module, final Scope scope,
			final DictionaryCallStmtContext ctx) {
		super(name, module, scope, ctx);
	}

	@Override
	public CallType getCallType() {
		return CallType.DICTIONARY_CALL;
	}

	@Override
	public Type getType() {
		return null;
	}
}
