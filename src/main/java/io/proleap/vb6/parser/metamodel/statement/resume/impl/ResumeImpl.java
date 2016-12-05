/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.resume.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.LineLabel;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;
import io.proleap.vb6.parser.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.parser.metamodel.statement.StatementType;
import io.proleap.vb6.parser.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.parser.metamodel.statement.resume.Resume;

public class ResumeImpl extends ScopedElementImpl implements Resume {

	protected LineLabel lineLabel;

	protected final StatementType statementType = StatementTypeEnum.Resume;

	public ResumeImpl(final LineLabel lineLabel, final Module module, final Scope scope, final ParseTree ctx) {
		super(module, scope, ctx);

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
