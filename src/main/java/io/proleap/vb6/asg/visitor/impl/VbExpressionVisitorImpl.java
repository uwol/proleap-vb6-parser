/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;

/**
 * Visitor for analyzing expressions in the AST.
 */
public class VbExpressionVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbExpressionVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitAppActivateStmt(final VisualBasic6Parser.AppActivateStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addAppActivate(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitArgCall(final VisualBasic6Parser.ArgCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addArgValueAssignment(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitBeepStmt(final VisualBasic6Parser.BeepStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addBeep(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitBlockIfThenElse(final VisualBasic6Parser.BlockIfThenElseContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addBlockIfThenElse(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitChDirStmt(final VisualBasic6Parser.ChDirStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addChDir(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitChDriveStmt(final VisualBasic6Parser.ChDriveStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addChDrive(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCloseStmt(final VisualBasic6Parser.CloseStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addClose(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDateStmt(final VisualBasic6Parser.DateStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDate(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeftypeStmt(final VisualBasic6Parser.DeftypeStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDeftype(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeleteSettingStmt(final VisualBasic6Parser.DeleteSettingStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDeleteSetting(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDoLoopStmt(final VisualBasic6Parser.DoLoopStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addDoLoop(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEventStmt(final VisualBasic6Parser.EventStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addEvent(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExitStmt(final VisualBasic6Parser.ExitStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addExit(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExplicitCallStmt(final VisualBasic6Parser.ExplicitCallStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitForEachStmt(final VisualBasic6Parser.ForEachStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addForEach(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitForNextStmt(final VisualBasic6Parser.ForNextStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addForNext(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitICS_S_MembersCall(final VisualBasic6Parser.ICS_S_MembersCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitICS_S_ProcedureOrArrayCall(final VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitICS_S_VariableOrProcedureCall(
			final VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, null, null, false, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfBlockStmt(final VisualBasic6Parser.IfBlockStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addIfBlock(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfConditionStmt(final VisualBasic6Parser.IfConditionStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addIfCondition(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfElseBlockStmt(final VisualBasic6Parser.IfElseBlockStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addElseBlock(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIfElseIfBlockStmt(final VisualBasic6Parser.IfElseIfBlockStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addElseIfBlock(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitImplicitCallStmt_InBlock(final VisualBasic6Parser.ImplicitCallStmt_InBlockContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitImplicitCallStmt_InStmt(final VisualBasic6Parser.ImplicitCallStmt_InStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addCall(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitInlineIfThenElse(final VisualBasic6Parser.InlineIfThenElseContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addInlineIfThenElse(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLetStmt(final VisualBasic6Parser.LetStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addLet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLiteral(final VisualBasic6Parser.LiteralContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addLiteral(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOnErrorStmt(final VisualBasic6Parser.OnErrorStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addOnError(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOpenStmt(final VisualBasic6Parser.OpenStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addOpen(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPrintStmt(final VisualBasic6Parser.PrintStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addPrint(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitResumeStmt(final VisualBasic6Parser.ResumeStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addResume(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSaveSettingStmt(final VisualBasic6Parser.SaveSettingStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSaveSetting(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSC_Case(final VisualBasic6Parser.SC_CaseContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSelectCase(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSelectCaseStmt(final VisualBasic6Parser.SelectCaseStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSelect(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSetStmt(final VisualBasic6Parser.SetStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addSet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAdd(final VisualBasic6Parser.VsAddContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAddressOf(final VisualBasic6Parser.VsAddressOfContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAmp(final VisualBasic6Parser.VsAmpContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAnd(final VisualBasic6Parser.VsAndContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsAssign(final VisualBasic6Parser.VsAssignContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsDiv(final VisualBasic6Parser.VsDivContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsEq(final VisualBasic6Parser.VsEqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsEqv(final VisualBasic6Parser.VsEqvContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsGeq(final VisualBasic6Parser.VsGeqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsGt(final VisualBasic6Parser.VsGtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsICS(final VisualBasic6Parser.VsICSContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(null, ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsImp(final VisualBasic6Parser.VsImpContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsIs(final VisualBasic6Parser.VsIsContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLeq(final VisualBasic6Parser.VsLeqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLike(final VisualBasic6Parser.VsLikeContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLiteral(final VisualBasic6Parser.VsLiteralContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsLt(final VisualBasic6Parser.VsLtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMid(final VisualBasic6Parser.VsMidContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMinus(final VisualBasic6Parser.VsMinusContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMod(final VisualBasic6Parser.VsModContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsMult(final VisualBasic6Parser.VsMultContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNegation(final VisualBasic6Parser.VsNegationContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNeq(final VisualBasic6Parser.VsNeqContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNew(final VisualBasic6Parser.VsNewContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsNot(final VisualBasic6Parser.VsNotContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsOr(final VisualBasic6Parser.VsOrContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsPlus(final VisualBasic6Parser.VsPlusContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsPow(final VisualBasic6Parser.VsPowContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsStruct(final VisualBasic6Parser.VsStructContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsTypeOf(final VisualBasic6Parser.VsTypeOfContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVsXor(final VisualBasic6Parser.VsXorContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addValueStmt(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWhileWendStmt(final VisualBasic6Parser.WhileWendStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWhile(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWithStmt(final VisualBasic6Parser.WithStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWith(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitWriteStmt(final VisualBasic6Parser.WriteStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addWrite(ctx);

		return visitChildren(ctx);
	}
}
