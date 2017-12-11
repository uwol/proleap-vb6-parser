/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.visitor.impl;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class VbDeclarationVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbDeclarationVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitAttributeStmt(final VisualBasic6Parser.AttributeStmtContext ctx) {
		module.addAttribute(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitConstStmt(final VisualBasic6Parser.ConstStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addConstants(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeclareStmt(final VisualBasic6Parser.DeclareStmtContext ctx) {
		module.addDeclaration(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeftypeStmt(final VisualBasic6Parser.DeftypeStmtContext ctx) {
		module.addDefType(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFunctionStmt(final VisualBasic6Parser.FunctionStmtContext ctx) {
		module.addFunction(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLineLabel(final VisualBasic6Parser.LineLabelContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addLineLabel(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitModuleConfigElement(final VisualBasic6Parser.ModuleConfigElementContext ctx) {
		module.addModuleConfigElement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitModuleHeader(final VisualBasic6Parser.ModuleHeaderContext ctx) {
		module.addModuleHeader(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionBaseStmt(final VisualBasic6Parser.OptionBaseStmtContext ctx) {
		module.addOptionBase(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionCompareStmt(final VisualBasic6Parser.OptionCompareStmtContext ctx) {
		module.addOptionCompare(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionExplicitStmt(final VisualBasic6Parser.OptionExplicitStmtContext ctx) {
		module.addOptionExplicit(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionPrivateModuleStmt(final VisualBasic6Parser.OptionPrivateModuleStmtContext ctx) {
		module.addOptionPrivateModule(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertyGetStmt(final VisualBasic6Parser.PropertyGetStmtContext ctx) {
		module.addPropertyGet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertyLetStmt(final VisualBasic6Parser.PropertyLetStmtContext ctx) {
		module.addPropertyLet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertySetStmt(final VisualBasic6Parser.PropertySetStmtContext ctx) {
		module.addPropertySet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRedimSubStmt(final VisualBasic6Parser.RedimSubStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addReDim(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSubStmt(final VisualBasic6Parser.SubStmtContext ctx) {
		module.addSub(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVariableStmt(final VisualBasic6Parser.VariableStmtContext ctx) {
		final Scope scope = findScope(ctx);

		scope.addVariables(ctx);

		return visitChildren(ctx);
	}
}
