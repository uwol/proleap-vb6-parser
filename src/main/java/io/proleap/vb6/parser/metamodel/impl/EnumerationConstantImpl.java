/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.parser.metamodel.Enumeration;
import io.proleap.vb6.parser.metamodel.EnumerationConstant;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class EnumerationConstantImpl extends VbScopedElementImpl implements EnumerationConstant {

	protected final EnumerationStmt_ConstantContext ctx;

	protected final Enumeration enumeration;

	protected final String name;

	protected final int position;

	protected ValueStmt valueStmt;

	public EnumerationConstantImpl(final String name, final int position, final Enumeration enumeration,
			final EnumerationStmt_ConstantContext ctx) {
		super(enumeration.getModule(), enumeration.getSuperScope(), ctx);

		this.ctx = ctx;
		this.enumeration = enumeration;
		this.name = name;
		this.position = position;
	}

	@Override
	public EnumerationStmt_ConstantContext getCtx() {
		return ctx;
	}

	@Override
	public Enumeration getEnumeration() {
		return enumeration;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public Type getType() {
		return enumeration;
	}

	@Override
	public ValueStmt getValueStmt() {
		return valueStmt;
	}

	@Override
	public void setValueStmt(final ValueStmt valueStmt) {
		this.valueStmt = valueStmt;
	}

	@Override
	public String toString() {
		return "name=[" + name + "], enumeration=[" + enumeration + "]";
	}

}
