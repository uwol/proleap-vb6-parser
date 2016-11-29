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
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.type.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class StringValueStmtImpl extends ValueStmtImpl implements ValueStmt {

	public StringValueStmtImpl(final Module module, final Scope scope, final ValueStmtContext ctx) {
		super(module, scope, ctx);
	}

	@Override
	public Type getType() {
		return VbBaseType.STRING;
	}

}
