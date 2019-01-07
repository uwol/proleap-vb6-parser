/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import static io.proleap.vb6.asg.util.CastUtils.castApiEnumeration;
import static io.proleap.vb6.asg.util.CastUtils.castApiEnumerationConstant;
import static io.proleap.vb6.asg.util.CastUtils.castApiModule;
import static io.proleap.vb6.asg.util.CastUtils.castApiProcedure;
import static io.proleap.vb6.asg.util.CastUtils.castApiProperty;
import static io.proleap.vb6.asg.util.CastUtils.castArg;
import static io.proleap.vb6.asg.util.CastUtils.castComplexType;
import static io.proleap.vb6.asg.util.CastUtils.castConst;
import static io.proleap.vb6.asg.util.CastUtils.castElementVariable;
import static io.proleap.vb6.asg.util.CastUtils.castEnumeration;
import static io.proleap.vb6.asg.util.CastUtils.castEnumerationConstant;
import static io.proleap.vb6.asg.util.CastUtils.castFunction;
import static io.proleap.vb6.asg.util.CastUtils.castLineLabel;
import static io.proleap.vb6.asg.util.CastUtils.castModule;
import static io.proleap.vb6.asg.util.CastUtils.castPropertyGet;
import static io.proleap.vb6.asg.util.CastUtils.castPropertyLet;
import static io.proleap.vb6.asg.util.CastUtils.castPropertySet;
import static io.proleap.vb6.asg.util.CastUtils.castSub;
import static io.proleap.vb6.asg.util.CastUtils.castTypeElement;
import static io.proleap.vb6.asg.util.CastUtils.castTypeStmtType;
import static io.proleap.vb6.asg.util.CastUtils.castVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.AmbiguousIdentifierContext;
import io.proleap.vb6.VisualBasic6Parser.AppActivateStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgsCallContext;
import io.proleap.vb6.VisualBasic6Parser.BeepStmtContext;
import io.proleap.vb6.VisualBasic6Parser.BlockIfThenElseContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondElseContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondExprContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondExprIsContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondExprToContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondExprValueContext;
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
import io.proleap.vb6.VisualBasic6Parser.PublicPrivateGlobalVisibilityContext;
import io.proleap.vb6.VisualBasic6Parser.PublicPrivateVisibilityContext;
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
import io.proleap.vb6.VisualBasic6Parser.VisibilityContext;
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
import io.proleap.vb6.asg.inference.impl.TypeAssignmentInferenceImpl;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Declaration;
import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.Literal;
import io.proleap.vb6.asg.metamodel.ModelElement;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.NamedElement;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.api.ApiModule;
import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationCall;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.ApiProcedureCall;
import io.proleap.vb6.asg.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.asg.metamodel.call.ArgCall;
import io.proleap.vb6.asg.metamodel.call.ArgValueAssignmentsContainer;
import io.proleap.vb6.asg.metamodel.call.ArrayElementCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallContext;
import io.proleap.vb6.asg.metamodel.call.ConstantCall;
import io.proleap.vb6.asg.metamodel.call.ElementVariableCall;
import io.proleap.vb6.asg.metamodel.call.EnumerationCall;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.FunctionCall;
import io.proleap.vb6.asg.metamodel.call.MembersCall;
import io.proleap.vb6.asg.metamodel.call.PropertyGetCall;
import io.proleap.vb6.asg.metamodel.call.PropertyLetCall;
import io.proleap.vb6.asg.metamodel.call.PropertySetCall;
import io.proleap.vb6.asg.metamodel.call.ReturnValueCall;
import io.proleap.vb6.asg.metamodel.call.SubCall;
import io.proleap.vb6.asg.metamodel.call.TypeElementCall;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.call.impl.ApiEnumerationCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ApiEnumerationConstantCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ApiProcedureCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ApiPropertyCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ArgCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ArrayElementCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.CallDelegateImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ConstantCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.DictionaryCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ElementVariableCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.EnumerationCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.EnumerationConstantCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.FunctionCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.MeCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.MembersCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ModuleCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.PropertyGetCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.PropertyLetCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.PropertySetCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.ReturnValueCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.SubCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.TypeElementCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.VariableCallImpl;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.statement.appactivate.AppActivate;
import io.proleap.vb6.asg.metamodel.statement.appactivate.impl.AppActivateImpl;
import io.proleap.vb6.asg.metamodel.statement.beep.Beep;
import io.proleap.vb6.asg.metamodel.statement.beep.impl.BeepImpl;
import io.proleap.vb6.asg.metamodel.statement.callstmt.CallStmt;
import io.proleap.vb6.asg.metamodel.statement.callstmt.impl.CallStmtImpl;
import io.proleap.vb6.asg.metamodel.statement.chdir.ChDir;
import io.proleap.vb6.asg.metamodel.statement.chdir.impl.ChDirImpl;
import io.proleap.vb6.asg.metamodel.statement.chdrive.ChDrive;
import io.proleap.vb6.asg.metamodel.statement.chdrive.impl.ChDriveImpl;
import io.proleap.vb6.asg.metamodel.statement.close.Close;
import io.proleap.vb6.asg.metamodel.statement.close.impl.CloseImpl;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.statement.constant.impl.ConstantImpl;
import io.proleap.vb6.asg.metamodel.statement.date.Date;
import io.proleap.vb6.asg.metamodel.statement.date.impl.DateImpl;
import io.proleap.vb6.asg.metamodel.statement.deftype.Deftype;
import io.proleap.vb6.asg.metamodel.statement.deftype.impl.DeftypeImpl;
import io.proleap.vb6.asg.metamodel.statement.deletesetting.DeleteSetting;
import io.proleap.vb6.asg.metamodel.statement.deletesetting.impl.DeleteSettingImpl;
import io.proleap.vb6.asg.metamodel.statement.doloop.DoLoop;
import io.proleap.vb6.asg.metamodel.statement.doloop.impl.DoLoopImpl;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.event.Event;
import io.proleap.vb6.asg.metamodel.statement.event.impl.EventImpl;
import io.proleap.vb6.asg.metamodel.statement.exit.Exit;
import io.proleap.vb6.asg.metamodel.statement.exit.Exit.ExitType;
import io.proleap.vb6.asg.metamodel.statement.exit.impl.ExitImpl;
import io.proleap.vb6.asg.metamodel.statement.explicitcallstmt.ExplicitCallStmt;
import io.proleap.vb6.asg.metamodel.statement.explicitcallstmt.impl.ExplicitCallStmtImpl;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.metamodel.statement.foreach.impl.ElementVariableImpl;
import io.proleap.vb6.asg.metamodel.statement.foreach.impl.ForEachImpl;
import io.proleap.vb6.asg.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.asg.metamodel.statement.fornext.impl.ForNextImpl;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.BlockIfThenElse;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseIfBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfBlock;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfCondition;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.InlineIfThenElse;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.impl.BlockIfThenElseImpl;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.impl.ElseBlockImpl;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.impl.ElseIfBlockImpl;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.impl.IfBlockImpl;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.impl.IfConditionImpl;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.impl.InlineIfThenElseImpl;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.statement.let.impl.LetImpl;
import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;
import io.proleap.vb6.asg.metamodel.statement.onerror.impl.OnErrorImpl;
import io.proleap.vb6.asg.metamodel.statement.open.Open;
import io.proleap.vb6.asg.metamodel.statement.open.impl.OpenImpl;
import io.proleap.vb6.asg.metamodel.statement.print.Print;
import io.proleap.vb6.asg.metamodel.statement.print.impl.PrintImpl;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.asg.metamodel.statement.redim.ReDim;
import io.proleap.vb6.asg.metamodel.statement.redim.impl.ReDimImpl;
import io.proleap.vb6.asg.metamodel.statement.resume.Resume;
import io.proleap.vb6.asg.metamodel.statement.resume.impl.ResumeImpl;
import io.proleap.vb6.asg.metamodel.statement.savesetting.SaveSetting;
import io.proleap.vb6.asg.metamodel.statement.savesetting.impl.SaveSettingImpl;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond.SelectCaseCondType;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression.SelectCaseCondExpressionType;
import io.proleap.vb6.asg.metamodel.statement.select.impl.SelectCaseCondExpressionImpl;
import io.proleap.vb6.asg.metamodel.statement.select.impl.SelectCaseCondImpl;
import io.proleap.vb6.asg.metamodel.statement.select.impl.SelectCaseImpl;
import io.proleap.vb6.asg.metamodel.statement.select.impl.SelectImpl;
import io.proleap.vb6.asg.metamodel.statement.set.Set;
import io.proleap.vb6.asg.metamodel.statement.set.impl.SetImpl;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.statement.whilestmt.While;
import io.proleap.vb6.asg.metamodel.statement.whilestmt.impl.WhileImpl;
import io.proleap.vb6.asg.metamodel.statement.with.With;
import io.proleap.vb6.asg.metamodel.statement.with.impl.WithImpl;
import io.proleap.vb6.asg.metamodel.statement.write.Write;
import io.proleap.vb6.asg.metamodel.statement.write.impl.WriteImpl;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.NewValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.ArgValueAssignmentImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.ArithmeticValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.BooleanValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.CallValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.EqValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.IsValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.NewValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.NotValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.StringValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.StructValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.ValueAssignmentImpl;
import io.proleap.vb6.asg.resolver.impl.NameResolverImpl;
import io.proleap.vb6.asg.resolver.impl.TypeResolverImpl;
import io.proleap.vb6.asg.util.ANTLRUtils;

public abstract class ScopeImpl extends ScopedElementImpl implements Scope {

	private final static Logger LOG = LoggerFactory.getLogger(ScopeImpl.class);

	public final static String ME = "ME";

	protected Map<String, Constant> constants = new HashMap<String, Constant>();

	protected final List<ScopedElement> scopedElements = new ArrayList<ScopedElement>();

	protected final Map<String, List<ScopedElement>> scopedElementsSymbolTable = new LinkedHashMap<String, List<ScopedElement>>();

	protected List<Statement> statements = new ArrayList<Statement>();

	protected Map<String, Variable> variables = new HashMap<String, Variable>();

	public ScopeImpl(final Program program, final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(program, module, scope, ctx);
	}

	@Override
	public AppActivate addAppActivate(final AppActivateStmtContext ctx) {
		AppActivate result = (AppActivate) getASGElement(ctx);

		if (result == null) {
			result = new AppActivateImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ArgValueAssignment addArgValueAssignment(final ArgCallContext ctx) {
		ArgValueAssignment result = (ArgValueAssignment) getASGElement(ctx);

		if (result == null) {
			result = new ArgValueAssignmentImpl(module, this, ctx);

			final ValueStmt assignedValueStmt = addValueStmt(ctx.valueStmt());
			result.setAssignedValueStmt(assignedValueStmt);

			registerScopedElement(result);
		}

		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, module.getProgram());

		return result;
	}

	@Override
	public Beep addBeep(final BeepStmtContext ctx) {
		Beep result = (Beep) getASGElement(ctx);

		if (result == null) {
			result = new BeepImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public BlockIfThenElse addBlockIfThenElse(final BlockIfThenElseContext ctx) {
		BlockIfThenElse result = (BlockIfThenElse) getASGElement(ctx);

		if (result == null) {
			result = new BlockIfThenElseImpl(module, this, ctx);

			if (ctx.ifBlockStmt() != null) {
				final IfBlock ifBlock = addIfBlock(ctx.ifBlockStmt());
				result.setIfBlock(ifBlock);
			}

			for (final IfElseIfBlockStmtContext ifElseIfBlockStmtContext : ctx.ifElseIfBlockStmt()) {
				final ElseIfBlock elseIfBlock = addElseIfBlock(ifElseIfBlockStmtContext);
				result.addElseIfBlock(elseIfBlock);
			}

			if (ctx.ifElseBlockStmt() != null) {
				final ElseBlock elseBlock = addElseBlock(ctx.ifElseBlockStmt());
				result.setElseBlock(elseBlock);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final Call instanceCall, final ComplexType instanceType, final CallContext callContext,
			final boolean isIntermediateMemberCall, final ICS_S_MemberCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall;

			if (ctx.iCS_S_VariableOrProcedureCall() != null) {
				delegatedCall = addCall(instanceCall, instanceType, callContext, isIntermediateMemberCall,
						ctx.iCS_S_VariableOrProcedureCall());
			} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
				delegatedCall = addCall(instanceCall, instanceType, ctx.iCS_S_ProcedureOrArrayCall());
			} else {
				delegatedCall = null;
			}

			result = new CallDelegateImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final Call instanceCall, final ComplexType instanceType, final CallContext callContext,
			final boolean isIntermediateMemberCall, final ICS_S_VariableOrProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			/*
			 * instead of regular variables, standard modules can be called for calling
			 * further members on them -> check for calls on standard modules
			 */
			final StandardModule calledModule = module.getProgram().getStandardModule(name);

			if (calledModule != null) {
				result = new ModuleCallImpl(name, calledModule, module, this, ctx);
			} else if (ME.equals(name.toUpperCase())) {
				result = new MeCallImpl(name, module, this, ctx);
			} else {
				/*
				 * determine referenced element, i. e. array variable or function
				 */
				final List<ModelElement> referencedProgramElements = getElements(instanceCall, instanceType, name);

				final Arg arg = castArg(referencedProgramElements);
				final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);
				final ApiProperty apiProperty = castApiProperty(referencedProgramElements);
				final ApiEnumeration apiEnumeration = castApiEnumeration(referencedProgramElements);
				final ApiEnumerationConstant apiEnumerationConstant = castApiEnumerationConstant(
						referencedProgramElements);
				final Constant constant = castConst(referencedProgramElements);
				final ElementVariable elementVariable = castElementVariable(referencedProgramElements);
				final Enumeration enumeration = castEnumeration(referencedProgramElements);
				final EnumerationConstant enumerationConstant = castEnumerationConstant(referencedProgramElements);
				final Function function = castFunction(referencedProgramElements);
				final PropertyGet propertyGet = castPropertyGet(referencedProgramElements);
				final PropertyLet propertyLet = castPropertyLet(referencedProgramElements);
				final PropertySet propertySet = castPropertySet(referencedProgramElements);
				final Sub sub = castSub(referencedProgramElements);
				final TypeElement typeElement = castTypeElement(referencedProgramElements);
				final Variable variable = castVariable(referencedProgramElements);

				/*
				 * potentially, this let sets a return variable of a function or property get
				 */
				final Function scopeFunction = this.findScope(Function.class);
				final boolean hasScopeFunctionName = scopeFunction == null ? false
						: scopeFunction.getName().equals(name);

				final PropertyGet scopePropertyGet = this.findScope(PropertyGet.class);
				final boolean hasScopePropertyGetName = scopePropertyGet == null ? false
						: scopePropertyGet.getName().equals(name);

				final boolean isLeftHandSideCall = CallContext.LET_LEFT_HAND_SIDE.equals(callContext);

				if (instanceType == null && hasScopeFunctionName) {
					final ReturnValueCall returnValueCall = new ReturnValueCallImpl(name, scopeFunction, module, this,
							ctx);

					result = returnValueCall;
				} else if (instanceType == null && hasScopePropertyGetName) {
					final ReturnValueCall returnValueCall = new ReturnValueCallImpl(name, scopePropertyGet, module,
							this, ctx);

					result = returnValueCall;
				} else if (elementVariable != null) {
					final ElementVariableCall elementVariableCall = new ElementVariableCallImpl(name, elementVariable,
							module, this, ctx);

					linkElementVariableCallWithElementVariable(elementVariableCall, elementVariable);

					result = elementVariableCall;
				} else if (variable != null && getModule().equals(variable.getModule())) {
					final VariableCall variableCall = new VariableCallImpl(name, variable, module, this, ctx);

					linkVariableCallWithVariable(variableCall, variable);

					result = variableCall;
				} else if (constant != null && getModule().equals(constant.getModule())) {
					final ConstantCall constantCall = new ConstantCallImpl(name, constant, module, this, ctx);

					linkConstantCallWithConstant(constantCall, constant);

					result = constantCall;
				} else if (arg != null) {
					// sic!, precedence is after constants and variables: arg
					// values can be overwritten by constant and variables
					final ArgCall argCall = new ArgCallImpl(name, arg, module, this, ctx);

					linkArgCallWithArg(argCall, arg);

					result = argCall;
				} else if (enumerationConstant != null) {
					final EnumerationConstantCall enumerationConstantCall = new EnumerationConstantCallImpl(name,
							enumerationConstant, module, this, ctx);

					linkEnumerationConstantCallWithEnumerationConstant(enumerationConstantCall, enumerationConstant);

					final boolean isStandalone = instanceType == null;
					enumerationConstantCall.setStandaloneCall(isStandalone);

					result = enumerationConstantCall;
				} else if (variable != null) {
					final VariableCall variableCall = new VariableCallImpl(name, variable, module, this, ctx);

					linkVariableCallWithVariable(variableCall, variable);

					result = variableCall;
				} else if (constant != null) {
					final ConstantCall constantCall = new ConstantCallImpl(name, constant, module, this, ctx);

					linkConstantCallWithConstant(constantCall, constant);

					result = constantCall;
				} else if (propertyGet != null && (!isLeftHandSideCall || isIntermediateMemberCall)) {
					final PropertyGetCall propertyGetCall = new PropertyGetCallImpl(name, propertyGet, module, this,
							ctx);

					linkPropertyGetCallWithPropertyGet(propertyGetCall, propertyGet, (ArgsCallContext) null);

					result = propertyGetCall;
				} else if (propertyLet != null && isLeftHandSideCall && !isIntermediateMemberCall) {
					final PropertyLetCall properyLetCall = new PropertyLetCallImpl(name, propertyLet, module, this,
							ctx);

					linkPropertyLetCallWithPropertySet(properyLetCall, propertyLet, null);

					result = properyLetCall;
				} else if (propertySet != null && isLeftHandSideCall && !isIntermediateMemberCall) {
					final PropertySetCall propertySetCall = new PropertySetCallImpl(name, propertySet, module, this,
							ctx);

					linkPropertySetCallWithPropertySet(propertySetCall, propertySet, null);

					result = propertySetCall;
				} else if (typeElement != null) {
					final TypeElementCall typeElementCall = new TypeElementCallImpl(name, typeElement, module, this,
							ctx);

					linkTypeElementCallWithTypeElement(typeElementCall, typeElement);

					result = typeElementCall;
				} else if (function != null && (!isLeftHandSideCall || isIntermediateMemberCall)) {
					final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

					linkFunctionCallWithFunction(functionCall, function, (ArgsCallContext) null);

					result = functionCall;
				} else if (sub != null && (!isLeftHandSideCall || isIntermediateMemberCall)) {
					final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

					linkSubCallWithSub(subCall, sub, null);

					result = subCall;
				} else if (enumeration != null) {
					final EnumerationCall enumerationCall = new EnumerationCallImpl(name, enumeration, module, this,
							ctx);

					linkEnumerationCallWithEnumeration(enumerationCall, enumeration);

					result = enumerationCall;
				} else if (apiProcedure != null) {
					final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
							ctx);

					linkApiProcedureCallWithApiProcedure(apiProcedureCall, apiProcedure);

					result = apiProcedureCall;
				} else if (apiProperty != null) {
					final ApiPropertyCall apiPropertyCall = new ApiPropertyCallImpl(name, apiProperty, module, this,
							ctx);

					linkApiPropertyCallWithApiProperty(apiPropertyCall, apiProperty);

					result = apiPropertyCall;
				} else if (apiEnumeration != null) {
					final ApiEnumerationCall apiEnumerationCall = new ApiEnumerationCallImpl(name, apiEnumeration,
							module, this, ctx);

					linkApiEnumerationCallWithApiEnumeration(apiEnumerationCall, apiEnumeration);

					result = apiEnumerationCall;
				} else if (apiEnumerationConstant != null) {
					final ApiEnumerationConstantCall apiEnumerationConstantCall = new ApiEnumerationConstantCallImpl(
							name, apiEnumerationConstant, module, this, ctx);

					linkApiEnumerationConstantCallWithApiEnumerationConstant(apiEnumerationConstantCall,
							apiEnumerationConstant);

					final boolean isStandalone = instanceType == null;
					apiEnumerationConstantCall.setStandaloneCall(isStandalone);

					result = apiEnumerationConstantCall;
				} else {
					LOG.debug("Call to unknown element {}.", name);
					result = new UndefinedCallImpl(name, null, module, this, ctx);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final Call instanceCall, final ComplexType instanceType,
			final ICS_S_ProcedureOrArrayCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			/*
			 * determine referenced element, i. e. array variable or function
			 */
			final List<ModelElement> referencedProgramElements = getElements(instanceCall, instanceType, name);

			final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);
			final ApiProperty apiProperty = castApiProperty(referencedProgramElements);
			final Arg arg = castArg(referencedProgramElements);
			final Function function = castFunction(referencedProgramElements);
			final Variable variable = castVariable(referencedProgramElements);
			final PropertyGet propertyGet = castPropertyGet(referencedProgramElements);

			final boolean isCollection;

			if (arg != null) {
				isCollection = arg.isCollection();
			} else if (variable != null) {
				isCollection = variable.isCollection();
			} else {
				isCollection = false;
			}

			/*
			 * create call model element
			 */
			if (isCollection) {
				final ArrayElementCall arrayElementCall = new ArrayElementCallImpl(name, variable, module, this, ctx);

				linkArrayElementCallWithVariable(arrayElementCall, variable);

				result = arrayElementCall;
			} else {
				if (function != null) {
					final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

					linkFunctionCallWithFunction(functionCall, function, ctx.argsCall());

					result = functionCall;
				} else if (propertyGet != null) {
					final PropertyGetCall propertyGetCall = new PropertyGetCallImpl(name, propertyGet, module, this,
							ctx);

					linkPropertyGetCallWithPropertyGet(propertyGetCall, propertyGet, ctx.argsCall());

					result = propertyGetCall;
				} else if (apiProcedure != null) {
					final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
							ctx);

					linkApiProcedureCallWithApiProcedure(apiProcedureCall, apiProcedure);

					result = apiProcedureCall;
				} else if (apiProperty != null) {
					final ApiPropertyCall apiPropertyCall = new ApiPropertyCallImpl(name, apiProperty, module, this,
							ctx);

					linkApiPropertyCallWithApiProperty(apiPropertyCall, apiProperty);

					result = apiPropertyCall;
				} else if (arg != null) {
					final ArgCall argCall = new ArgCallImpl(name, arg, module, this, ctx);

					linkArgCallWithArg(argCall, arg);

					result = argCall;
				} else {
					LOG.debug("Call to unknown element {}.", name);
					result = new UndefinedCallImpl(name, null, module, this, ctx);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final CallContext callContext, final ImplicitCallStmt_InStmtContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall;

			if (ctx.iCS_S_VariableOrProcedureCall() != null) {
				delegatedCall = addCall(null, null, callContext, false, ctx.iCS_S_VariableOrProcedureCall());
			} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
				delegatedCall = addCall(null, null, ctx.iCS_S_ProcedureOrArrayCall());
			} else if (ctx.iCS_S_MembersCall() != null) {
				delegatedCall = addCall(callContext, ctx.iCS_S_MembersCall());
			} else if (ctx.iCS_S_DictionaryCall() != null) {
				delegatedCall = addCall(ctx.iCS_S_DictionaryCall());
			} else {
				LOG.warn("Unknown implicit call {}.", ctx);
				delegatedCall = null;
			}

			result = new CallDelegateImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final CallContext callContext, final VisualBasic6Parser.ICS_S_MembersCallContext ctx) {
		MembersCall result = (MembersCall) getASGElement(ctx);

		if (result == null) {
			result = new MembersCallImpl(module, this, ctx);

			Call instanceCall = null;
			ComplexType instanceType = null;

			if (ctx.iCS_S_VariableOrProcedureCall() != null) {
				instanceCall = addCall(null, null, callContext, true, ctx.iCS_S_VariableOrProcedureCall());
				instanceType = castComplexType(instanceCall.getType());

				result.addSubCall(instanceCall);
			} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
				instanceCall = addCall(null, null, ctx.iCS_S_ProcedureOrArrayCall());
				instanceType = castComplexType(instanceCall.getType());

				result.addSubCall(instanceCall);
			} else if (this instanceof With) {
				final With with = (With) this;
				instanceCall = with.getWithVariableCall();
				instanceType = castComplexType(instanceCall.getType());
			} else {
				final With with = (With) ANTLRUtils.findParent(With.class, ctx, program.getASGElementRegistry());

				if (with != null) {
					instanceCall = with.getWithVariableCall();
					instanceType = castComplexType(instanceCall.getType());
				}
			}

			final int numberOfMemberCalls = ctx.iCS_S_MemberCall().size();
			int currentMemberCall = 0;

			for (final ICS_S_MemberCallContext memberCallContext : ctx.iCS_S_MemberCall()) {
				final boolean isIntermediateMemberCall = currentMemberCall < numberOfMemberCalls - 1;

				instanceCall = addCall(instanceCall, instanceType, callContext, isIntermediateMemberCall,
						memberCallContext);
				instanceType = castComplexType(instanceCall.getType());

				result.addSubCall(instanceCall);

				currentMemberCall++;
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final DictionaryCallStmtContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DictionaryCallImpl(name, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ECS_MemberProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			Call instanceCall = null;
			ComplexType instanceType = null;

			if (ctx.implicitCallStmt_InStmt() != null) {
				instanceCall = addCall(null, ctx.implicitCallStmt_InStmt());
				instanceType = castComplexType(instanceCall.getType());
			} else if (this instanceof With) {
				final With with = (With) this;
				instanceCall = with.getWithVariableCall();
				instanceType = castComplexType(instanceCall.getType());
			} else {
				final With with = (With) ANTLRUtils.findParent(With.class, ctx, program.getASGElementRegistry());

				if (with != null) {
					instanceCall = with.getWithVariableCall();
					instanceType = castComplexType(instanceCall.getType());
				}
			}

			final Module instanceModule = castModule(instanceType);

			final Sub sub;
			final Function function;
			final ApiProcedure apiProcedure;

			if (instanceModule != null) {
				sub = instanceModule.getSub(name);
				function = instanceModule.getFunction(name);
				apiProcedure = null;
			} else {
				final List<ModelElement> referencedProgramElements = getElements(instanceCall, instanceType, name);

				sub = castSub(referencedProgramElements);
				function = castFunction(referencedProgramElements);
				apiProcedure = castApiProcedure(referencedProgramElements);
			}

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				linkSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				linkFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
						ctx);

				linkApiProcedureCallWithApiProcedure(apiProcedureCall, apiProcedure);

				result = apiProcedureCall;
			} else {
				result = new UndefinedCallImpl(name, null, module, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ECS_ProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			final List<ModelElement> referencedProgramElements = getElements(null, null, name);

			final Sub sub = castSub(referencedProgramElements);
			final Function function = castFunction(referencedProgramElements);
			final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				linkSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				linkFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
						ctx);

				linkApiProcedureCallWithApiProcedure(apiProcedureCall, apiProcedure);

				result = apiProcedureCall;
			} else {
				result = new UndefinedCallImpl(name, null, module, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ExplicitCallStmtContext ctx) {
		ExplicitCallStmt result = (ExplicitCallStmt) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall;

			if (ctx.eCS_ProcedureCall() != null) {
				delegatedCall = addCall(ctx.eCS_ProcedureCall());
			} else if (ctx.eCS_MemberProcedureCall() != null) {
				delegatedCall = addCall(ctx.eCS_MemberProcedureCall());
			} else {
				delegatedCall = null;
			}

			result = new ExplicitCallStmtImpl(delegatedCall, module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ICS_B_MemberProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			Call instanceCall = null;
			ComplexType instanceType = null;

			if (ctx.implicitCallStmt_InStmt() != null) {
				instanceCall = addCall(null, ctx.implicitCallStmt_InStmt());
				instanceType = castComplexType(instanceCall.getType());
			} else if (this instanceof With) {
				final With with = (With) this;
				instanceCall = with.getWithVariableCall();
				instanceType = castComplexType(instanceCall.getType());
			} else {
				final With with = (With) ANTLRUtils.findParent(With.class, ctx, program.getASGElementRegistry());

				if (with != null) {
					instanceCall = with.getWithVariableCall();
					instanceType = castComplexType(instanceCall.getType());
				}
			}

			final Module instanceModule = castModule(instanceType);

			final Sub sub;
			final Function function;
			final ApiProcedure apiProcedure;

			if (instanceModule != null) {
				sub = instanceModule.getSub(name);
				function = instanceModule.getFunction(name);
				apiProcedure = null;
			} else {
				final List<ModelElement> referencedProgramElements = getElements(instanceCall, instanceType, name);

				sub = castSub(referencedProgramElements);
				function = castFunction(referencedProgramElements);
				apiProcedure = castApiProcedure(referencedProgramElements);
			}

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				linkSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				linkFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
						ctx);

				linkApiProcedureCallWithApiProcedure(apiProcedureCall, apiProcedure);

				result = apiProcedureCall;
			} else {
				result = new UndefinedCallImpl(name, null, module, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ICS_B_ProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			final List<ModelElement> referencedProgramElements = getElements(null, null, name);

			final Sub sub = castSub(referencedProgramElements);
			final Function function = castFunction(referencedProgramElements);
			final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				linkSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				linkFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
						ctx);

				linkApiProcedureCallWithApiProcedure(apiProcedureCall, apiProcedure);

				result = apiProcedureCall;
			} else {
				result = new UndefinedCallImpl(name, null, module, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ICS_S_DictionaryCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall = addCall(ctx.dictionaryCallStmt());
			result = new CallDelegateImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ImplicitCallStmt_InBlockContext ctx) {
		CallStmt result = (CallStmt) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall;

			if (ctx.iCS_B_ProcedureCall() != null) {
				delegatedCall = addCall(ctx.iCS_B_ProcedureCall());
			} else if (ctx.iCS_B_MemberProcedureCall() != null) {
				delegatedCall = addCall(ctx.iCS_B_MemberProcedureCall());
			} else {
				LOG.warn("Unknown implicit call {}.", ctx);
				delegatedCall = null;
			}

			result = new CallStmtImpl(delegatedCall, module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ChDir addChDir(final ChDirStmtContext ctx) {
		ChDir result = (ChDir) getASGElement(ctx);

		if (result == null) {
			result = new ChDirImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ChDrive addChDrive(final ChDriveStmtContext ctx) {
		ChDrive result = (ChDrive) getASGElement(ctx);

		if (result == null) {
			result = new ChDriveImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Close addClose(final CloseStmtContext ctx) {
		Close result = (Close) getASGElement(ctx);

		if (result == null) {
			result = new CloseImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Constant addConstant(final VisibilityEnum visibility, final ConstSubStmtContext ctx) {
		Constant result = (Constant) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new ConstantImpl(name, visibility, type, module, this, ctx);

			final ValueStmt valueStmt = addValueStmt(ctx.valueStmt());
			result.setValueStmt(valueStmt);

			registerStatement(result);
			constants.put(getSymbol(name), result);
		}

		return result;
	}

	@Override
	public void addConstants(final ConstStmtContext ctx) {
		final VisibilityEnum visibility = determineVisibility(ctx.publicPrivateGlobalVisibility());

		for (final ConstSubStmtContext constSubStmtContext : ctx.constSubStmt()) {
			addConstant(visibility, constSubStmtContext);
		}
	}

	@Override
	public Date addDate(final DateStmtContext ctx) {
		Date result = (Date) getASGElement(ctx);

		if (result == null) {
			result = new DateImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Deftype addDeftype(final DeftypeStmtContext ctx) {
		Deftype result = (Deftype) getASGElement(ctx);

		if (result == null) {
			result = new DeftypeImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DeleteSetting addDeleteSetting(final DeleteSettingStmtContext ctx) {
		DeleteSetting result = (DeleteSetting) getASGElement(ctx);

		if (result == null) {
			result = new DeleteSettingImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DoLoop addDoLoop(final DoLoopStmtContext ctx) {
		DoLoop result = (DoLoop) getASGElement(ctx);

		if (result == null) {
			result = new DoLoopImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ElementVariable addElementVariable(final AmbiguousIdentifierContext ctx) {
		ElementVariable result = (ElementVariable) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new ElementVariableImpl(name, type, module, this, ctx);

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public ElseBlock addElseBlock(final IfElseBlockStmtContext ctx) {
		ElseBlock result = (ElseBlock) getASGElement(ctx);

		if (result == null) {
			result = new ElseBlockImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ElseIfBlock addElseIfBlock(final IfElseIfBlockStmtContext ctx) {
		ElseIfBlock result = (ElseIfBlock) getASGElement(ctx);

		if (result == null) {
			result = new ElseIfBlockImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Event addEvent(final EventStmtContext ctx) {
		Event result = (Event) getASGElement(ctx);

		if (result == null) {
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());
			result = new EventImpl(visibility, module, this, ctx);

			registerStatement(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}
		}

		return result;
	}

	@Override
	public Exit addExit(final ExitStmtContext ctx) {
		Exit result = (Exit) getASGElement(ctx);

		if (result == null) {
			final ExitType exitType;

			if (ctx.EXIT_DO() != null) {
				exitType = ExitType.Do;
			} else if (ctx.EXIT_FOR() != null) {
				exitType = ExitType.For;
			} else if (ctx.EXIT_FUNCTION() != null) {
				exitType = ExitType.Function;
			} else if (ctx.EXIT_PROPERTY() != null) {
				exitType = ExitType.Property;
			} else if (ctx.EXIT_SUB() != null) {
				exitType = ExitType.Sub;
			} else {
				exitType = null;
			}

			result = new ExitImpl(exitType, module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ForEach addForEach(final ForEachStmtContext ctx) {
		ForEach result = (ForEach) getASGElement(ctx);

		if (result == null) {
			result = new ForEachImpl(module, this, ctx);

			final ElementVariable elementVariable = addElementVariable(ctx.ambiguousIdentifier(0));
			result.setElementVariable(elementVariable);

			// in
			if (ctx.valueStmt() != null) {
				final ValueStmt in = addValueStmt(ctx.valueStmt());
				result.setIn(in);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ForNext addForNext(final ForNextStmtContext ctx) {
		ForNext result = (ForNext) getASGElement(ctx);

		if (result == null) {
			result = new ForNextImpl(module, this, ctx);

			final Call counterCall = addCall(null, null, null, false, ctx.iCS_S_VariableOrProcedureCall());
			result.setCounterCall(counterCall);

			// from
			if (!ctx.valueStmt().isEmpty()) {
				final ValueStmt from = addValueStmt(ctx.valueStmt(0));
				result.setFrom(from);
			}

			// to
			if (ctx.valueStmt().size() > 1) {
				final ValueStmt to = addValueStmt(ctx.valueStmt(1));
				result.setTo(to);
			}

			// step
			if (ctx.valueStmt().size() > 2) {
				final ValueStmt step = addValueStmt(ctx.valueStmt(2));
				result.setStep(step);
			}

			registerStatement(result);
		}

		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, module.getProgram());

		return result;
	}

	@Override
	public IfBlock addIfBlock(final IfBlockStmtContext ctx) {
		IfBlock result = (IfBlock) getASGElement(ctx);

		if (result == null) {
			result = new IfBlockImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IfCondition addIfCondition(final IfConditionStmtContext ctx) {
		IfCondition result = (IfCondition) getASGElement(ctx);

		if (result == null) {
			result = new IfConditionImpl(module, module, ctx);

			final ValueStmt valueStmt = addValueStmt(ctx.valueStmt());
			result.setValueStmt(valueStmt);

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public InlineIfThenElse addInlineIfThenElse(final InlineIfThenElseContext ctx) {
		InlineIfThenElse result = (InlineIfThenElse) getASGElement(ctx);

		if (result == null) {
			result = new InlineIfThenElseImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	/**
	 * @see #addSet(SetStmtContext)
	 */
	@Override
	public Let addLet(final LetStmtContext ctx) {
		Let result = (Let) getASGElement(ctx);

		if (result == null) {
			result = new LetImpl(module, this, ctx);

			final ImplicitCallStmt_InStmtContext implicitCallStmt = ctx.implicitCallStmt_InStmt();
			final ValueStmtContext valueStmt = ctx.valueStmt();

			// left hand side call
			final Call leftHandCall = addCall(CallContext.LET_LEFT_HAND_SIDE, implicitCallStmt);
			result.setLeftHandCall(leftHandCall);

			// right hand side call
			final ValueStmt rightHandValueStmt = addValueStmt(valueStmt);
			result.setRightHandValueStmt(rightHandValueStmt);

			registerStatement(result);
		}

		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, module.getProgram());

		return result;
	}

	@Override
	public LineLabel addLineLabel(final LineLabelContext ctx) {
		LineLabel result = (LineLabel) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new LineLabelImpl(name, module, this, ctx);

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public Literal addLiteral(final LiteralContext ctx) {
		Literal result = (Literal) getASGElement(ctx);

		if (result == null) {
			final Type type = determineType(ctx);
			final String value = ctx.getText().replace("\"", "");

			result = new LiteralImpl(type, value, module, this, ctx);
			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public OnError addOnError(final OnErrorStmtContext ctx) {
		OnError result = (OnError) getASGElement(ctx);

		if (result == null) {
			final String lineLabelName = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(null, null, lineLabelName);

			final LineLabel lineLabel = castLineLabel(referencedProgramElements);

			result = new OnErrorImpl(lineLabel, module, this, ctx);

			if (lineLabel != null) {
				lineLabel.addOnError(result);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Open addOpen(final OpenStmtContext ctx) {
		Open result = (Open) getASGElement(ctx);

		if (result == null) {
			result = new OpenImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Print addPrint(final PrintStmtContext ctx) {
		Print result = (Print) getASGElement(ctx);

		if (result == null) {
			result = new PrintImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReDim addReDim(final RedimSubStmtContext ctx) {
		ReDim result = (ReDim) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(null, null, name);
			final Variable variable = castVariable(referencedProgramElements);

			result = new ReDimImpl(variable, module, this, ctx);

			registerStatement(result);
		}

		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, module.getProgram());

		return result;
	}

	@Override
	public Resume addResume(final ResumeStmtContext ctx) {
		Resume result = (Resume) getASGElement(ctx);

		if (result == null) {
			final String lineLabelName = determineName(ctx);

			final List<ModelElement> referencedProgramElements = getElements(null, null, lineLabelName);

			final LineLabel lineLabel = castLineLabel(referencedProgramElements);

			result = new ResumeImpl(lineLabel, module, this, ctx);

			if (lineLabel != null) {
				lineLabel.addResume(result);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SaveSetting addSaveSetting(final SaveSettingStmtContext ctx) {
		SaveSetting result = (SaveSetting) getASGElement(ctx);

		if (result == null) {
			result = new SaveSettingImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Select addSelect(final SelectCaseStmtContext ctx) {
		Select result = (Select) getASGElement(ctx);

		if (result == null) {
			result = new SelectImpl(module, this, ctx);

			// value stmt
			final ValueStmt valueStmt = addValueStmt(ctx.valueStmt());
			result.setValueStmt(valueStmt);

			// add cases of select
			for (final SC_CaseContext sc_case : ctx.sC_Case()) {
				final SelectCase selectCase = addSelectCase(sc_case);

				selectCase.setSelect(result);
				result.addSelectCase(selectCase);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SelectCase addSelectCase(final SC_CaseContext ctx) {
		SelectCase result = (SelectCase) getASGElement(ctx);

		if (result == null) {
			result = new SelectCaseImpl(module, this, ctx);

			// condition
			final SelectCaseCond selectCaseCond = addSelectCaseCond(ctx.sC_Cond());

			selectCaseCond.setSelectCase(result);
			result.setSelectCaseCond(selectCaseCond);

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public SelectCaseCond addSelectCaseCond(final SC_CondContext ctx) {
		SelectCaseCond result = (SelectCaseCond) getASGElement(ctx);

		if (result == null) {
			final SelectCaseCondType selectCaseCondType;

			if (ctx instanceof CaseCondElseContext) {
				selectCaseCondType = SelectCaseCondType.ELSE;
			} else if (ctx instanceof CaseCondExprContext) {
				selectCaseCondType = SelectCaseCondType.EXPRESSION;
			} else {
				selectCaseCondType = null;
			}

			result = new SelectCaseCondImpl(selectCaseCondType, module, this, ctx);

			if (SelectCaseCondType.EXPRESSION.equals(selectCaseCondType)) {
				final CaseCondExprContext condExprContext = (CaseCondExprContext) ctx;

				for (final SC_CondExprContext sc_CondExprContext : condExprContext.sC_CondExpr()) {
					final SelectCaseCondExpression selectCaseCondExpression = addSelectCaseCondExpression(
							sc_CondExprContext);

					result.addSelectCaseCondExpression(selectCaseCondExpression);
					selectCaseCondExpression.setSelectCaseCond(result);
				}
			}

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public SelectCaseCondExpression addSelectCaseCondExpression(final SC_CondExprContext ctx) {
		SelectCaseCondExpression result = (SelectCaseCondExpression) getASGElement(ctx);

		if (result == null) {
			final SelectCaseCondExpressionType selectCaseCondExpressionType;

			if (ctx instanceof CaseCondExprIsContext) {
				selectCaseCondExpressionType = SelectCaseCondExpressionType.IS;
			} else if (ctx instanceof CaseCondExprValueContext) {
				selectCaseCondExpressionType = SelectCaseCondExpressionType.VALUE;
			} else if (ctx instanceof CaseCondExprToContext) {
				selectCaseCondExpressionType = SelectCaseCondExpressionType.TO;
			} else {
				selectCaseCondExpressionType = null;
			}

			result = new SelectCaseCondExpressionImpl(selectCaseCondExpressionType, module, this, ctx);

			if (SelectCaseCondExpressionType.IS.equals(selectCaseCondExpressionType)) {
				final CaseCondExprIsContext condIsCtx = (CaseCondExprIsContext) ctx;
				final ValueStmt valueStmt = addValueStmt(condIsCtx.valueStmt());

				result.addValueStmt(valueStmt);
			} else if (SelectCaseCondExpressionType.VALUE.equals(selectCaseCondExpressionType)) {
				final CaseCondExprValueContext condValueCtx = (CaseCondExprValueContext) ctx;

				final ValueStmt valueStmt = addValueStmt(condValueCtx.valueStmt());
				result.addValueStmt(valueStmt);
			} else if (SelectCaseCondExpressionType.TO.equals(selectCaseCondExpressionType)) {
				final CaseCondExprToContext condToCtx = (CaseCondExprToContext) ctx;

				for (final ValueStmtContext valueCtx : condToCtx.valueStmt()) {
					final ValueStmt valueStmt = addValueStmt(valueCtx);
					result.addValueStmt(valueStmt);
				}
			}

			registerScopedElement(result);
		}

		return result;
	}

	/**
	 * @see #addLet(LetStmtContext)
	 */
	@Override
	public Set addSet(final SetStmtContext ctx) {
		Set result = (Set) getASGElement(ctx);

		if (result == null) {
			result = new SetImpl(module, this, ctx);

			final ImplicitCallStmt_InStmtContext implicitCallStmt = ctx.implicitCallStmt_InStmt();
			final ValueStmtContext valueStmt = ctx.valueStmt();

			// left hand side call
			final Call leftHandCall = addCall(CallContext.LET_LEFT_HAND_SIDE, implicitCallStmt);
			result.setLeftHandCall(leftHandCall);

			// right hand side call
			final ValueStmt rightHandValueStmt = addValueStmt(valueStmt);
			result.setRightHandValueStmt(rightHandValueStmt);

			registerStatement(result);
		}

		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, module.getProgram());

		return result;
	}

	@Override
	public CallValueStmt addValueStmt(final CallContext callContext, final VsICSContext ctx) {
		CallValueStmt result = (CallValueStmt) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall = addCall(callContext, ctx.implicitCallStmt_InStmt());

			result = new CallValueStmtImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final ValueStmtContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			if (ctx == null) {
				result = null;
			} else if (ctx instanceof VsLiteralContext) {
				result = addValueStmt((VsLiteralContext) ctx);
			} else if (ctx instanceof VsICSContext) {
				result = addValueStmt(null, (VsICSContext) ctx);
			} else if (ctx instanceof VsStructContext) {
				result = addValueStmt((VsStructContext) ctx);
			} else if (ctx instanceof VsNewContext) {
				result = addValueStmt((VsNewContext) ctx);
			} else if (ctx instanceof VsTypeOfContext) {
				result = addValueStmt((VsTypeOfContext) ctx);
			} else if (ctx instanceof VsMidContext) {
				result = addValueStmt((VsMidContext) ctx);
			} else if (ctx instanceof VsAddressOfContext) {
				result = addValueStmt((VsAddressOfContext) ctx);
			} else if (ctx instanceof VsAssignContext) {
				result = addValueStmt((VsAssignContext) ctx);
			} else if (ctx instanceof VsIsContext) {
				result = addValueStmt((VsIsContext) ctx);
			} else if (ctx instanceof VsLikeContext) {
				result = addValueStmt((VsLikeContext) ctx);
			} else if (ctx instanceof VsGeqContext) {
				result = addValueStmt((VsGeqContext) ctx);
			} else if (ctx instanceof VsLeqContext) {
				result = addValueStmt((VsLeqContext) ctx);
			} else if (ctx instanceof VsGtContext) {
				result = addValueStmt((VsGtContext) ctx);
			} else if (ctx instanceof VsLtContext) {
				result = addValueStmt((VsLtContext) ctx);
			} else if (ctx instanceof VsNeqContext) {
				result = addValueStmt((VsNeqContext) ctx);
			} else if (ctx instanceof VsEqContext) {
				result = addValueStmt((VsEqContext) ctx);
			} else if (ctx instanceof VsAmpContext) {
				result = addValueStmt((VsAmpContext) ctx);
			} else if (ctx instanceof VsNeqContext) {
				result = addValueStmt((VsNeqContext) ctx);
			} else if (ctx instanceof VsEqContext) {
				result = addValueStmt((VsEqContext) ctx);
			} else if (ctx instanceof VsAmpContext) {
				result = addValueStmt((VsAmpContext) ctx);
			} else if (ctx instanceof VsNegationContext) {
				result = addValueStmt((VsNegationContext) ctx);
			} else if (ctx instanceof VsPlusContext) {
				result = addValueStmt((VsPlusContext) ctx);
			} else if (ctx instanceof VsAddContext) {
				result = addValueStmt((VsAddContext) ctx);
			} else if (ctx instanceof VsModContext) {
				result = addValueStmt((VsModContext) ctx);
			} else if (ctx instanceof VsDivContext) {
				result = addValueStmt((VsDivContext) ctx);
			} else if (ctx instanceof VsMultContext) {
				result = addValueStmt((VsMultContext) ctx);
			} else if (ctx instanceof VsMinusContext) {
				result = addValueStmt((VsMinusContext) ctx);
			} else if (ctx instanceof VsPowContext) {
				result = addValueStmt((VsPowContext) ctx);
			} else if (ctx instanceof VsImpContext) {
				result = addValueStmt((VsImpContext) ctx);
			} else if (ctx instanceof VsEqvContext) {
				result = addValueStmt((VsEqvContext) ctx);
			} else if (ctx instanceof VsXorContext) {
				result = addValueStmt((VsXorContext) ctx);
			} else if (ctx instanceof VsOrContext) {
				result = addValueStmt((VsOrContext) ctx);
			} else if (ctx instanceof VsAndContext) {
				result = addValueStmt((VsAndContext) ctx);
			} else if (ctx instanceof VsNotContext) {
				result = addValueStmt((VsNotContext) ctx);
			} else {
				result = null;
			}
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsAddContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt(0));
			final ValueStmt delegatedValueStmtRight = addValueStmt(ctx.valueStmt(1));

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, delegatedValueStmtRight, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsAddressOfContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new StringValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsAmpContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new StringValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsAndContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueAssignment addValueStmt(final VsAssignContext ctx) {
		ValueAssignment result = (ValueAssignment) getASGElement(ctx);

		if (result == null) {
			result = new ValueAssignmentImpl(module, this, ctx);

			/*
			 * identify name of assigned variable
			 */
			final String assignedVariableName = determineName(ctx.implicitCallStmt_InStmt());

			/*
			 * identify model element of assigned variable
			 */
			final List<ModelElement> referencedProgramElements = getElements(null, null, assignedVariableName);

			/*
			 * identify typed model element of assigned variable
			 */
			final Arg arg = castArg(referencedProgramElements);
			final Constant constant = castConst(referencedProgramElements);
			final Variable variable = castVariable(referencedProgramElements);

			final PropertyLet propertyLet = module.getPropertyLet(assignedVariableName);
			final PropertySet propertySet = module.getPropertySet(assignedVariableName);

			result.setArg(arg);
			result.setConstant(constant);
			result.setPropertyLet(propertyLet);
			result.setPropertySet(propertySet);
			result.setVariable(variable);

			// create model element for variable call ctx of assigned variable
			final Call assignedVariableCall = addCall(null, ctx.implicitCallStmt_InStmt());

			/*
			 * associate value assignment model element with variable call model element
			 */
			result.setAssignedVariableCall(assignedVariableCall);

			// right hand side call
			final ValueStmt rightHandValueStmt = addValueStmt(ctx.valueStmt());
			result.setRightHandValueStmt(rightHandValueStmt);

			registerASGElement(result);
		}

		new TypeAssignmentInferenceImpl().addTypeAssignment(ctx, module.getProgram());

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsDivContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt(0));
			final ValueStmt delegatedValueStmtRight = addValueStmt(ctx.valueStmt(1));

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, delegatedValueStmtRight, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsEqContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt subValueStmt0 = addValueStmt(ctx.valueStmt(0));
			final ValueStmt subValueStmt1 = addValueStmt(ctx.valueStmt(1));

			result = new EqValueStmtImpl(module, this, ctx);
			result.addSubValueStmt(subValueStmt0);
			result.addSubValueStmt(subValueStmt1);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsEqvContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsGeqContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsGtContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsImpContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsIsContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt subValueStmt0 = addValueStmt(ctx.valueStmt(0));
			final ValueStmt subValueStmt1 = addValueStmt(ctx.valueStmt(1));

			result = new IsValueStmtImpl(module, this, ctx);
			result.addSubValueStmt(subValueStmt0);
			result.addSubValueStmt(subValueStmt1);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsLeqContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsLikeContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsLiteralContext ctx) {
		LiteralValueStmt result = (LiteralValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new LiteralValueStmtImpl(module, this, ctx);

			registerASGElement(result);

			final Literal literal = addLiteral(ctx.literal());
			result.setLiteral(literal);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsLtContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsMidContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new StringValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsMinusContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt(0));
			final ValueStmt delegatedValueStmtRight = addValueStmt(ctx.valueStmt(1));

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, delegatedValueStmtRight, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsModContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt(0));
			final ValueStmt delegatedValueStmtRight = addValueStmt(ctx.valueStmt(1));

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, delegatedValueStmtRight, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsMultContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt(0));
			final ValueStmt delegatedValueStmtRight = addValueStmt(ctx.valueStmt(1));

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, delegatedValueStmtRight, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsNegationContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt());

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, null, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsNeqContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsNewContext ctx) {
		NewValueStmt result = (NewValueStmt) getASGElement(ctx);

		if (result == null) {
			final Type type = determineType(ctx);

			assert type != null : "null type for " + ctx.getText();

			result = new NewValueStmtImpl(type, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsNotContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt subValueStmt = addValueStmt(ctx.valueStmt());

			result = new NotValueStmtImpl(module, this, ctx);
			result.addSubValueStmt(subValueStmt);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsOrContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsPlusContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			final ValueStmt delegatedValueStmtLeft = addValueStmt(ctx.valueStmt());

			result = new ArithmeticValueStmtImpl(delegatedValueStmtLeft, null, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsPowContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsStructContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new StructValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsTypeOfContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final VsXorContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new BooleanValueStmtImpl(module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Variable addVariable(final VisibilityEnum visibility, final VariableSubStmtContext ctx) {
		Variable result = (Variable) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new VariableImpl(name, visibility, type, module, this, ctx);

			final boolean isArray = ctx.LPAREN() != null && ctx.RPAREN() != null;
			final boolean isStaticArray = isArray && ctx.subscripts() != null;

			result.setDeclaredAsArray(isArray);
			result.setDeclaredAsStaticArray(isStaticArray);

			registerScopedElement(result);
			variables.put(getSymbol(name), result);
		}

		return result;
	}

	@Override
	public void addVariables(final VariableStmtContext ctx) {
		final VisibilityEnum visibility = determineVisibility(ctx.visibility());
		addVariables(visibility, ctx.variableListStmt());
	}

	@Override
	public void addVariables(final VisibilityEnum visibility, final VariableListStmtContext ctx) {
		for (final VariableSubStmtContext variableSubStmtContext : ctx.variableSubStmt()) {
			addVariable(visibility, variableSubStmtContext);
		}
	}

	@Override
	public While addWhile(final WhileWendStmtContext ctx) {
		While result = (While) getASGElement(ctx);

		if (result == null) {
			result = new WhileImpl(module, this, ctx);

			final ValueStmt condition = addValueStmt(ctx.valueStmt());
			result.setCondition(condition);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public With addWith(final WithStmtContext ctx) {
		With result = (With) getASGElement(ctx);

		if (result == null) {
			result = new WithImpl(module, this, ctx);

			// value stmt
			final Call call = addCall(null, ctx.implicitCallStmt_InStmt());
			result.setWithVariableCall(call);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Write addWrite(final WriteStmtContext ctx) {
		Write result = (Write) getASGElement(ctx);

		if (result == null) {
			result = new WriteImpl(module, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	protected String determineName(final ParserRuleContext ctx) {
		return new NameResolverImpl().determineName(ctx);
	}

	protected Type determineType(final ParserRuleContext ctx) {
		final Program program = module.getProgram();
		return new TypeResolverImpl().determineType(ctx, program);
	}

	protected VisibilityEnum determineVisibility(final PublicPrivateGlobalVisibilityContext visibility) {
		final VisibilityEnum result;

		if (visibility == null) {
			result = null;
		} else if (visibility.PRIVATE() != null) {
			result = VisibilityEnum.PRIVATE;
		} else if (visibility.GLOBAL() != null) {
			result = VisibilityEnum.GLOBAL;
		} else {
			result = VisibilityEnum.PUBLIC;
		}

		return result;
	}

	protected VisibilityEnum determineVisibility(final PublicPrivateVisibilityContext visibility) {
		final VisibilityEnum result;

		if (visibility == null) {
			result = null;
		} else if (visibility.PRIVATE() != null) {
			result = VisibilityEnum.PRIVATE;
		} else {
			result = VisibilityEnum.PUBLIC;
		}

		return result;
	}

	protected VisibilityEnum determineVisibility(final VisibilityContext visibility) {
		final VisibilityEnum result;

		if (visibility == null) {
			result = null;
		} else if (visibility.PRIVATE() != null) {
			result = VisibilityEnum.PRIVATE;
		} else if (visibility.FRIEND() != null) {
			result = VisibilityEnum.FRIEND;
		} else if (visibility.GLOBAL() != null) {
			result = VisibilityEnum.GLOBAL;
		} else {
			result = VisibilityEnum.PUBLIC;
		}

		return result;
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final Program program = module.getProgram();
		final ASGElement result = program.getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	public Constant getConstant(final String name) {
		return constants.get(getSymbol(name));
	}

	@Override
	public List<Constant> getConstants() {
		return new ArrayList<>(constants.values());
	}

	/**
	 * searches elements of the program by their name such as functions, variables,
	 * api procedures etc.
	 */
	protected List<ModelElement> getElements(final Call instanceCall, final ComplexType instanceType,
			final String name) {
		final Module instanceModule = castModule(instanceType);
		final Enumeration instanceEnumeration = castEnumeration(instanceType);
		final io.proleap.vb6.asg.metamodel.Type instanceTypeStmtType = castTypeStmtType(instanceType);
		final ApiModule instanceApiModule = castApiModule(instanceType);
		final ApiEnumeration instanceApiEnumeration = castApiEnumeration(instanceType);

		final List<ModelElement> referencedProgramElements = new ArrayList<ModelElement>();

		if (name == null) {
		} else if (instanceType != null) {
			// if a module instance is given
			if (instanceModule != null) {
				final List<ScopedElement> instanceModuleScopedElements = instanceModule.getScopedElementsInScope(name);

				if (instanceModuleScopedElements != null) {
					referencedProgramElements.addAll(instanceModuleScopedElements);
				}
			}
			// if a enumeration is given
			else if (instanceEnumeration != null) {
				final EnumerationConstant enumerationConstant = instanceEnumeration.getEnumerationConstant(name);

				if (enumerationConstant != null) {
					referencedProgramElements.add(enumerationConstant);
				}
			}
			// if a type stmt type is given
			else if (instanceTypeStmtType != null) {
				final TypeElement instanceTypeElement = instanceTypeStmtType.getTypeElement(name);

				if (instanceTypeElement != null) {
					referencedProgramElements.add(instanceTypeElement);
				}
			}
			// if an api module instance is given
			else if (instanceApiModule != null) {
				final ApiProcedure apiProcedure = instanceApiModule.getApiProcedure(name);
				final ApiProperty apiProperty = instanceApiModule.getApiProperty(name);

				referencedProgramElements.add(apiProcedure);
				referencedProgramElements.add(apiProperty);
			}
			// if an api enumeration instance is given
			else if (instanceApiEnumeration != null) {
				final ApiEnumerationConstant apiEnumerationConstant = instanceApiEnumeration
						.getApiEnumerationConstant(name);

				referencedProgramElements.add(apiEnumerationConstant);
			} else {
				LOG.warn("Could not resolve instance type {}.", instanceType);
			}
		} else if (instanceCall == null) {
			// search globally in the program for elements with that name
			final List<ScopedElement> globalProgramElements = getScopedElementsInHierarchy(name);

			final ApiProcedure apiProcedure = module.getProgram().getApiProcedureRegistry().getApiProcedure(name);
			final ApiProperty apiProperty = module.getProgram().getApiPropertyRegistry().getApiProperty(name);
			final ApiEnumerationConstant apiEnumerationConstant = module.getProgram().getApiEnumerationRegistry()
					.getApiEnumerationConstant(name);
			final EnumerationConstant enumerationConstant = module.getProgram().getEnumerationRegistry()
					.getEnumerationConstant(name);

			if (globalProgramElements != null) {
				referencedProgramElements.addAll(globalProgramElements);
			}

			referencedProgramElements.add(apiProcedure);
			referencedProgramElements.add(apiProperty);
			referencedProgramElements.add(apiEnumerationConstant);
			referencedProgramElements.add(enumerationConstant);
		}

		while (referencedProgramElements.contains(null)) {
			referencedProgramElements.remove(null);
		}

		return referencedProgramElements;
	}

	@Override
	public Module getModule() {
		return module;
	}

	@Override
	public List<ScopedElement> getScopedElements() {
		return scopedElements;
	}

	@Override
	public List<ScopedElement> getScopedElementsInHierarchy(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			final List<ScopedElement> scopedElementInScope = getScopedElementsInScope(name);

			if (scopedElementInScope != null) {
				result = scopedElementInScope;
			} else if (scope != null) {
				result = scope.getScopedElementsInHierarchy(name);
			} else {
				result = null;
			}
		}

		return result;
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			final List<ScopedElement> scopedElementInScope = scopedElementsSymbolTable.get(getSymbol(name));

			result = scopedElementInScope;
		}

		return result;
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public List<Scope> getSubScopes() {
		final List<Scope> result = new ArrayList<Scope>();

		for (final ScopedElement scopedElement : scopedElements) {
			if (scopedElement instanceof Scope) {
				final Scope scope = (Scope) scopedElement;
				result.add(scope);
			}
		}

		return result;
	}

	@Override
	public Variable getVariable(final String name) {
		return variables.get(getSymbol(name));
	}

	@Override
	public List<Variable> getVariables() {
		return new ArrayList<Variable>(variables.values());
	}

	protected void linkApiEnumerationCallWithApiEnumeration(final ApiEnumerationCall apiEnumerationCall,
			final ApiEnumeration apiEnumeration) {
		apiEnumeration.addApiEnumerationCall(apiEnumerationCall);
	}

	protected void linkApiEnumerationConstantCallWithApiEnumerationConstant(
			final ApiEnumerationConstantCall apiEnumerationConstantCall,
			final ApiEnumerationConstant apiEnumerationConstant) {
		apiEnumerationConstant.addApiEnumerationConstantCall(apiEnumerationConstantCall);
	}

	protected void linkApiProcedureCallWithApiProcedure(final ApiProcedureCall apiProcedureCall,
			final ApiProcedure apiProcedure) {
		apiProcedure.addApiProcedureCall(apiProcedureCall);
	}

	protected void linkApiPropertyCallWithApiProperty(final ApiPropertyCall apiPropertyCall,
			final ApiProperty apiProperty) {
		apiProperty.addApiPropertyCall(apiPropertyCall);
	}

	protected void linkArgCallsWithArgs(final Procedure procedure,
			final ArgValueAssignmentsContainer argValueAssignmentsContainer, final ArgsCallContext ctx) {
		// if there are args
		if (ctx != null) {
			/*
			 * associate arg calls with procedure args
			 */
			final List<Arg> argsOfProcedure;

			if (procedure != null) {
				argsOfProcedure = procedure.getArgsList();
			} else {
				argsOfProcedure = null;
			}

			int argCallIndex = 0;

			// for each arg call
			for (final ArgCallContext argCallCtx : ctx.argCall()) {
				final ArgValueAssignment argValueAssignment = addArgValueAssignment(argCallCtx);
				argValueAssignmentsContainer.addArgValueAssignment(argValueAssignment);

				if (argsOfProcedure == null) {
					LOG.warn("Could not identify called procedure for arg call {}.", argValueAssignment);
				} else {
					final ValueStmt assignedValueStmt = argValueAssignment.getAssignedValueStmt();
					final Arg arg;

					// in case of a named arg call
					if (assignedValueStmt != null && assignedValueStmt instanceof ValueAssignment) {
						final ValueAssignment valueAssignment = (ValueAssignment) assignedValueStmt;
						final Call assignedVariableCall = valueAssignment.getAssignedVariableCall();
						final String name = assignedVariableCall.getName();
						final Arg argWithSameName = procedure.getArgs().get(name);

						if (argWithSameName == null) {
							LOG.warn("{} does not have arg {}.", procedure, name);
						}

						arg = argWithSameName;
					} else if (argsOfProcedure.size() < argCallIndex + 1) {
						LOG.warn("{} does not accept surplus arg call {}.", procedure, argValueAssignment);
						arg = null;
					} else {
						arg = procedure.getArgsList().get(argCallIndex);
					}

					argValueAssignment.setArg(arg);
					argCallIndex++;
				}
			}
		}
	}

	protected void linkArgCallWithArg(final ArgCall argCall, final Arg arg) {
		arg.addArgCall(argCall);
	}

	protected void linkArrayElementCallWithVariable(final ArrayElementCall arrayElementCall, final Variable variable) {
		variable.addVariableCall(arrayElementCall);
	}

	protected void linkConstantCallWithConstant(final ConstantCall constantCall, final Constant constant) {
		constant.addConstantCall(constantCall);
	}

	protected void linkElementVariableCallWithElementVariable(final ElementVariableCall elementVariableCall,
			final ElementVariable elementVariable) {
		elementVariable.addElementVariableCall(elementVariableCall);
	}

	protected void linkEnumerationCallWithEnumeration(final EnumerationCall enumerationCall,
			final Enumeration enumeration) {
		enumeration.addEnumerationCall(enumerationCall);
	}

	protected void linkEnumerationConstantCallWithEnumerationConstant(
			final EnumerationConstantCall enumerationConstantCall, final EnumerationConstant enumerationConstant) {
		enumerationConstant.addEnumerationConstantCall(enumerationConstantCall);
	}

	protected void linkFunctionCallWithFunction(final FunctionCall functionCall, final Function function,
			final ArgsCallContext ctx) {
		function.addFunctionCall(functionCall);

		linkArgCallsWithArgs(function, functionCall, ctx);
	}

	protected void linkFunctionCallWithFunction(final FunctionCall functionCall, final Function function,
			final List<ArgsCallContext> ctxs) {
		final ArgsCallContext ctx;

		if (ctxs == null || ctxs.isEmpty()) {
			ctx = null;
		} else {
			ctx = ctxs.get(0);
		}

		linkFunctionCallWithFunction(functionCall, function, ctx);
	}

	protected void linkPropertyGetCallWithPropertyGet(final PropertyGetCall propertyGetCall,
			final PropertyGet propertyGet, final ArgsCallContext ctx) {
		propertyGet.addPropertyGetCall(propertyGetCall);

		linkArgCallsWithArgs(propertyGet, propertyGetCall, ctx);
	}

	protected void linkPropertyGetCallWithPropertyGet(final PropertyGetCall propertyGetCall,
			final PropertyGet propertyGet, final List<ArgsCallContext> ctxs) {
		final ArgsCallContext ctx;

		if (ctxs == null || ctxs.isEmpty()) {
			ctx = null;
		} else {
			ctx = ctxs.get(0);
		}

		linkPropertyGetCallWithPropertyGet(propertyGetCall, propertyGet, ctx);
	}

	protected void linkPropertyLetCallWithPropertySet(final PropertyLetCall propertyLetCall,
			final PropertyLet propertyLet, final ArgsCallContext ctx) {
		propertyLet.addPropertyLetCall(propertyLetCall);

		linkArgCallsWithArgs(propertyLet, propertyLetCall, ctx);
	}

	protected void linkPropertySetCallWithPropertySet(final PropertySetCall propertySetCall,
			final PropertySet propertySet, final ArgsCallContext ctx) {
		propertySet.addPropertySetCall(propertySetCall);

		linkArgCallsWithArgs(propertySet, propertySetCall, ctx);
	}

	protected void linkSubCallWithSub(final SubCall subCall, final Sub sub, final ArgsCallContext ctx) {
		sub.addSubCall(subCall);

		linkArgCallsWithArgs(sub, subCall, ctx);
	}

	protected void linkTypeElementCallWithTypeElement(final TypeElementCall typeElementCall,
			final TypeElement typeElement) {
		typeElement.addTypeElementCall(typeElementCall);
	}

	protected void linkVariableCallWithVariable(final VariableCall variableCall, final Variable variable) {
		variable.addVariableCall(variableCall);
	}

	protected void registerASGElement(final ASGElement asgElement) {
		module.getProgram().getASGElementRegistry().addASGElement(asgElement);
	}

	protected void registerScopedElement(final ScopedElement scopedElement) {
		assert scopedElement != null;
		assert scopedElement.getCtx() != null;

		registerASGElement(scopedElement);
		scopedElements.add(scopedElement);

		/*
		 * expressions should not be stored under their name, as they collide with
		 * declarations under the same name -> only declarations
		 */
		if (scopedElement instanceof Declaration) {
			final NamedElement namedElement = (NamedElement) scopedElement;
			final String symbol = getSymbol(namedElement.getName());

			if (scopedElementsSymbolTable.get(symbol) == null) {
				scopedElementsSymbolTable.put(symbol, new ArrayList<ScopedElement>());
			}

			scopedElementsSymbolTable.get(symbol).add(scopedElement);
		}
	}

	protected void registerStatement(final Statement statement) {
		assert statement != null;
		assert statement.getCtx() != null;

		registerScopedElement(statement);
		statements.add(statement);
	}
}
