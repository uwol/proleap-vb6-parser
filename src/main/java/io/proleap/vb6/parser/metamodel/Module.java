/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.List;
import java.util.Map;

import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeclareStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeftypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleConfigElementContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleHeaderContext;
import io.proleap.vb6.VisualBasic6Parser.OptionBaseStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OptionCompareStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OptionExplicitStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OptionPrivateModuleStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;

public interface Module extends VbScope, ComplexType {

	public enum OptionCompare {
		BINARY, TEXT
	}

	Attribute addAttribute(AttributeStmtContext ctx);

	ProcedureDeclaration addDeclaration(DeclareStmtContext ctx);

	DefType addDefType(final DeftypeStmtContext ctx);

	Enumeration addEnumeration(EnumerationStmtContext ctx);

	Function addFunction(final FunctionStmtContext ctx);

	ModuleConfigElement addModuleConfigElement(ModuleConfigElementContext ctx);

	void addModuleHeader(ModuleHeaderContext ctx);

	void addOptionBase(OptionBaseStmtContext ctx);

	void addOptionCompare(OptionCompareStmtContext ctx);

	void addOptionExplicit(OptionExplicitStmtContext ctx);

	void addOptionPrivateModule(OptionPrivateModuleStmtContext ctx);

	PropertyGet addPropertyGet(final PropertyGetStmtContext ctx);

	PropertyLet addPropertyLet(final PropertyLetStmtContext ctx);

	PropertySet addPropertySet(final PropertySetStmtContext ctx);

	Sub addSub(final SubStmtContext ctx);

	io.proleap.vb6.parser.metamodel.Type addType(final TypeStmtContext ctx);

	TypeElement addTypeElement(TypeStmt_ElementContext ctx);

	@Override
	ModuleContext getCtx();

	List<DefType> getDefTypes();

	EnumerationConstant getEnumerationConstant(final String name);

	Map<String, Enumeration> getEnumerations();

	Function getFunction(final String name);

	Program getProgram();

	PropertyGet getPropertyGet(final String name);

	PropertyLet getPropertyLet(final String name);

	PropertySet getPropertySet(final String name);

	Sub getSub(final String name);

	Double getVersion();

	@Override
	boolean isCollection();

	boolean isModuleWithMetaData();

}
