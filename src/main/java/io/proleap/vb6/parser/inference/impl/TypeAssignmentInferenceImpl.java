/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.inference.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.inference.TypeAssignmentInference;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Procedure;
import io.proleap.vb6.parser.metamodel.ASGElement;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.call.Call.CallType;
import io.proleap.vb6.parser.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.parser.metamodel.statement.let.Let;
import io.proleap.vb6.parser.metamodel.statement.redim.ReDim;
import io.proleap.vb6.parser.metamodel.statement.set.Set;
import io.proleap.vb6.parser.metamodel.type.AssignableTypedElement;
import io.proleap.vb6.parser.metamodel.type.BaseType;
import io.proleap.vb6.parser.metamodel.type.Type;
import io.proleap.vb6.parser.metamodel.type.TypedElement;
import io.proleap.vb6.parser.metamodel.call.PropertyLetCall;
import io.proleap.vb6.parser.metamodel.call.PropertySetCall;
import io.proleap.vb6.parser.metamodel.call.ReturnValueCall;
import io.proleap.vb6.parser.metamodel.call.VariableCall;
import io.proleap.vb6.parser.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueAssignment;

public class TypeAssignmentInferenceImpl implements TypeAssignmentInference {

	@Override
	public void addTypeAssignment(final ArgCallContext ctx) {
		final ArgValueAssignment argSetterCall = (ArgValueAssignment) getASGElement(ctx);

		assert (argSetterCall != null);

		final Arg arg = argSetterCall.getArg();
		final Type type = determineType(argSetterCall.getAssignedValueStmt());

		if (arg != null) {
			arg.addTypeOfAssignedValue(type);
		}
	}

	@Override
	public void addTypeAssignment(final ForNextStmtContext ctx) {
		final ForNext forNext = (ForNext) getASGElement(ctx);

		assert (forNext != null);

		if (forNext.getIteratorVariable() != null) {
			final BaseType integerType = VbBaseType.INTEGER;

			assert (integerType != null);

			forNext.getIteratorVariable().addTypeOfAssignedValue(integerType);
		}
	}

	@Override
	public void addTypeAssignment(final LetStmtContext ctx) {
		final Let let = (Let) getASGElement(ctx);

		assert (let != null);

		// right hand side type
		final Type valueType = determineType(let.getRightHandValueStmt());

		// potentially, a property let or property set is called by this let
		// statement, which is registered at the let stmt
		final Call leftHandCall = let.getLeftHandCall().unwrap();
		final CallType leftHandCallType = leftHandCall.getCallType();

		if (CallType.VariableCall.equals(leftHandCallType)) {
			final VariableCall variableCall = (VariableCall) leftHandCall;
			variableCall.getVariable().addTypeOfAssignedValue(valueType);
		} else if (CallType.PropertyLetCall.equals(leftHandCallType)) {
			final PropertyLetCall propertyLetCall = (PropertyLetCall) leftHandCall;
			propertyLetCall.getPropertyLet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.PropertySetCall.equals(leftHandCallType)) {
			final PropertySetCall propertySetCall = (PropertySetCall) leftHandCall;
			propertySetCall.getPropertySet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.ReturnValueCall.equals(leftHandCallType)) {
			final ReturnValueCall returnValueCall = (ReturnValueCall) leftHandCall;
			final Procedure procedure = returnValueCall.getProcedure();

			if (procedure instanceof AssignableTypedElement) {
				final AssignableTypedElement assignableTypedElement = (AssignableTypedElement) procedure;
				assignableTypedElement.addTypeOfAssignedValue(valueType);
			}
		}
	}

	@Override
	public void addTypeAssignment(final RedimSubStmtContext ctx) {
		final ReDim reDim = (ReDim) getASGElement(ctx);

		final Variable variable = reDim.getVariable();

		if (variable != null) {
			variable.setIsRedimed(true);
		}
	}

	@Override
	public void addTypeAssignment(final SetStmtContext ctx) {
		final Set set = (Set) getASGElement(ctx);

		assert (set != null);

		// right hand side type
		final Type valueType = determineType(set.getRightHandValueStmt());

		// potentially, a property let or property set is called by this set
		// statement, which is registered at the let stmt
		final Call leftHandCall = set.getLeftHandCall().unwrap();
		final CallType leftHandCallType = leftHandCall.getCallType();

		if (CallType.VariableCall.equals(leftHandCallType)) {
			final VariableCall variableCall = (VariableCall) leftHandCall;
			variableCall.getVariable().addTypeOfAssignedValue(valueType);
		} else if (CallType.PropertyLetCall.equals(leftHandCallType)) {
			final PropertyLetCall propertyLetCall = (PropertyLetCall) leftHandCall;
			propertyLetCall.getPropertyLet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.PropertySetCall.equals(leftHandCallType)) {
			final PropertySetCall propertySetCall = (PropertySetCall) leftHandCall;
			propertySetCall.getPropertySet().getArgsList().get(0).addTypeOfAssignedValue(valueType);
		} else if (CallType.ReturnValueCall.equals(leftHandCallType)) {
			final ReturnValueCall returnValueCall = (ReturnValueCall) leftHandCall;
			final Procedure procedure = returnValueCall.getProcedure();

			if (procedure instanceof AssignableTypedElement) {
				final AssignableTypedElement assignableTypedElement = (AssignableTypedElement) procedure;
				assignableTypedElement.addTypeOfAssignedValue(valueType);
			}
		}
	}

	@Override
	public void addTypeAssignment(final VsAssignContext ctx) {
		final ValueAssignment vsAssign = (ValueAssignment) getASGElement(ctx);

		assert (vsAssign != null);

		final Type type = determineType(vsAssign.getRightHandValueStmt());

		if (vsAssign.getVariable() != null) {
			vsAssign.getVariable().addTypeOfAssignedValue(type);
		}
	}

	protected Type determineType(final TypedElement typedElement) {
		return typedElement != null ? typedElement.getType() : null;
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = VbParserContext.getInstance().getASGElementRegistry()
				.getASGElement(ctx);
		return result;
	}

}
