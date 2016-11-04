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
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.impl.VbScopedElementImpl;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class CallDelegateImpl extends VbScopedElementImpl implements Call {

	protected final Call delegate;

	public CallDelegateImpl(final Call delegate, final Module module, final Scope superScope, final ParseTree ctx) {
		super(module, superScope, ctx);

		this.delegate = delegate;
	}

	@Override
	public boolean equals(final Object obj) {
		final boolean result;

		if (super.equals(obj)) {
			result = true;
		} else if (obj instanceof Call) {
			final Call otherCall = (Call) obj;
			result = delegate.getName().toLowerCase().equals(otherCall.getName().toLowerCase());
		} else {
			result = false;
		}

		return result;
	}

	@Override
	public CallType getCallType() {
		return delegate.getCallType();
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public Type getType() {
		return delegate.getType();
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

	@Override
	public Call unwrap() {
		return delegate.unwrap();
	}

}
