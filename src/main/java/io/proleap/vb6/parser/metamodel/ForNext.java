/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public interface ForNext extends VbScope {

	@Override
	ForNextStmtContext getCtx();

	ValueStmt getFrom();

	Variable getIteratorVariable();

	ValueStmt getStep();

	ValueStmt getTo();

	void setFrom(ValueStmt from);

	void setIteratorVariable(Variable variable);

	void setStep(ValueStmt step);

	void setTo(ValueStmt to);
}
