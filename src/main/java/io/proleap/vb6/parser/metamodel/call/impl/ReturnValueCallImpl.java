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
import io.proleap.vb6.parser.metamodel.Procedure;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.call.ReturnValueCall;
import io.proleap.vb6.parser.metamodel.type.Type;
import io.proleap.vb6.parser.metamodel.type.TypedElement;

public class ReturnValueCallImpl extends CallImpl implements ReturnValueCall {

	protected Procedure procedure;

	public ReturnValueCallImpl(final String name, final Procedure procedure, final Module module,
			final Scope scope, final ParseTree ctx) {
		super(name, module, scope, ctx);

		this.procedure = procedure;
	}

	@Override
	public CallType getCallType() {
		return CallType.ReturnValueCall;
	}

	@Override
	public Procedure getProcedure() {
		return procedure;
	}

	@Override
	public Type getType() {
		final Type result;

		if (procedure != null && procedure instanceof TypedElement) {
			result = ((TypedElement) procedure).getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", procedure=[" + procedure + "]";
	}
}
