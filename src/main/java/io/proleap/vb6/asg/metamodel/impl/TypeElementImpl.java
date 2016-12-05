/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.type.Type;

public class TypeElementImpl extends ScopedElementImpl implements TypeElement {

	protected final TypeStmt_ElementContext ctx;

	protected boolean isArray;

	protected boolean isStaticArray;

	protected final String name;

	protected final Type type;

	/*
	 * LinkedHashSet, so that entries are ordered by their insertion order,
	 * giving precedence to types declared near to the assigned variable
	 */
	protected Set<Type> typesOfAssignedValues = new LinkedHashSet<Type>();

	public TypeElementImpl(final String name, final Type type, final Module module, final TypeStmt_ElementContext ctx) {
		super(module, module, ctx);

		this.name = name;
		this.ctx = ctx;
		this.type = type;
	}

	@Override
	public void addTypeOfAssignedValue(final Type type) {
		if (type != null) {
			typesOfAssignedValues.add(type);
		}
	}

	@Override
	public TypeStmt_ElementContext getCtx() {
		return ctx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		final Type defType = VbParserContext.getInstance().getTypeInference().inferTypeFromDefType(module, name);
		final Type result = VbParserContext.getInstance().getTypeInference().inferType(type, defType,
				typesOfAssignedValues);
		return result;
	}

	@Override
	public Set<Type> getTypesOfAssignedValues() {
		return typesOfAssignedValues;
	}

	@Override
	public boolean isDeclaredAsArray() {
		return isArray;
	}

	@Override
	public boolean isDeclaredAsStaticArray() {
		return isStaticArray;
	}

	@Override
	public void setDeclaredAsArray(final boolean isArray) {
		this.isArray = isArray;
	}

	@Override
	public void setDeclaredAsStaticArray(final boolean isStaticArray) {
		this.isStaticArray = isStaticArray;
	}

	@Override
	public String toString() {
		return "name=[" + name + "], type=[" + type + "]";
	}
}
