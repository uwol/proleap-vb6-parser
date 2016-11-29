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
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.parser.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.parser.metamodel.type.Type;

public class ApiEnumerationConstantCallImpl extends CallImpl implements ApiEnumerationConstantCall {

	protected ApiEnumerationConstant apiEnumerationConstant;

	protected boolean standalone;

	public ApiEnumerationConstantCallImpl(final String name, final ApiEnumerationConstant apiEnumerationConstant,
			final Module module, final Scope scope, final ParseTree ctx) {
		super(name, module, scope, ctx);

		this.apiEnumerationConstant = apiEnumerationConstant;
	}

	@Override
	public ApiEnumerationConstant getApiEnumerationConstant() {
		return apiEnumerationConstant;
	}

	@Override
	public CallType getCallType() {
		return CallType.ApiEnumerationConstantCall;
	}

	@Override
	public Type getType() {
		final Type result;

		if (apiEnumerationConstant != null) {
			result = apiEnumerationConstant.getType();
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
		return super.toString() + ", apiEnumerationConstant=[" + apiEnumerationConstant + "]";
	}
}
