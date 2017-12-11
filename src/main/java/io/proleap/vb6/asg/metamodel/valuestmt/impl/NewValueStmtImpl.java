/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.VsNewContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.NewValueStmt;

public class NewValueStmtImpl extends ValueStmtImpl implements NewValueStmt {

	protected final Type type;

	public NewValueStmtImpl(final Type type, final Module module, final Scope scope, final VsNewContext ctx) {
		super(module, scope, ctx);

		this.type = type;
	}

	@Override
	public String getName() {
		return type.getName();
	}

	@Override
	public Type getType() {
		return type;
	}

}
