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
import io.proleap.vb6.parser.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProperty;

public class ApiPropertyCallImpl extends CallImpl implements ApiPropertyCall {

	protected final ApiProperty apiProperty;

	public ApiPropertyCallImpl(final String name, final ApiProperty apiProperty, final Module module,
			final Scope superScope, final ParseTree ctx) {
		super(name, module, superScope, ctx);

		this.apiProperty = apiProperty;
	}

	@Override
	public ApiProperty getApiProperty() {
		return apiProperty;
	}

	@Override
	public CallType getCallType() {
		return CallType.ApiPropertyCall;
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
