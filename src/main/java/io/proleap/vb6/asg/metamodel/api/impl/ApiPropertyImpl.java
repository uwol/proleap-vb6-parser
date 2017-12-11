/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.api.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ApiPropertyImpl implements ApiProperty {

	protected final List<ApiPropertyCall> apiPropertyCalls = new ArrayList<ApiPropertyCall>();

	protected final String name;

	protected final Type type;

	public ApiPropertyImpl(final String name, final Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public void addApiPropertyCall(final ApiPropertyCall apiPropertyCall) {
		apiPropertyCalls.add(apiPropertyCall);
	}

	@Override
	public List<ApiPropertyCall> getApiPropertyCalls() {
		return apiPropertyCalls;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
