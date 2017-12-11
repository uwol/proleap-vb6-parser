/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.AmbiguousIdentifierContext;
import io.proleap.vb6.VisualBasic6Parser.AppActivateStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.BeepStmtContext;
import io.proleap.vb6.VisualBasic6Parser.BlockIfThenElseContext;
import io.proleap.vb6.VisualBasic6Parser.ChDirStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ChDriveStmtContext;
import io.proleap.vb6.VisualBasic6Parser.CloseStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ConstStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DateStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeftypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeleteSettingStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DictionaryCallStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DoLoopStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_ProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.EventStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ExitStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ExplicitCallStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_B_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_B_ProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_DictionaryCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_MemberCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_MembersCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.IfBlockStmtContext;
import io.proleap.vb6.VisualBasic6Parser.IfConditionStmtContext;
import io.proleap.vb6.VisualBasic6Parser.IfElseBlockStmtContext;
import io.proleap.vb6.VisualBasic6Parser.IfElseIfBlockStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InBlockContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InStmtContext;
import io.proleap.vb6.VisualBasic6Parser.InlineIfThenElseContext;
import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LineLabelContext;
import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.VisualBasic6Parser.OnErrorStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OpenStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PrintStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ResumeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.VisualBasic6Parser.SC_CondContext;
import io.proleap.vb6.VisualBasic6Parser.SC_CondExprContext;
import io.proleap.vb6.VisualBasic6Parser.SaveSettingStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableListStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VsAddContext;
import io.proleap.vb6.VisualBasic6Parser.VsAddressOfContext;
import io.proleap.vb6.VisualBasic6Parser.VsAmpContext;
import io.proleap.vb6.VisualBasic6Parser.VsAndContext;
import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.VisualBasic6Parser.VsDivContext;
import io.proleap.vb6.VisualBasic6Parser.VsEqContext;
import io.proleap.vb6.VisualBasic6Parser.VsEqvContext;
import io.proleap.vb6.VisualBasic6Parser.VsGeqContext;
import io.proleap.vb6.VisualBasic6Parser.VsGtContext;
import io.proleap.vb6.VisualBasic6Parser.VsICSContext;
import io.proleap.vb6.VisualBasic6Parser.VsImpContext;
import io.proleap.vb6.VisualBasic6Parser.VsIsContext;
import io.proleap.vb6.VisualBasic6Parser.VsLeqContext;
import io.proleap.vb6.VisualBasic6Parser.VsLikeContext;
import io.proleap.vb6.VisualBasic6Parser.VsLiteralContext;
import io.proleap.vb6.VisualBasic6Parser.VsLtContext;
import io.proleap.vb6.VisualBasic6Parser.VsMidContext;
import io.proleap.vb6.VisualBasic6Parser.VsMinusContext;
import io.proleap.vb6.VisualBasic6Parser.VsModContext;
import io.proleap.vb6.VisualBasic6Parser.VsMultContext;
import io.proleap.vb6.VisualBasic6Parser.VsNegationContext;
import io.proleap.vb6.VisualBasic6Parser.VsNeqContext;
import io.proleap.vb6.VisualBasic6Parser.VsNewContext;
import io.proleap.vb6.VisualBasic6Parser.VsNotContext;
import io.proleap.vb6.VisualBasic6Parser.VsOrContext;
import io.proleap.vb6.VisualBasic6Parser.VsPlusContext;
import io.proleap.vb6.VisualBasic6Parser.VsPowContext;
import io.proleap.vb6.VisualBasic6Parser.VsStructContext;
import io.proleap.vb6.VisualBasic6Parser.VsTypeOfContext;
import io.proleap.vb6.VisualBasic6Parser.VsXorContext;
import io.proleap.vb6.VisualBasic6Parser.WhileWendStmtContext;
import io.proleap.vb6.VisualBasic6Parser.WithStmtContext;
import io.proleap.vb6.VisualBasic6Parser.WriteStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.statement.appactivate.AppActivate;
import io.proleap.vb6.asg.metamodel.statement.beep.Beep;
import io.proleap.vb6.asg.metamodel.statement.chdir.ChDir;
import io.proleap.vb6.asg.metamodel.statement.chdrive.ChDrive;
import io.proleap.vb6.asg.metamodel.statement.close.Close;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.statement.date.Date;
import io.proleap.vb6.asg.metamodel.statement.deftype.Deftype;
import io.proleap.vb6.asg.metamodel.statement.deletesetting.DeleteSetting;
import io.proleap.vb6.asg.metamodel.statement.doloop.DoLoop;
import io.proleap.vb6.asg.metamodel.statement.event.Event;
import io.proleap.vb6.asg.metamodel.statement.exit.Exit;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.BlockIfThenElse;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseIfBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfCondition;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.InlineIfThenElse;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;
import io.proleap.vb6.asg.metamodel.statement.open.Open;
import io.proleap.vb6.asg.metamodel.statement.print.Print;
import io.proleap.vb6.asg.metamodel.statement.redim.ReDim;
import io.proleap.vb6.asg.metamodel.statement.resume.Resume;
import io.proleap.vb6.asg.metamodel.statement.savesetting.SaveSetting;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.statement.set.Set;
import io.proleap.vb6.asg.metamodel.statement.whilestmt.While;
import io.proleap.vb6.asg.metamodel.statement.with.With;
import io.proleap.vb6.asg.metamodel.statement.write.Write;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public interface Scope extends ScopedElement {

	AppActivate addAppActivate(AppActivateStmtContext ctx);

	ArgValueAssignment addArgValueAssignment(ArgCallContext ctx);

	Beep addBeep(BeepStmtContext ctx);

	BlockIfThenElse addBlockIfThenElse(BlockIfThenElseContext ctx);

	Call addCall(Call instanceCall, ComplexType instanceType, CallContext callContext, boolean isIntermediateMemberCall,
			ICS_S_MemberCallContext ctx);

	Call addCall(Call instanceCall, ComplexType instanceType, CallContext callContext, boolean isIntermediateMemberCall,
			ICS_S_VariableOrProcedureCallContext ctx);

	Call addCall(Call instanceCall, ComplexType instanceType, ICS_S_ProcedureOrArrayCallContext ctx);

	Call addCall(CallContext callContext, ICS_S_MembersCallContext ctx);

	Call addCall(CallContext callContext, ImplicitCallStmt_InStmtContext ctx);

	Call addCall(DictionaryCallStmtContext ctx);

	Call addCall(ECS_MemberProcedureCallContext ctx);

	Call addCall(ECS_ProcedureCallContext ctx);

	Call addCall(ExplicitCallStmtContext ctx);

	Call addCall(ICS_B_MemberProcedureCallContext ctx);

	Call addCall(ICS_B_ProcedureCallContext ctx);

	Call addCall(ICS_S_DictionaryCallContext ctx);

	Call addCall(ImplicitCallStmt_InBlockContext ctx);

	ChDir addChDir(ChDirStmtContext ctx);

	ChDrive addChDrive(ChDriveStmtContext ctx);

	Close addClose(CloseStmtContext ctx);

	Constant addConstant(VisibilityEnum visibility, ConstSubStmtContext ctx);

	void addConstants(ConstStmtContext ctx);

	Date addDate(DateStmtContext ctx);

	Deftype addDeftype(DeftypeStmtContext ctx);

	DeleteSetting addDeleteSetting(DeleteSettingStmtContext ctx);

	DoLoop addDoLoop(DoLoopStmtContext ctx);

	ElementVariable addElementVariable(AmbiguousIdentifierContext ctx);

	ElseBlock addElseBlock(IfElseBlockStmtContext ctx);

	ElseIfBlock addElseIfBlock(IfElseIfBlockStmtContext ctx);

	Event addEvent(EventStmtContext ctx);

	Exit addExit(ExitStmtContext ctx);

	ForEach addForEach(ForEachStmtContext ctx);

	ForNext addForNext(ForNextStmtContext ctx);

	IfBlock addIfBlock(IfBlockStmtContext ctx);

	IfCondition addIfCondition(IfConditionStmtContext ctx);

	InlineIfThenElse addInlineIfThenElse(InlineIfThenElseContext ctx);

	Let addLet(LetStmtContext ctx);

	LineLabel addLineLabel(LineLabelContext ctx);

	Literal addLiteral(LiteralContext ctx);

	OnError addOnError(OnErrorStmtContext ctx);

	Open addOpen(OpenStmtContext ctx);

	Print addPrint(PrintStmtContext ctx);

	ReDim addReDim(RedimSubStmtContext ctx);

	Resume addResume(ResumeStmtContext ctx);

	SaveSetting addSaveSetting(SaveSettingStmtContext ctx);

	Select addSelect(SelectCaseStmtContext ctx);

	SelectCase addSelectCase(SC_CaseContext ctx);

	SelectCaseCond addSelectCaseCond(SC_CondContext ctx);

	SelectCaseCondExpression addSelectCaseCondExpression(SC_CondExprContext ctx);

	Set addSet(SetStmtContext ctx);

	CallValueStmt addValueStmt(CallContext callContext, VsICSContext ctx);

	ValueStmt addValueStmt(ValueStmtContext ctx);

	ValueStmt addValueStmt(VsAddContext ctx);

	ValueStmt addValueStmt(VsAddressOfContext ctx);

	ValueStmt addValueStmt(VsAmpContext ctx);

	ValueStmt addValueStmt(VsAndContext ctx);

	ValueStmt addValueStmt(VsAssignContext ctx);

	ValueStmt addValueStmt(VsDivContext ctx);

	ValueStmt addValueStmt(VsEqContext ctx);

	ValueStmt addValueStmt(VsEqvContext ctx);

	ValueStmt addValueStmt(VsGeqContext ctx);

	ValueStmt addValueStmt(VsGtContext ctx);

	ValueStmt addValueStmt(VsImpContext ctx);

	ValueStmt addValueStmt(VsIsContext ctx);

	ValueStmt addValueStmt(VsLeqContext ctx);

	ValueStmt addValueStmt(VsLikeContext ctx);

	ValueStmt addValueStmt(VsLiteralContext ctx);

	ValueStmt addValueStmt(VsLtContext ctx);

	ValueStmt addValueStmt(VsMidContext ctx);

	ValueStmt addValueStmt(VsMinusContext ctx);

	ValueStmt addValueStmt(VsModContext ctx);

	ValueStmt addValueStmt(VsMultContext ctx);

	ValueStmt addValueStmt(VsNegationContext ctx);

	ValueStmt addValueStmt(VsNeqContext ctx);

	ValueStmt addValueStmt(VsNewContext ctx);

	ValueStmt addValueStmt(VsNotContext ctx);

	ValueStmt addValueStmt(VsOrContext ctx);

	ValueStmt addValueStmt(VsPlusContext ctx);

	ValueStmt addValueStmt(VsPowContext ctx);

	ValueStmt addValueStmt(VsStructContext ctx);

	ValueStmt addValueStmt(VsTypeOfContext ctx);

	ValueStmt addValueStmt(VsXorContext ctx);

	Variable addVariable(VisibilityEnum visibility, VariableSubStmtContext ctx);

	void addVariables(VariableStmtContext ctx);

	void addVariables(VisibilityEnum visibility, VariableListStmtContext ctx);

	While addWhile(WhileWendStmtContext ctx);

	With addWith(WithStmtContext ctx);

	Write addWrite(WriteStmtContext ctx);

	Constant getConstant(String name);

	List<Constant> getConstants();

	List<ScopedElement> getScopedElements();

	List<ScopedElement> getScopedElementsInHierarchy(String name);

	List<ScopedElement> getScopedElementsInScope(String name);

	List<Statement> getStatements();

	List<Scope> getSubScopes();

	Variable getVariable(String name);

	List<Variable> getVariables();

}
