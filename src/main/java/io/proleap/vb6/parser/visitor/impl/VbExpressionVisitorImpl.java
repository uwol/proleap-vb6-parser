/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Scope;

/**
 * Visitor for analyzing expressions in the AST.
 */
public class VbExpressionVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbExpressionVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitArgCall(@NotNull final VisualBasic6Parser.ArgCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addArgValueAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExitStmt(@NotNull final VisualBasic6Parser.ExitStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExit(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExplicitCallStmt(@NotNull final VisualBasic6Parser.ExplicitCallStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitForEachStmt(@NotNull final VisualBasic6Parser.ForEachStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addForEach(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitForNextStmt(@NotNull final VisualBasic6Parser.ForNextStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addForNext(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitICS_S_MembersCall(@NotNull final VisualBasic6Parser.ICS_S_MembersCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitICS_S_ProcedureOrArrayCall(
			@NotNull final VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitICS_S_VariableOrProcedureCall(
			@NotNull final VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfConditionStmt(@NotNull final VisualBasic6Parser.IfConditionStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addIfCondition(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitImplicitCallStmt_InBlock(
			@NotNull final VisualBasic6Parser.ImplicitCallStmt_InBlockContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitImplicitCallStmt_InStmt(@NotNull final VisualBasic6Parser.ImplicitCallStmt_InStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLetStmt(@NotNull final VisualBasic6Parser.LetStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addLet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLiteral(@NotNull final VisualBasic6Parser.LiteralContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addLiteral(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOnErrorStmt(@NotNull final VisualBasic6Parser.OnErrorStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addOnError(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitResumeStmt(@NotNull final VisualBasic6Parser.ResumeStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addResume(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSC_Case(@NotNull final VisualBasic6Parser.SC_CaseContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSelectCase(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSelectCaseStmt(@NotNull final VisualBasic6Parser.SelectCaseStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSelect(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSetStmt(@NotNull final VisualBasic6Parser.SetStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAdd(@NotNull final VisualBasic6Parser.VsAddContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAddressOf(@NotNull final VisualBasic6Parser.VsAddressOfContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAmp(@NotNull final VisualBasic6Parser.VsAmpContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAnd(@NotNull final VisualBasic6Parser.VsAndContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAssign(@NotNull final VisualBasic6Parser.VsAssignContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsDiv(@NotNull final VisualBasic6Parser.VsDivContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsEq(@NotNull final VisualBasic6Parser.VsEqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsEqv(@NotNull final VisualBasic6Parser.VsEqvContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsGeq(@NotNull final VisualBasic6Parser.VsGeqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsGt(@NotNull final VisualBasic6Parser.VsGtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsICS(@NotNull final VisualBasic6Parser.VsICSContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsImp(@NotNull final VisualBasic6Parser.VsImpContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsIs(@NotNull final VisualBasic6Parser.VsIsContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLeq(@NotNull final VisualBasic6Parser.VsLeqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLike(@NotNull final VisualBasic6Parser.VsLikeContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLiteral(@NotNull final VisualBasic6Parser.VsLiteralContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLt(@NotNull final VisualBasic6Parser.VsLtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMid(@NotNull final VisualBasic6Parser.VsMidContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMinus(@NotNull final VisualBasic6Parser.VsMinusContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMod(@NotNull final VisualBasic6Parser.VsModContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMult(@NotNull final VisualBasic6Parser.VsMultContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNegation(@NotNull final VisualBasic6Parser.VsNegationContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNeq(@NotNull final VisualBasic6Parser.VsNeqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNew(@NotNull final VisualBasic6Parser.VsNewContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNot(@NotNull final VisualBasic6Parser.VsNotContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsOr(@NotNull final VisualBasic6Parser.VsOrContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsPlus(@NotNull final VisualBasic6Parser.VsPlusContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsPow(@NotNull final VisualBasic6Parser.VsPowContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsStruct(@NotNull final VisualBasic6Parser.VsStructContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsTypeOf(@NotNull final VisualBasic6Parser.VsTypeOfContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsXor(@NotNull final VisualBasic6Parser.VsXorContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWhileWendStmt(@NotNull final VisualBasic6Parser.WhileWendStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWhile(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWithStmt(@NotNull final VisualBasic6Parser.WithStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWith(ctx);

		return visitChildren(ctx);
	}

}
