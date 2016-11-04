/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.Map;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;

public interface Enumeration extends ComplexType, VbScopedElement {

	void addEnumerationConstant(EnumerationStmt_ConstantContext ctx);

	@Override
	EnumerationStmtContext getCtx();

	EnumerationConstant getEnumerationConstant(EnumerationStmt_ConstantContext ctx);

	EnumerationConstant getEnumerationConstant(String name);

	Map<String, EnumerationConstant> getEnumerationConstants();

}
