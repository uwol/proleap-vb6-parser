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
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class UndefinedCallImpl extends CallImpl implements Call {

	protected final Type type;

	public UndefinedCallImpl(final String name, final Type type, final Module module, final VbScope superScope,
			final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.type = type;
	}

	@Override
	public CallType getCallType() {
		return CallType.UndefinedCall;
	}

	@Override
	public Type getType() {
		return type;
	}

}
