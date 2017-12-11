/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.property.get;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.call.PropertyGetCall;
import io.proleap.vb6.asg.metamodel.type.AssignableTypedElement;

/**
 * Declares the name, arguments, and code that form the body of a Property
 * procedure, which gets the value of a property.
 */
public interface PropertyGet extends Procedure, AssignableTypedElement {

	void addPropertyGetCall(PropertyGetCall propertyGetCall);

	@Override
	PropertyGetStmtContext getCtx();

	List<PropertyGetCall> getPropertyGetCalls();

	boolean isDeclaredAsArray();

	void setDeclaredAsArray(boolean isArray);

}
