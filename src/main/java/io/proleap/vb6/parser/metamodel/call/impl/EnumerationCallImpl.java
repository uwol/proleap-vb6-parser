/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.call.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.Enumeration;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.call.EnumerationCall;
import io.proleap.vb6.parser.metamodel.type.Type;

public class EnumerationCallImpl extends CallImpl implements EnumerationCall {

	protected Enumeration enumeration;

	public EnumerationCallImpl(final String name, final Enumeration enumeration, final Module module,
			final Scope scope, final ParseTree ctx) {
		super(name, module, scope, ctx);

		this.enumeration = enumeration;
	}

	@Override
	public CallType getCallType() {
		return CallType.EnumerationCall;
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
