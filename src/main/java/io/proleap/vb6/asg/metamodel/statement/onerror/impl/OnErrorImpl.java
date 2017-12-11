/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.onerror.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;

public class OnErrorImpl extends ScopedElementImpl implements OnError {

	protected LineLabel lineLabel;

	protected final StatementType statementType = StatementTypeEnum.ON_ERROR;

	public OnErrorImpl(final LineLabel lineLabel, final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.lineLabel = lineLabel;
	}

	@Override
	public LineLabel getLineLabel() {
		return lineLabel;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public String toString() {
		return "lineLabel=[" + lineLabel + "]";
	}
}
