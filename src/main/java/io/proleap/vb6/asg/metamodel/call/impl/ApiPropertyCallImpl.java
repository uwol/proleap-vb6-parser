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
import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ApiPropertyCallImpl extends CallImpl implements ApiPropertyCall {

	protected final ApiProperty apiProperty;

	public ApiPropertyCallImpl(final String name, final ApiProperty apiProperty, final Module module,
			final Scope scope, final ParserRuleContext ctx) {
		super(name, module, scope, ctx);

		this.apiProperty = apiProperty;
	}

	@Override
	public ApiProperty getApiProperty() {
		return apiProperty;
	}

	@Override
	public CallType getCallType() {
		return CallType.API_PROPERTY_CALL;
	}

	@Override
	public Type getType() {
		final Type result;

		if (apiProperty != null) {
			result = apiProperty.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", apiProperty=[" + apiProperty + "]";
	}

}
