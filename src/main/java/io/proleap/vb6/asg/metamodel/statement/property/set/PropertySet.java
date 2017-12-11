/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.property.set;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.call.PropertySetCall;

/**
 * Declares the name, arguments, and code that form the body of a Property
 * procedure, which sets a reference to an object.
 */
public interface PropertySet extends Procedure {

	void addPropertySetCall(PropertySetCall propertySetCall);

	@Override
	PropertySetStmtContext getCtx();

	List<PropertySetCall> getPropertySetCalls();
}
