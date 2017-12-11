/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.property.let;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.call.PropertyLetCall;

/**
 * Declares the name, arguments, and code that form the body of a Property Let
 * procedure, which assigns a value to a property.
 */
public interface PropertyLet extends Procedure {

	void addPropertyLetCall(PropertyLetCall propertyLetCall);

	@Override
	PropertyLetStmtContext getCtx();

	List<PropertyLetCall> getPropertyLetCalls();
}
