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
import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ApiEnumerationCallImpl extends CallImpl implements ApiEnumerationCall {

	protected ApiEnumeration apiEnumeration;

	public ApiEnumerationCallImpl(final String name, final ApiEnumeration apiEnumeration, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.apiEnumeration = apiEnumeration;
	}

	@Override
	public ApiEnumeration getApiEnumeration() {
		return apiEnumeration;
	}

	@Override
	public CallType getCallType() {
		return CallType.API_ENUMERATION_CALL;
	}

	@Override
	public Type getType() {
		final Type result;

		if (apiEnumeration != null) {
			result = apiEnumeration;
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", apiEnumeration=[" + apiEnumeration + "]";
	}
}
