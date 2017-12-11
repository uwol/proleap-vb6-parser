/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.enumeration.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class EnumerationConstantImpl extends ScopedElementImpl implements EnumerationConstant {

	protected final EnumerationStmt_ConstantContext ctx;

	protected final Enumeration enumeration;

	protected final List<EnumerationConstantCall> enumerationConstantCalls = new ArrayList<EnumerationConstantCall>();

	protected final String name;

	protected final int position;

	protected ValueStmt valueStmt;

	public EnumerationConstantImpl(final String name, final int position, final Enumeration enumeration,
			final EnumerationStmt_ConstantContext ctx) {
		super(enumeration.getModule().getProgram(), enumeration.getModule(), enumeration.getScope(), ctx);

		this.ctx = ctx;
		this.enumeration = enumeration;
		this.name = name;
		this.position = position;
	}

	@Override
	public void addEnumerationConstantCall(final EnumerationConstantCall enumerationConstantCall) {
		enumerationConstantCalls.add(enumerationConstantCall);
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
	public List<EnumerationConstantCall> getEnumerationConstantCalls() {
		return enumerationConstantCalls;
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
