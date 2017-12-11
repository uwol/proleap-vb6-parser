/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.redim.impl;

import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.redim.ReDim;
import io.proleap.vb6.asg.metamodel.type.Type;

public class ReDimImpl extends ScopedElementImpl implements ReDim {

	protected final RedimSubStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.RE_DIM;

	protected final Variable variable;

	public ReDimImpl(final Variable variable, final Module module, final Scope scope, final RedimSubStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.variable = variable;
	}

	@Override
	public String getName() {
		final String result;

		if (variable != null) {
			result = variable.getName();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public Type getType() {
		final Type result;

		if (variable != null) {
			result = variable.getType();
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public Variable getVariable() {
		return variable;
	}

}
