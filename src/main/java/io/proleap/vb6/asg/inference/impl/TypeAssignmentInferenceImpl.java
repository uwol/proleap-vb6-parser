/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.inference.impl;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.asg.inference.TypeAssignmentInference;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.PropertyLetCall;
import io.proleap.vb6.asg.metamodel.call.PropertySetCall;
import io.proleap.vb6.asg.metamodel.call.ReturnValueCall;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.statement.redim.ReDim;
import io.proleap.vb6.asg.metamodel.statement.set.Set;
import io.proleap.vb6.asg.metamodel.type.AssignableTypedElement;
import io.proleap.vb6.asg.metamodel.type.BaseType;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueAssignment;

public class TypeAssignmentInferenceImpl implements TypeAssignmentInference {

	@Override
	public void addTypeAssignment(final ArgCallContext ctx, final Program program) {
		final ArgValueAssignment argSetterCall = (ArgValueAssignment) getASGElement(ctx, program);

		assert (argSetterCall != null);

		final Arg arg = argSetterCall.getArg();
		final Type type = determineType(argSetterCall.getAssignedValueStmt());

		if (arg != null) {
			arg.addTypeOfAssignedValue(type);
		}
	}

	@Override
	public void addTypeAssignment(final ForNextStmtContext ctx, final Program program) {
		final ForNext forNext = (ForNext) getASGElement(ctx, program);

		assert (forNext != null);

		if (forNext.getCounterCall() != null) {
			final BaseType integerType = VbBaseType.INTEGER;
			final Call counterCall = forNext.getCounterCall();
			final Call unwrappedCall = counterCall.unwrap();
			final CallType callType = unwrappedCall.getCallType();

			if (CallType.VARIABLE_CALL.equals(callType)) {
				final VariableCall variableCall = (VariableCall) unwrappedCall;
				final Variable variable = variableCall.getVariable();
				variable.addTypeOfAssignedValue(integerType);
			}
		}
	}

	@Override
	public void addTypeAssignment(final LetStmtContext ctx, final Program program) {
		final Let let = (Let) getASGElement(ctx, program);

		assert (let != null);

		// right hand side type
		final Type valueType = determineType(let.getRightHandValueStmt());

		// potentially, a property let or property set is called by this let
		// statement, which is registered at the let stmt
		final Call leftHandCall = let.getLeftHandCall().unwrap();
		final CallType leftHandCallType = leftHandCall.getCallType();

		if (CallType.VARIABLE_CALL.equals(leftHandCallType)) {
			final VariableCall variableCall = (VariableCall) leftHandCall;
			variableCall.getVariable().addTypeOfAssignedValue(valueType);
		} else if (CallType.PROPERTY_LET_CALL.equals(leftHandCallType)) {
			final PropertyLetCall propertyLetCall = (PropertyLetCall) leftHandCall;
			propertyLetCall.getPropertyLet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.PROPERTY_SET_CALL.equals(leftHandCallType)) {
			final PropertySetCall propertySetCall = (PropertySetCall) leftHandCall;
			propertySetCall.getPropertySet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.RETURN_VALUE_CALL.equals(leftHandCallType)) {
			final ReturnValueCall returnValueCall = (ReturnValueCall) leftHandCall;
			final Procedure procedure = returnValueCall.getProcedure();

			if (procedure instanceof AssignableTypedElement) {
				final AssignableTypedElement assignableTypedElement = (AssignableTypedElement) procedure;
				assignableTypedElement.addTypeOfAssignedValue(valueType);
			}
		}
	}

	@Override
	public void addTypeAssignment(final RedimSubStmtContext ctx, final Program program) {
		final ReDim reDim = (ReDim) getASGElement(ctx, program);

		final Variable variable = reDim.getVariable();

		if (variable != null) {
			variable.setIsRedimed(true);
		}
	}

	@Override
	public void addTypeAssignment(final SetStmtContext ctx, final Program program) {
		final Set set = (Set) getASGElement(ctx, program);

		assert (set != null);

		// right hand side type
		final Type valueType = determineType(set.getRightHandValueStmt());

		// potentially, a property let or property set is called by this set
		// statement, which is registered at the let stmt
		final Call leftHandCall = set.getLeftHandCall().unwrap();
		final CallType leftHandCallType = leftHandCall.getCallType();

		if (CallType.VARIABLE_CALL.equals(leftHandCallType)) {
			final VariableCall variableCall = (VariableCall) leftHandCall;
			variableCall.getVariable().addTypeOfAssignedValue(valueType);
		} else if (CallType.PROPERTY_LET_CALL.equals(leftHandCallType)) {
			final PropertyLetCall propertyLetCall = (PropertyLetCall) leftHandCall;
			propertyLetCall.getPropertyLet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.PROPERTY_SET_CALL.equals(leftHandCallType)) {
			final PropertySetCall propertySetCall = (PropertySetCall) leftHandCall;
			propertySetCall.getPropertySet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.RETURN_VALUE_CALL.equals(leftHandCallType)) {
			final ReturnValueCall returnValueCall = (ReturnValueCall) leftHandCall;
			final Procedure procedure = returnValueCall.getProcedure();

			if (procedure instanceof AssignableTypedElement) {
				final AssignableTypedElement assignableTypedElement = (AssignableTypedElement) procedure;
				assignableTypedElement.addTypeOfAssignedValue(valueType);
			}
		}
	}

	@Override
	public void addTypeAssignment(final VsAssignContext ctx, final Program program) {
		final ValueAssignment vsAssign = (ValueAssignment) getASGElement(ctx, program);

		assert (vsAssign != null);

		final Type type = determineType(vsAssign.getRightHandValueStmt());

		if (vsAssign.getVariable() != null) {
			vsAssign.getVariable().addTypeOfAssignedValue(type);
		}
	}

	protected Type determineType(final TypedElement typedElement) {
		return typedElement != null ? typedElement.getType() : null;
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx, final Program program) {
		final ASGElement result = program.getASGElementRegistry().getASGElement(ctx);
		return result;
	}

}
