/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.ifstmt.impl;

import io.proleap.vb6.VisualBasic6Parser.IfBlockStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfBlock;

public class IfBlockImpl extends ScopedElementImpl implements IfBlock {

	public IfBlockImpl(final Module module, final Scope scope, final IfBlockStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);
	}

}
