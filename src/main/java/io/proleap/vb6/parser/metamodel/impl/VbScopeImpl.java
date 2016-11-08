/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import static io.proleap.vb6.parser.util.CastUtils.castApiEnumeration;
import static io.proleap.vb6.parser.util.CastUtils.castApiEnumerationConstant;
import static io.proleap.vb6.parser.util.CastUtils.castApiModule;
import static io.proleap.vb6.parser.util.CastUtils.castApiProcedure;
import static io.proleap.vb6.parser.util.CastUtils.castApiProperty;
import static io.proleap.vb6.parser.util.CastUtils.castArg;
import static io.proleap.vb6.parser.util.CastUtils.castComplexType;
import static io.proleap.vb6.parser.util.CastUtils.castConst;
import static io.proleap.vb6.parser.util.CastUtils.castEnumeration;
import static io.proleap.vb6.parser.util.CastUtils.castEnumerationConstant;
import static io.proleap.vb6.parser.util.CastUtils.castFunction;
import static io.proleap.vb6.parser.util.CastUtils.castLineLabel;
import static io.proleap.vb6.parser.util.CastUtils.castModule;
import static io.proleap.vb6.parser.util.CastUtils.castPropertyGet;
import static io.proleap.vb6.parser.util.CastUtils.castPropertyLet;
import static io.proleap.vb6.parser.util.CastUtils.castPropertySet;
import static io.proleap.vb6.parser.util.CastUtils.castSub;
import static io.proleap.vb6.parser.util.CastUtils.castVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ArgsCallContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondElseContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondIsContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondToContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondValueContext;
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
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Constant;
import io.proleap.vb6.parser.metamodel.Enumeration;
import io.proleap.vb6.parser.metamodel.EnumerationConstant;
import io.proleap.vb6.parser.metamodel.Exit;
import io.proleap.vb6.parser.metamodel.Exit.ExitType;
import io.proleap.vb6.parser.metamodel.ForEach;
import io.proleap.vb6.parser.metamodel.ForNext;
import io.proleap.vb6.parser.metamodel.Function;
import io.proleap.vb6.parser.metamodel.IfCondition;
import io.proleap.vb6.parser.metamodel.Let;
import io.proleap.vb6.parser.metamodel.LineLabel;
import io.proleap.vb6.parser.metamodel.Literal;
import io.proleap.vb6.parser.metamodel.ModelElement;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.OnError;
import io.proleap.vb6.parser.metamodel.Procedure;
import io.proleap.vb6.parser.metamodel.PropertyGet;
import io.proleap.vb6.parser.metamodel.PropertyLet;
import io.proleap.vb6.parser.metamodel.PropertySet;
import io.proleap.vb6.parser.metamodel.ReDim;
import io.proleap.vb6.parser.metamodel.Resume;
import io.proleap.vb6.parser.metamodel.Select;
import io.proleap.vb6.parser.metamodel.SelectCase;
import io.proleap.vb6.parser.metamodel.SelectCaseCond;
import io.proleap.vb6.parser.metamodel.SelectCaseCond.SelectCaseCondType;
import io.proleap.vb6.parser.metamodel.Set;
import io.proleap.vb6.parser.metamodel.StandardModule;
import io.proleap.vb6.parser.metamodel.Sub;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.While;
import io.proleap.vb6.parser.metamodel.With;
import io.proleap.vb6.parser.metamodel.call.ApiEnumerationCall;
import io.proleap.vb6.parser.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.parser.metamodel.call.ApiProcedureCall;
import io.proleap.vb6.parser.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.parser.metamodel.call.ArgCall;
import io.proleap.vb6.parser.metamodel.call.ArrayElementCall;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.call.Call.CallContext;
import io.proleap.vb6.parser.metamodel.call.ConstantCall;
import io.proleap.vb6.parser.metamodel.call.EnumerationCall;
import io.proleap.vb6.parser.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.parser.metamodel.call.FunctionCall;
import io.proleap.vb6.parser.metamodel.call.MembersCall;
import io.proleap.vb6.parser.metamodel.call.PropertyGetCall;
import io.proleap.vb6.parser.metamodel.call.PropertyLetCall;
import io.proleap.vb6.parser.metamodel.call.PropertySetCall;
import io.proleap.vb6.parser.metamodel.call.ReturnValueCall;
import io.proleap.vb6.parser.metamodel.call.SubCall;
import io.proleap.vb6.parser.metamodel.call.VariableCall;
import io.proleap.vb6.parser.metamodel.call.impl.ApiEnumerationCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ApiEnumerationConstantCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ApiProcedureCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ApiPropertyCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ArgCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ArrayElementCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.CallDelegateImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ConstantCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.DictionaryCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.EnumerationCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.EnumerationConstantCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.FunctionCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.MeCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.MembersCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ModuleCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.PropertyGetCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.PropertyLetCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.PropertySetCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.ReturnValueCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.vb6.parser.metamodel.call.impl.VariableCallImpl;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;
import io.proleap.vb6.parser.metamodel.oop.ScopedElement;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.oop.api.ApiEnumeration;
import io.proleap.vb6.parser.metamodel.oop.api.ApiEnumerationConstant;
import io.proleap.vb6.parser.metamodel.oop.api.ApiModule;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProcedure;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProperty;
import io.proleap.vb6.parser.metamodel.oop.impl.ScopeImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.vb6.parser.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.vb6.parser.metamodel.valuestmt.NewValueStmt;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueAssignment;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.ArgValueAssignmentImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.ArithmeticValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.BooleanValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.CallValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.EqValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.IsValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.NewValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.NotValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.StringValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.StructValueStmtImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.SubCallImpl;
import io.proleap.vb6.parser.metamodel.valuestmt.impl.ValueAssignmentImpl;

public abstract class VbScopeImpl extends ScopeImpl implements VbScope {

	private final static Logger LOG = LogManager.getLogger(VbScopeImpl.class);

	public final static String ME = "ME";

	protected Module module;

	protected Map<String, Variable> variables = new HashMap<String, Variable>();

	public VbScopeImpl(final Module module, final VbScope superScope, final ParseTree ctx) {
		super(superScope, ctx);

		this.module = module;
	}

	@Override
	public ArgValueAssignment addArgValueAssignment(final ArgCallContext ctx) {
		ArgValueAssignment result = (ArgValueAssignment) getASGElement(ctx);

		if (result == null) {
			result = new ArgValueAssignmentImpl(module, this, ctx);

			final ValueStmt assignedValueStmt = addValueStmt(ctx.valueStmt());
			result.setAssignedValueStmt(assignedValueStmt);

			storeScopedElement(result);
		}

		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return result;
	}

	@Override
	public Call addCall(final CallContext callContext, final ImplicitCallStmt_InStmtContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			/*
			 * then the delegated call
			 */
			final Call delegatedCall;

			if (ctx.iCS_S_VariableOrProcedureCall() != null) {
				delegatedCall = addCall(null, callContext, ctx.iCS_S_VariableOrProcedureCall());
			} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
				delegatedCall = addCall(null, ctx.iCS_S_ProcedureOrArrayCall());
			} else if (ctx.iCS_S_MembersCall() != null) {
				delegatedCall = addCall(callContext, ctx.iCS_S_MembersCall());
			} else if (ctx.iCS_S_DictionaryCall() != null) {
				delegatedCall = addCall(ctx.iCS_S_DictionaryCall());
			} else {
				LOG.warn("unknown implicit call {}.", ctx);
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

			ComplexType instanceType = null;

			if (ctx.iCS_S_VariableOrProcedureCall() != null) {
				final Call instanceCall = addCall(null, callContext, ctx.iCS_S_VariableOrProcedureCall());
				instanceType = castComplexType(instanceCall.getType());

				result.addSubCall(instanceCall);
			} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
				final Call instanceCall = addCall(null, ctx.iCS_S_ProcedureOrArrayCall());
				instanceType = castComplexType(instanceCall.getType());

				result.addSubCall(instanceCall);
			}

			if (ctx.iCS_S_MemberCall() != null) {
				for (final ICS_S_MemberCallContext memberCallContext : ctx.iCS_S_MemberCall()) {
					final Call call = addCall(instanceType, callContext, memberCallContext);
					instanceType = castComplexType(call.getType());

					result.addSubCall(call);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ComplexType instanceType, final CallContext callContext,
			final ICS_S_MemberCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			/*
			 * then the delegated call
			 */
			final Call delegatedCall;

			if (ctx.iCS_S_VariableOrProcedureCall() != null) {
				delegatedCall = addCall(instanceType, callContext, ctx.iCS_S_VariableOrProcedureCall());
			} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
				delegatedCall = addCall(instanceType, ctx.iCS_S_ProcedureOrArrayCall());
			} else {
				delegatedCall = null;
			}

			result = new CallDelegateImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ComplexType instanceType, final CallContext callContext,
			final ICS_S_VariableOrProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			/*
			 * instead of regular variables, standard modules can be called for
			 * calling further members on them -> check for calls on standard
			 * modules
			 */
			final StandardModule calledModule = module.getProgram().getStandardModule(name);

			if (calledModule != null) {
				result = new ModuleCallImpl(name, calledModule, module, this, ctx);
			} else if (ME.equals(name.toUpperCase())) {
				result = new MeCallImpl(name, module, this, ctx);
			} else {
				/*
				 * determine referenced element, i. e. array variable or
				 * function
				 */
				final List<ModelElement> referencedProgramElements = getElements(instanceType, name);

				final Arg arg = castArg(referencedProgramElements);
				final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);
				final ApiProperty apiProperty = castApiProperty(referencedProgramElements);
				final ApiEnumeration apiEnumeration = castApiEnumeration(referencedProgramElements);
				final ApiEnumerationConstant apiEnumerationConstant = castApiEnumerationConstant(
						referencedProgramElements);
				final Constant constant = castConst(referencedProgramElements);
				final Enumeration enumeration = castEnumeration(referencedProgramElements);
				final EnumerationConstant enumerationConstant = castEnumerationConstant(referencedProgramElements);
				final Function function = castFunction(referencedProgramElements);
				final PropertyGet propertyGet = castPropertyGet(referencedProgramElements);
				final PropertyLet propertyLet = castPropertyLet(referencedProgramElements);
				final PropertySet propertySet = castPropertySet(referencedProgramElements);
				final Sub sub = castSub(referencedProgramElements);
				final Variable variable = castVariable(referencedProgramElements);

				/*
				 * potentially, this let sets a return variable of a function or
				 * property get
				 */
				final Procedure procedure = this.findSuperScope(Procedure.class);
				final boolean hasProcedureName;

				if (procedure != null) {
					hasProcedureName = procedure.getName().equals(name);
				} else {
					hasProcedureName = false;
				}

				/*
				 * create call model element
				 */
				if (CallContext.LET_LEFT_HAND_SIDE.equals(callContext)) {
					if (instanceType == null && hasProcedureName) {
						if (propertyGet != null) {
							final ReturnValueCall returnValueCall = new ReturnValueCallImpl(name, propertyGet, module,
									this, ctx);

							result = returnValueCall;
						} else {
							final ReturnValueCall returnValueCall = new ReturnValueCallImpl(name, function, module,
									this, ctx);

							result = returnValueCall;
						}
					} else if (variable != null) {
						final VariableCall variableCall = new VariableCallImpl(name, variable, module, this, ctx);

						result = variableCall;
					}
					// arg values can be overwritten
					else if (arg != null) {
						final ArgCall argCall = new ArgCallImpl(name, arg, module, this, ctx);

						result = argCall;
					} else if (propertyLet != null) {
						final PropertyLetCall properyLetCall = new PropertyLetCallImpl(name, propertyLet, module, this,
								ctx);

						associatePropertyLetCallWithPropertySet(properyLetCall, propertyLet, null);

						result = properyLetCall;
					} else if (propertySet != null) {
						final PropertySetCall propertySetCall = new PropertySetCallImpl(name, propertySet, module, this,
								ctx);

						associatePropertySetCallWithPropertySet(propertySetCall, propertySet, null);

						result = propertySetCall;
					} else if (constant != null) {
						final ConstantCall constantCall = new ConstantCallImpl(name, constant, module, this, ctx);

						result = constantCall;
					} else if (enumeration != null) {
						final EnumerationCall enumerationCall = new EnumerationCallImpl(name, enumeration, module, this,
								ctx);

						result = enumerationCall;
					} else if (enumerationConstant != null) {
						final EnumerationConstantCall enumerationConstantCall = new EnumerationConstantCallImpl(name,
								enumerationConstant, module, this, ctx);

						final boolean isStandalone = instanceType == null;
						enumerationConstantCall.setStandaloneCall(isStandalone);

						result = enumerationConstantCall;
					} else if (apiProcedure != null) {
						final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module,
								this, ctx);

						result = apiProcedureCall;
					} else if (apiProperty != null) {
						final ApiPropertyCall apiPropertyCall = new ApiPropertyCallImpl(name, apiProperty, module, this,
								ctx);

						result = apiPropertyCall;
					} else if (apiEnumeration != null) {
						final ApiEnumerationCall apiEnumerationCall = new ApiEnumerationCallImpl(name, apiEnumeration,
								module, this, ctx);

						result = apiEnumerationCall;
					} else if (apiEnumerationConstant != null) {
						final ApiEnumerationConstantCall enumerationConstantCall = new ApiEnumerationConstantCallImpl(
								name, apiEnumerationConstant, module, this, ctx);

						final boolean isStandalone = instanceType == null;
						enumerationConstantCall.setStandaloneCall(isStandalone);

						result = enumerationConstantCall;
					} else {
						LOG.warn("left hand call to unknown element {}", name);
						result = new UndefinedCallImpl(name, null, module, this, ctx);
					}
				} else {
					// return values can be read inside of functions or property
					// gets
					if (instanceType == null && hasProcedureName) {
						if (propertyGet != null) {
							final ReturnValueCall returnValueCall = new ReturnValueCallImpl(name, propertyGet, module,
									this, ctx);

							result = returnValueCall;
						} else {
							final ReturnValueCall returnValueCall = new ReturnValueCallImpl(name, function, module,
									this, ctx);

							result = returnValueCall;
						}
					} else if (variable != null) {
						final VariableCall variableCall = new VariableCallImpl(name, variable, module, this, ctx);

						result = variableCall;
					} else if (arg != null) {
						final ArgCall argCall = new ArgCallImpl(name, arg, module, this, ctx);

						result = argCall;
					} else if (constant != null) {
						final ConstantCall constantCall = new ConstantCallImpl(name, constant, module, this, ctx);

						result = constantCall;
					} else if (propertyGet != null) {
						final PropertyGetCall propertyGetCall = new PropertyGetCallImpl(name, propertyGet, module, this,
								ctx);

						associatePropertyGetCallWithPropertyGet(propertyGetCall, propertyGet, null);

						result = propertyGetCall;
					} else if (function != null) {
						final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

						associateFunctionCallWithFunction(functionCall, function, null);

						result = functionCall;
					} else if (sub != null) {
						final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

						associateSubCallWithSub(subCall, sub, null);

						result = subCall;
					} else if (enumeration != null) {
						final EnumerationCall enumerationCall = new EnumerationCallImpl(name, enumeration, module, this,
								ctx);

						result = enumerationCall;
					} else if (enumerationConstant != null) {
						final EnumerationConstantCall enumerationConstantCall = new EnumerationConstantCallImpl(name,
								enumerationConstant, module, this, ctx);

						final boolean isStandalone = instanceType == null;
						enumerationConstantCall.setStandaloneCall(isStandalone);

						result = enumerationConstantCall;
					} else if (apiProcedure != null) {
						final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module,
								this, ctx);

						result = apiProcedureCall;
					} else if (apiProperty != null) {
						final ApiPropertyCall apiPropertyCall = new ApiPropertyCallImpl(name, apiProperty, module, this,
								ctx);

						result = apiPropertyCall;
					} else if (apiEnumeration != null) {
						final ApiEnumerationCall apiEnumerationCall = new ApiEnumerationCallImpl(name, apiEnumeration,
								module, this, ctx);

						result = apiEnumerationCall;
					} else if (apiEnumerationConstant != null) {
						final ApiEnumerationConstantCall enumerationConstantCall = new ApiEnumerationConstantCallImpl(
								name, apiEnumerationConstant, module, this, ctx);

						final boolean isStandalone = instanceType == null;
						enumerationConstantCall.setStandaloneCall(isStandalone);

						result = enumerationConstantCall;
					} else {
						LOG.warn("call to unknown element {}", name);
						result = new UndefinedCallImpl(name, null, module, this, ctx);
					}
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ComplexType instanceType, final ICS_S_ProcedureOrArrayCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			/*
			 * determine referenced element, i. e. array variable or function
			 */
			final List<ModelElement> referencedProgramElements = getElements(instanceType, name);

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
				final ArrayElementCall arrayElementCall = new ArrayElementCallImpl(name, module, this, ctx);
				arrayElementCall.setVariable(variable);
				result = arrayElementCall;
			} else {
				if (function != null) {
					final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

					associateFunctionCallWithFunction(functionCall, function, ctx.argsCall());

					result = functionCall;
				} else if (propertyGet != null) {
					final PropertyGetCall propertyGetCall = new PropertyGetCallImpl(name, propertyGet, module, this,
							ctx);

					associatePropertyGetCallWithPropertyGet(propertyGetCall, propertyGet, ctx.argsCall());

					result = propertyGetCall;
				} else if (apiProcedure != null) {
					final ApiProcedureCall apiProcedureCall = new ApiProcedureCallImpl(name, apiProcedure, module, this,
							ctx);

					result = apiProcedureCall;
				} else if (apiProperty != null) {
					final ApiPropertyCall apiPropertyCall = new ApiPropertyCallImpl(name, apiProperty, module, this,
							ctx);

					result = apiPropertyCall;
				} else {
					LOG.warn("call to unknown element {}", name);
					result = new UndefinedCallImpl(name, null, module, this, ctx);
				}
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
			final Type type = determineType(ctx);

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

			ComplexType instanceType = null;

			if (ctx.implicitCallStmt_InStmt() != null) {
				final Call instanceCall = addCall(null, ctx.implicitCallStmt_InStmt());
				instanceType = castComplexType(instanceCall.getType());
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
				final List<ModelElement> referencedProgramElements = getElements(null, name);

				sub = castSub(referencedProgramElements);
				function = castFunction(referencedProgramElements);
				apiProcedure = castApiProcedure(referencedProgramElements);
			}

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				associateSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				associateFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				result = new ApiProcedureCallImpl(name, apiProcedure, module, this, ctx);
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

			final List<ModelElement> referencedProgramElements = getElements(null, name);

			final Sub sub = castSub(referencedProgramElements);
			final Function function = castFunction(referencedProgramElements);
			final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				associateSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				associateFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				result = new ApiProcedureCallImpl(name, apiProcedure, module, this, ctx);
			} else {
				result = new UndefinedCallImpl(name, null, module, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ExplicitCallStmtContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall;

			if (ctx.eCS_ProcedureCall() != null) {
				delegatedCall = addCall(ctx.eCS_ProcedureCall());
			} else if (ctx.eCS_MemberProcedureCall() != null) {
				delegatedCall = addCall(ctx.eCS_MemberProcedureCall());
			} else {
				delegatedCall = null;
			}

			result = new CallDelegateImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final ICS_B_MemberProcedureCallContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			ComplexType instanceType = null;

			if (ctx.implicitCallStmt_InStmt() != null) {
				final Call instanceCall = addCall(null, ctx.implicitCallStmt_InStmt());
				instanceType = castComplexType(instanceCall.getType());
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
				final List<ModelElement> referencedProgramElements = getElements(null, name);

				sub = castSub(referencedProgramElements);
				function = castFunction(referencedProgramElements);
				apiProcedure = castApiProcedure(referencedProgramElements);
			}

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				associateSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				associateFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				result = new ApiProcedureCallImpl(name, apiProcedure, module, this, ctx);
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

			final List<ModelElement> referencedProgramElements = getElements(null, name);

			final Sub sub = castSub(referencedProgramElements);
			final Function function = castFunction(referencedProgramElements);
			final ApiProcedure apiProcedure = castApiProcedure(referencedProgramElements);

			if (sub != null) {
				final SubCall subCall = new SubCallImpl(name, sub, module, this, ctx);

				associateSubCallWithSub(subCall, sub, ctx.argsCall());

				result = subCall;
			} else if (function != null) {
				final FunctionCall functionCall = new FunctionCallImpl(name, function, module, this, ctx);

				associateFunctionCallWithFunction(functionCall, function, ctx.argsCall());

				result = functionCall;
			} else if (apiProcedure != null) {
				result = new ApiProcedureCallImpl(name, apiProcedure, module, this, ctx);
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
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final Call delegatedCall;

			if (ctx.iCS_B_ProcedureCall() != null) {
				delegatedCall = addCall(ctx.iCS_B_ProcedureCall());
			} else if (ctx.iCS_B_MemberProcedureCall() != null) {
				delegatedCall = addCall(ctx.iCS_B_MemberProcedureCall());
			} else {
				LOG.warn("unknown implicit call {}.", ctx);
				delegatedCall = null;
			}

			result = new CallDelegateImpl(delegatedCall, module, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Constant addConstant(final ConstSubStmtContext ctx) {
		Constant result = (Constant) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new ConstantImpl(name, type, module, this, ctx);

			final ValueStmt valueStmt = addValueStmt(ctx.valueStmt());
			result.setValueStmt(valueStmt);

			storeScopedElement(result);
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

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ForEach addForEach(final ForEachStmtContext ctx) {
		ForEach result = (ForEach) getASGElement(ctx);

		if (result == null) {
			result = new ForEachImpl(module, this, ctx);

			final String elementVariableName = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(null, elementVariableName);
			final Variable elementVariable = castVariable(referencedProgramElements);
			result.setElementVariable(elementVariable);

			// in
			if (ctx.valueStmt() != null) {
				final ValueStmt in = addValueStmt(ctx.valueStmt());
				result.setIn(in);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ForNext addForNext(final ForNextStmtContext ctx) {
		ForNext result = (ForNext) getASGElement(ctx);

		if (result == null) {
			result = new ForNextImpl(module, this, ctx);

			final String iteratorVariableName = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(null, iteratorVariableName);

			final Variable iteratorVariable = castVariable(referencedProgramElements);
			result.setIteratorVariable(iteratorVariable);

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

			storeScopedElement(result);
		}

		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return result;
	}

	@Override
	public IfCondition addIfCondition(final IfConditionStmtContext ctx) {
		IfCondition result = (IfCondition) getASGElement(ctx);

		if (result == null) {
			result = new IfConditionImpl(module, module, ctx);

			final ValueStmt valueStmt = addValueStmt(ctx.valueStmt());
			result.setValueStmt(valueStmt);

			storeScopedElement(result);
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

			storeScopedElement(result);
		}

		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return result;
	}

	@Override
	public LineLabel addLineLabel(final LineLabelContext ctx) {
		LineLabel result = (LineLabel) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new LineLabelImpl(name, module, this, ctx);

			storeScopedElement(result);
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
			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public OnError addOnError(final OnErrorStmtContext ctx) {
		OnError result = (OnError) getASGElement(ctx);

		if (result == null) {
			final String lineLabelName = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(null, lineLabelName);

			final LineLabel lineLabel = castLineLabel(referencedProgramElements);

			result = new OnErrorImpl(lineLabel, module, this, ctx);

			if (lineLabel != null) {
				lineLabel.addOnError(result);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ReDim addReDim(final RedimSubStmtContext ctx) {
		ReDim result = (ReDim) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(null, name);
			final Variable variable = castVariable(referencedProgramElements);

			result = new ReDimImpl(variable, module, this, ctx);

			storeScopedElement(result);
		}

		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

		return result;
	}

	@Override
	public Resume addResume(final ResumeStmtContext ctx) {
		Resume result = (Resume) getASGElement(ctx);

		if (result == null) {
			final String lineLabelName = determineName(ctx);

			final List<ModelElement> referencedProgramElements = getElements(null, lineLabelName);

			final LineLabel lineLabel = castLineLabel(referencedProgramElements);

			result = new ResumeImpl(lineLabel, module, this, ctx);

			if (lineLabel != null) {
				lineLabel.addResume(result);
			}

			storeScopedElement(result);
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

			storeScopedElement(result);
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

			storeScopedElement(result);
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
			} else if (ctx instanceof CaseCondIsContext) {
				selectCaseCondType = SelectCaseCondType.IS;
			} else if (ctx instanceof CaseCondToContext) {
				selectCaseCondType = SelectCaseCondType.TO;
			} else if (ctx instanceof CaseCondValueContext) {
				selectCaseCondType = SelectCaseCondType.VALUE;
			} else {
				selectCaseCondType = null;
			}

			result = new SelectCaseCondImpl(selectCaseCondType, module, this, ctx);

			if (SelectCaseCondType.IS.equals(selectCaseCondType)) {
				final CaseCondIsContext condIsCtx = (CaseCondIsContext) ctx;
				final ValueStmt valueStmt = addValueStmt(condIsCtx.valueStmt());

				result.addValueStmt(valueStmt);
			} else if (SelectCaseCondType.VALUE.equals(selectCaseCondType)) {
				final CaseCondValueContext condValueCtx = (CaseCondValueContext) ctx;

				for (final ValueStmtContext valueCtx : condValueCtx.valueStmt()) {
					final ValueStmt valueStmt = addValueStmt(valueCtx);

					result.addValueStmt(valueStmt);
				}
			} else if (SelectCaseCondType.TO.equals(selectCaseCondType)) {
				final CaseCondToContext condToCtx = (CaseCondToContext) ctx;

				for (final ValueStmtContext valueCtx : condToCtx.valueStmt()) {
					final ValueStmt valueStmt = addValueStmt(valueCtx);

					result.addValueStmt(valueStmt);
				}
			}

			storeScopedElement(result);
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

			storeScopedElement(result);
		}

		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

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
			final List<ModelElement> referencedProgramElements = getElements(null, assignedVariableName);

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
			 * associate value assignment model element with variable call model
			 * element
			 */
			result.setAssignedVariableCall(assignedVariableCall);

			// right hand side call
			final ValueStmt rightHandValueStmt = addValueStmt(ctx.valueStmt());
			result.setRightHandValueStmt(rightHandValueStmt);

			registerASGElement(result);
		}

		VbParserContext.getInstance().getTypeAssignmentInference().addTypeAssignment(ctx);

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
	public Variable addVariable(final VariableSubStmtContext ctx) {
		Variable result = (Variable) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new VariableImpl(name, type, module, this, ctx);

			final boolean isArray = ctx.LPAREN() != null && ctx.RPAREN() != null;
			final boolean isStaticArray = isArray && ctx.subscripts() != null;

			result.setDeclaredAsArray(isArray);
			result.setDeclaredAsStaticArray(isStaticArray);

			storeScopedElement(result);
			variables.put(name, result);
		}

		return result;
	}

	@Override
	public While addWhile(final WhileWendStmtContext ctx) {
		While result = (While) getASGElement(ctx);

		if (result == null) {
			result = new WhileImpl(module, this, ctx);

			final ValueStmt condition = addValueStmt(ctx.valueStmt());
			result.setCondition(condition);

			storeScopedElement(result);
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

			storeScopedElement(result);
		}

		return result;
	}

	protected void associateArgCallsWithArgs(final Procedure procedure, final ArgsCallContext ctx) {
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

				if (argsOfProcedure == null) {
					LOG.warn("could not identify called procedure for arg call {}", argValueAssignment);
				} else if (argsOfProcedure.size() < argCallIndex + 1) {
					LOG.warn("{} does not accept surplus arg call {}", procedure, argValueAssignment);
				} else {
					// determine the corresponding arg
					final Arg arg = procedure.getArgsList().get(argCallIndex);
					argValueAssignment.setArg(arg);
				}

				argCallIndex++;
			}
		}
	}

	protected void associateFunctionCallWithFunction(final FunctionCall functionCall, final Function function,
			final ArgsCallContext ctx) {
		function.addFunctionCall(functionCall);

		associateArgCallsWithArgs(function, ctx);
	}

	protected void associatePropertyGetCallWithPropertyGet(final PropertyGetCall propertyGetCall,
			final PropertyGet propertyGet, final ArgsCallContext ctx) {
		propertyGet.addPropertyGetCall(propertyGetCall);

		associateArgCallsWithArgs(propertyGet, ctx);
	}

	protected void associatePropertyLetCallWithPropertySet(final PropertyLetCall propertyLetCall,
			final PropertyLet propertyLet, final ArgsCallContext ctx) {
		propertyLet.addPropertyLetCall(propertyLetCall);

		associateArgCallsWithArgs(propertyLet, ctx);
	}

	protected void associatePropertySetCallWithPropertySet(final PropertySetCall propertySetCall,
			final PropertySet propertySet, final ArgsCallContext ctx) {
		propertySet.addPropertySetCall(propertySetCall);

		associateArgCallsWithArgs(propertySet, ctx);
	}

	protected void associateSubCallWithSub(final SubCall subCall, final Sub sub, final ArgsCallContext ctx) {
		sub.addSubCall(subCall);

		associateArgCallsWithArgs(sub, ctx);
	}

	/**
	 * searches elements of the program by their name such as functions,
	 * variables, api procedures etc.
	 */
	protected List<ModelElement> getElements(final ComplexType instanceType, final String name) {
		final Module instanceModule = castModule(instanceType);
		final ApiModule instanceApiModule = castApiModule(instanceType);
		final ApiEnumeration instanceApiEnumeration = castApiEnumeration(instanceType);

		final List<ModelElement> referencedProgramElements = new ArrayList<ModelElement>();

		if (name == null) {
		}
		// if a module instance is given
		else if (instanceModule != null) {
			final List<ScopedElement> instanceModuleScopedElements = instanceModule.getScopedElementsInScope(name);

			if (instanceModuleScopedElements != null) {
				referencedProgramElements.addAll(instanceModuleScopedElements);
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
		}
		// if there is no instance given
		else {
			// search globally in the program for elements with that
			// name
			final List<ScopedElement> globalProgramElements = getScopedElementsInHierarchy(name);

			final ApiProcedure apiProcedure = VbParserContext.getInstance().getApiProcedureRegistry()
					.getApiProcedure(name);

			final ApiProperty apiProperty = VbParserContext.getInstance().getApiPropertyRegistry().getApiProperty(name);

			final ApiEnumerationConstant apiEnumerationConstant = VbParserContext.getInstance()
					.getApiEnumerationRegistry().getApiEnumerationConstant(name);

			if (globalProgramElements != null) {
				referencedProgramElements.addAll(globalProgramElements);
			}

			referencedProgramElements.add(apiProcedure);
			referencedProgramElements.add(apiProperty);
			referencedProgramElements.add(apiEnumerationConstant);
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
	public Variable getVariable(final String name) {
		return variables.get(name);
	}
}
