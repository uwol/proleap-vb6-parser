/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.enumeration;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.asg.metamodel.Declaration;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;

public interface EnumerationConstant extends TypedElement, Declaration, ScopedElement {

	void addEnumerationConstantCall(EnumerationConstantCall enumerationConstantCall);

	@Override
	EnumerationStmt_ConstantContext getCtx();

	Enumeration getEnumeration();

	List<EnumerationConstantCall> getEnumerationConstantCalls();

	int getPosition();

	ValueStmt getValueStmt();

	void setValueStmt(ValueStmt valueStmt);
}
