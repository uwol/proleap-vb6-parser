/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.type.impl;

import io.proleap.vb6.parser.metamodel.type.Type;

public abstract class TypeImpl implements Type {

	protected final boolean isCollection;

	protected final String name;

	public TypeImpl(final String name, final boolean isCollection) {
		this.name = name;
		this.isCollection = isCollection;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}

	@Override
	public String toString() {
		return name;
	}

}
