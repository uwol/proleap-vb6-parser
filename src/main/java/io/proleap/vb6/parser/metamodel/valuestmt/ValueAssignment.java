/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.valuestmt;

import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Constant;
import io.proleap.vb6.parser.metamodel.PropertyLet;
import io.proleap.vb6.parser.metamodel.PropertySet;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.call.Call;

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
