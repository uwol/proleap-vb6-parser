/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt.impl;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.valuestmt.IsValueStmt;

public class IsValueStmtImpl extends BooleanValueStmtImpl implements IsValueStmt {

	public IsValueStmtImpl(final Module module, final Scope superScope, final ValueStmtContext ctx) {
		super(module, superScope, ctx);
	}

}
