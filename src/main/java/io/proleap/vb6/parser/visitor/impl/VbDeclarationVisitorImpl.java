/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;

/**
 * Visitor for analyzing declarations in the AST.
 */
public class VbDeclarationVisitorImpl extends AbstractVbParserVisitorImpl {

	public VbDeclarationVisitorImpl(final Module module) {
		super(module);
	}

	@Override
	public Boolean visitAttributeStmt(@NotNull final VisualBasic6Parser.AttributeStmtContext ctx) {
		module.addAttribute(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitConstSubStmt(@NotNull final VisualBasic6Parser.ConstSubStmtContext ctx) {
		final VbScope scope = findVbScope(ctx);

		scope.addConstant(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeclareStmt(@NotNull final VisualBasic6Parser.DeclareStmtContext ctx) {
		module.addDeclaration(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeftypeStmt(@NotNull final VisualBasic6Parser.DeftypeStmtContext ctx) {
		module.addDefType(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitFunctionStmt(@NotNull final VisualBasic6Parser.FunctionStmtContext ctx) {
		module.addFunction(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitLineLabel(@NotNull final VisualBasic6Parser.LineLabelContext ctx) {
		final VbScope scope = findVbScope(ctx);

		scope.addLineLabel(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitModuleConfigElement(@NotNull final VisualBasic6Parser.ModuleConfigElementContext ctx) {
		module.addModuleConfigElement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitModuleHeader(@NotNull final VisualBasic6Parser.ModuleHeaderContext ctx) {
		module.addModuleHeader(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionBaseStmt(@NotNull final VisualBasic6Parser.OptionBaseStmtContext ctx) {
		module.addOptionBase(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionCompareStmt(@NotNull final VisualBasic6Parser.OptionCompareStmtContext ctx) {
		module.addOptionCompare(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionExplicitStmt(@NotNull final VisualBasic6Parser.OptionExplicitStmtContext ctx) {
		module.addOptionExplicit(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitOptionPrivateModuleStmt(@NotNull final VisualBasic6Parser.OptionPrivateModuleStmtContext ctx) {
		module.addOptionPrivateModule(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertyGetStmt(@NotNull final VisualBasic6Parser.PropertyGetStmtContext ctx) {
		module.addPropertyGet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertyLetStmt(@NotNull final VisualBasic6Parser.PropertyLetStmtContext ctx) {
		module.addPropertyLet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertySetStmt(@NotNull final VisualBasic6Parser.PropertySetStmtContext ctx) {
		module.addPropertySet(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitRedimSubStmt(@NotNull final VisualBasic6Parser.RedimSubStmtContext ctx) {
		final VbScope scope = findVbScope(ctx);

		scope.addReDim(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitSubStmt(@NotNull final VisualBasic6Parser.SubStmtContext ctx) {
		module.addSub(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTypeStmt(@NotNull final VisualBasic6Parser.TypeStmtContext ctx) {
		module.addType(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitTypeStmt_Element(@NotNull final VisualBasic6Parser.TypeStmt_ElementContext ctx) {
		module.addTypeElement(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitVariableSubStmt(@NotNull final VisualBasic6Parser.VariableSubStmtContext ctx) {
		final VbScope scope = findVbScope(ctx);

		scope.addVariable(ctx);

		return visitChildren(ctx);
	}

}
