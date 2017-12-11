/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.enumeration;

import java.util.List;
import java.util.Map;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.asg.metamodel.VisibilityElement;
import io.proleap.vb6.asg.metamodel.call.EnumerationCall;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.type.ComplexType;

/**
 * Declares a type for an enumeration.
 */
public interface Enumeration extends ComplexType, VisibilityElement, Statement {

	void addEnumerationCall(EnumerationCall enumerationCall);

	void addEnumerationConstant(EnumerationStmt_ConstantContext ctx);

	@Override
	EnumerationStmtContext getCtx();

	List<EnumerationCall> getEnumerationCalls();

	EnumerationConstant getEnumerationConstant(EnumerationStmt_ConstantContext ctx);

	EnumerationConstant getEnumerationConstant(String name);

	Map<String, EnumerationConstant> getEnumerationConstants();

}
