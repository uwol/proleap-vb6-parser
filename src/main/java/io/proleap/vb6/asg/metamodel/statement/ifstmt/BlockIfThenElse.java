/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.ifstmt;

import java.util.List;

import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Conditionally executes a group of statements, depending on the value of an
 * expression.
 */
public interface BlockIfThenElse extends Statement {

	void addElseIfBlock(ElseIfBlock elseIfBlock);

	ElseBlock getElseBlock();

	List<ElseIfBlock> getElseIfBlocks();

	IfBlock getIfBlock();

	void setElseBlock(ElseBlock elseBlock);

	void setIfBlock(IfBlock ifBlock);

}
