/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.ifstmt.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.BlockIfThenElseContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.BlockIfThenElse;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseIfBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfBlock;

public class BlockIfThenElseImpl extends ScopedElementImpl implements BlockIfThenElse {

	protected ElseBlock elseBlock;

	protected List<ElseIfBlock> elseIfBlocks = new ArrayList<ElseIfBlock>();

	protected IfBlock ifBlock;

	public BlockIfThenElseImpl(final Module module, final Scope scope, final BlockIfThenElseContext ctx) {
		super(module.getProgram(), module, scope, ctx);
	}

	@Override
	public void addElseIfBlock(final ElseIfBlock elseIfBlock) {
		elseIfBlocks.add(elseIfBlock);
	}

	@Override
	public ElseBlock getElseBlock() {
		return elseBlock;
	}

	@Override
	public List<ElseIfBlock> getElseIfBlocks() {
		return elseIfBlocks;
	}

	@Override
	public IfBlock getIfBlock() {
		return ifBlock;
	}

	@Override
	public StatementType getStatementType() {
		return StatementTypeEnum.BLOCK_IF_THEN_ELSE;
	}

	@Override
	public void setElseBlock(final ElseBlock elseBlock) {
		this.elseBlock = elseBlock;
	}

	@Override
	public void setIfBlock(final IfBlock ifBlock) {
		this.ifBlock = ifBlock;
	}

}
