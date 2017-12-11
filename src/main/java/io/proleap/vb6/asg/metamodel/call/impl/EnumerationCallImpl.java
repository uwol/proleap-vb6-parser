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
import io.proleap.vb6.asg.metamodel.call.EnumerationCall;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.type.Type;

public class EnumerationCallImpl extends CallImpl implements EnumerationCall {

	protected Enumeration enumeration;

	public EnumerationCallImpl(final String name, final Enumeration enumeration, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.enumeration = enumeration;
	}

	@Override
	public CallType getCallType() {
		return CallType.ENUMERATION_CALL;
	}

	@Override
	public Enumeration getEnumeration() {
		return enumeration;
	}

	@Override
	public Type getType() {
		final Type result;

		if (enumeration != null) {
			result = enumeration;
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", enumeration=[" + enumeration + "]";
	}
}
