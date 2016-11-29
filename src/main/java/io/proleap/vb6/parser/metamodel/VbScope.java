/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DictionaryCallStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_ProcedureCallContext;
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
import io.proleap.vb6.VisualBasic6Parser.IfConditionStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InBlockContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LineLabelContext;
import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.VisualBasic6Parser.OnErrorStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ResumeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.VisualBasic6Parser.SC_CondContext;
import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
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
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.call.Call.CallContext;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public interface VbScope extends VbScopedElement, Scope {

	ArgValueAssignment addArgValueAssignment(ArgCallContext ctx);

	Call addCall(CallContext callContext, ICS_S_MembersCallContext ctx);

	Call addCall(CallContext callContext, ImplicitCallStmt_InStmtContext ctx);

	Call addCall(ComplexType instanceType, CallContext callContext, ICS_S_MemberCallContext ctx);

	Call addCall(ComplexType instanceType, CallContext callContext,

			ICS_S_VariableOrProcedureCallContext ctx);

	Call addCall(ComplexType instanceType, ICS_S_ProcedureOrArrayCallContext ctx);

	Call addCall(DictionaryCallStmtContext ctx);

	Call addCall(ECS_MemberProcedureCallContext ctx);

	Call addCall(ECS_ProcedureCallContext ctx);

	Call addCall(ExplicitCallStmtContext ctx);

	Call addCall(ICS_B_MemberProcedureCallContext ctx);

	Call addCall(ICS_B_ProcedureCallContext ctx);

	Call addCall(ICS_S_DictionaryCallContext ctx);

	Call addCall(ImplicitCallStmt_InBlockContext ctx);

	Constant addConstant(ConstSubStmtContext ctx);

	Exit addExit(ExitStmtContext ctx);

	ForEach addForEach(ForEachStmtContext ctx);

	ForNext addForNext(ForNextStmtContext ctx);

	IfCondition addIfCondition(IfConditionStmtContext ctx);

	Let addLet(LetStmtContext ctx);

	LineLabel addLineLabel(LineLabelContext ctx);

	Literal addLiteral(LiteralContext ctx);

	OnError addOnError(OnErrorStmtContext ctx);

	ReDim addReDim(RedimSubStmtContext ctx);

	Resume addResume(ResumeStmtContext ctx);

	Select addSelect(SelectCaseStmtContext ctx);

	SelectCase addSelectCase(SC_CaseContext ctx);

	SelectCaseCond addSelectCaseCond(SC_CondContext ctx);

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

	Variable addVariable(VariableSubStmtContext ctx);

	While addWhile(WhileWendStmtContext ctx);

	With addWith(WithStmtContext ctx);

	Constant getConstant(String name);

	List<Statement> getStatements();

	Variable getVariable(String name);

	void registerStatement(Statement statement);

}
