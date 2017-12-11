/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public class ArithmeticValueStmtImpl extends ValueStmtImpl implements ValueStmt {

	protected final ValueStmt valueStmtLeft;

	protected final ValueStmt valueStmtRight;

	public ArithmeticValueStmtImpl(final ValueStmt valueStmtLeft, final ValueStmt valueStmtRight, final Module module,
			final Scope scope, final ValueStmtContext ctx) {
		super(module, scope, ctx);

		this.valueStmtLeft = valueStmtLeft;
		this.valueStmtRight = valueStmtRight;
	}

	@Override
	public Type getType() {
		final Type typeLeft = valueStmtLeft.getType();
		final Type typeRight = valueStmtRight != null ? valueStmtRight.getType() : null;

		final Type result;

		if (typeLeft == VbBaseType.DOUBLE || typeRight == VbBaseType.DOUBLE) {
			result = VbBaseType.DOUBLE;
		} else if (typeLeft == VbBaseType.SINGLE || typeRight == VbBaseType.SINGLE) {
			result = VbBaseType.SINGLE;
		} else if (typeLeft == VbBaseType.LONG || typeRight == VbBaseType.LONG) {
			result = VbBaseType.LONG;
		} else if (typeLeft == VbBaseType.INTEGER || typeRight == VbBaseType.INTEGER) {
			result = VbBaseType.INTEGER;
		} else {
			result = VbBaseType.INTEGER;
		}

		return result;
	}

}
