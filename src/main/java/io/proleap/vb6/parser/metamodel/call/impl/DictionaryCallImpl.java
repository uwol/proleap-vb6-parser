/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import io.proleap.vb6.VisualBasic6Parser.DictionaryCallStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.DictionaryCall;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class DictionaryCallImpl extends CallImpl implements DictionaryCall {

	public DictionaryCallImpl(final String name, final Module module, final VbScope superScope,
			final DictionaryCallStmtContext ctx) {
		super(name, module, superScope, ctx);
	}

	@Override
	public CallType getCallType() {
		return CallType.DictionaryCall;
	}

	@Override
	public Type getType() {
		return null;
	}
}
