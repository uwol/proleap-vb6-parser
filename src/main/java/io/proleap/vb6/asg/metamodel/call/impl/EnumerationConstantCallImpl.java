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
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.type.Type;

public class EnumerationConstantCallImpl extends CallImpl implements EnumerationConstantCall {

	protected EnumerationConstant enumerationConstant;

	protected boolean standalone;

	public EnumerationConstantCallImpl(final String name, final EnumerationConstant enumerationConstant,
			final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.enumerationConstant = enumerationConstant;
	}

	@Override
	public CallType getCallType() {
		return CallType.ENUMERATION_CONSTANT_CALL;
	}

	@Override
	public EnumerationConstant getEnumerationConstant() {
		return enumerationConstant;
	}

	@Override
	public Type getType() {
		final Type result;

		if (enumerationConstant != null) {
			result = enumerationConstant.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public boolean isStandaloneCall() {
		return standalone;
	}

	@Override
	public void setStandaloneCall(final boolean standalone) {
		this.standalone = standalone;
	}

	@Override
	public String toString() {
		return super.toString() + ", enumerationConstant=[" + enumerationConstant + "]";
	}
}
