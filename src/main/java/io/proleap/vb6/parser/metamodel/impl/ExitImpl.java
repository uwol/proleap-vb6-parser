/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.ExitStmtContext;
import io.proleap.vb6.parser.metamodel.Exit;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;

public class ExitImpl extends VbScopedElementImpl implements Exit {

	protected final ExitStmtContext ctx;

	protected final ExitType exitType;

	public ExitImpl(final ExitType exitType, final Module module, final VbScope scope, final ExitStmtContext ctx) {
		super(module, scope, ctx);

		this.ctx = ctx;
		this.exitType = exitType;
	}

	@Override
	public ExitStmtContext getCtx() {
		return ctx;
	}

	@Override
	public ExitType getExitType() {
		return exitType;
	}

}
