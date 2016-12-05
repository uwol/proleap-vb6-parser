/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.statement.enumeration;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.parser.metamodel.Declaration;
import io.proleap.vb6.parser.metamodel.ScopedElement;
import io.proleap.vb6.parser.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.parser.metamodel.type.TypedElement;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

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
