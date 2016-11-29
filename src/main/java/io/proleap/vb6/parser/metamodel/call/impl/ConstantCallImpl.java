/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Constant;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.call.ConstantCall;
import io.proleap.vb6.parser.metamodel.type.Type;

public class ConstantCallImpl extends CallImpl implements ConstantCall {

	protected Constant constant;

	public ConstantCallImpl(final String name, final Constant constant, final Module module, final Scope scope,
			final ParseTree ctx) {
		super(name, module, scope, ctx);

		this.constant = constant;
	}

	@Override
	public CallType getCallType() {
		return CallType.ConstantCall;
	}

	@Override
	public Constant getConstant() {
		return constant;
	}

	@Override
	public Type getType() {
		final Type result;

		if (constant != null) {
			result = constant.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", constant=[" + constant + "]";
	}
}
