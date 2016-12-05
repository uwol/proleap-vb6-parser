/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Type;
import io.proleap.vb6.asg.metamodel.TypeElement;

public class TypeImpl extends ScopedElementImpl implements Type {

	protected final String name;

	protected final List<TypeElement> typeElements = new ArrayList<TypeElement>();

	public TypeImpl(final String name, final Module module, final ParseTree ctx) {
		super(module, module, ctx);

		this.name = name;
	}

	@Override
	public void addTypeElement(final TypeElement typeElement) {
		typeElements.add(typeElement);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<TypeElement> getTypeElements() {
		return typeElements;
	}

	@Override
	public boolean isCollection() {
		return false;
	}

}
