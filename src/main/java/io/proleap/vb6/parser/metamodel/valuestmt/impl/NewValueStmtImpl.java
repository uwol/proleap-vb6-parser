/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.VsNewContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.NewValueStmt;

public class NewValueStmtImpl extends ValueStmtImpl implements NewValueStmt {

	protected final Type type;

	public NewValueStmtImpl(final Type type, final Module module, final Scope superScope, final VsNewContext ctx) {
		super(module, superScope, ctx);

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
