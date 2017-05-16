/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Type;
import io.proleap.vb6.asg.metamodel.TypeElement;

public class TypeImpl extends ScopedElementImpl implements Type {

	protected final String name;

	protected final Map<String, TypeElement> typeElements = new HashMap<String, TypeElement>();

	public TypeImpl(final String name, final Module module, final ParserRuleContext ctx) {
		super(module.getProgram(), module, module, ctx);

		this.name = name;
	}

	@Override
	public void addTypeElement(final TypeElement typeElement) {
		final String typeElementName = typeElement.getName();
		typeElements.put(typeElementName, typeElement);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public TypeElement getTypeElement(final String name) {
		return typeElements.get(name);
	}

	@Override
	public boolean isCollection() {
		return false;
	}

}
