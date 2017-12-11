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

public class StringValueStmtImpl extends ValueStmtImpl implements ValueStmt {

	public StringValueStmtImpl(final Module module, final Scope scope, final ValueStmtContext ctx) {
		super(module, scope, ctx);
	}

	@Override
	public Type getType() {
		return VbBaseType.STRING;
	}

}
