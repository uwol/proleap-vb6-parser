/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

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
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.ComplexType;

public interface Module extends Scope, ComplexType {

	public enum OptionCompare {
		BINARY, TEXT
	}

	Attribute addAttribute(AttributeStmtContext ctx);

	ProcedureDeclaration addDeclaration(DeclareStmtContext ctx);

	DefType addDefType(DeftypeStmtContext ctx);

	Enumeration addEnumeration(EnumerationStmtContext ctx);

	Function addFunction(FunctionStmtContext ctx);

	ModuleConfigElement addModuleConfigElement(ModuleConfigElementContext ctx);

	void addModuleHeader(ModuleHeaderContext ctx);

	void addOptionBase(OptionBaseStmtContext ctx);

	void addOptionCompare(OptionCompareStmtContext ctx);

	void addOptionExplicit(OptionExplicitStmtContext ctx);

	void addOptionPrivateModule(OptionPrivateModuleStmtContext ctx);

	PropertyGet addPropertyGet(PropertyGetStmtContext ctx);

	PropertyLet addPropertyLet(PropertyLetStmtContext ctx);

	PropertySet addPropertySet(PropertySetStmtContext ctx);

	Sub addSub(SubStmtContext ctx);

	io.proleap.vb6.asg.metamodel.Type addType(TypeStmtContext ctx);

	TypeElement addTypeElement(TypeStmt_ElementContext ctx);

	@Override
	ModuleContext getCtx();

	List<DefType> getDefTypes();

	EnumerationConstant getEnumerationConstant(String name);

	Map<String, Enumeration> getEnumerations();

	Function getFunction(String name);

	List<String> getLines();

	@Override
	Program getProgram();

	PropertyGet getPropertyGet(String name);

	PropertyLet getPropertyLet(String name);

	PropertySet getPropertySet(String name);

	Sub getSub(String name);

	io.proleap.vb6.asg.metamodel.Type getType(String name);

	Double getVersion();

	@Override
	boolean isCollection();

	boolean isModuleWithMetaData();

	void setLines(List<String> lines);

}
