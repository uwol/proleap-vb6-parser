/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.valuestmt;

import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;

public interface ValueAssignment extends ValueStmt {

	Arg getArg();

	Call getAssignedVariableCall();

	Constant getConstant();

	@Override
	VsAssignContext getCtx();

	PropertyLet getPropertyLet();

	PropertySet getPropertySet();

	ValueStmt getRightHandValueStmt();

	Variable getVariable();

	void setArg(Arg arg);

	void setAssignedVariableCall(Call variableCall);

	void setConstant(Constant constant);

	void setPropertyLet(PropertyLet propertyLet);

	void setPropertySet(PropertySet propertySet);

	void setRightHandValueStmt(ValueStmt rightHandValueStmt);

	void setVariable(Variable variable);
}
