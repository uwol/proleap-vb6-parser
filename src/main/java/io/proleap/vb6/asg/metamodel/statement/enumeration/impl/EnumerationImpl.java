/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.enumeration.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.call.EnumerationCall;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.asg.resolver.impl.NameResolverImpl;
import io.proleap.vb6.asg.resolver.impl.TypeResolverImpl;

public class EnumerationImpl extends ScopedElementImpl implements Enumeration {

	protected final EnumerationStmtContext ctx;

	protected final List<EnumerationCall> enumerationCalls = new ArrayList<EnumerationCall>();

	protected final Map<EnumerationStmt_ConstantContext, EnumerationConstant> enumerationConstantsByCtx = new LinkedHashMap<EnumerationStmt_ConstantContext, EnumerationConstant>();

	protected final Map<String, EnumerationConstant> enumerationConstantsSymbolTable = new LinkedHashMap<String, EnumerationConstant>();

	protected final Module module;

	protected final String name;

	protected final StatementType statementType = StatementTypeEnum.ENUMERATION;

	protected final VisibilityEnum visibility;

	public EnumerationImpl(final String name, final VisibilityEnum visibility, final Module module,
			final EnumerationStmtContext ctx) {
		super(module.getProgram(), module, module, ctx);

		this.ctx = ctx;
		this.module = module;
		this.name = name;
		this.visibility = visibility;
	}

	@Override
	public void addEnumerationCall(final EnumerationCall enumerationCall) {
		enumerationCalls.add(enumerationCall);
	}

	@Override
	public void addEnumerationConstant(final EnumerationStmt_ConstantContext ctx) {
		final String name = determineName(ctx);
		final EnumerationStmtContext enumerationCtx = (EnumerationStmtContext) ctx.getParent();

		int position = 0;

		for (int i = 0; i < enumerationCtx.enumerationStmt_Constant().size(); i++) {
			if (ctx.equals(enumerationCtx.enumerationStmt_Constant(i))) {
				position = i;
				break;
			}
		}

		final EnumerationConstant enumerationConstant = new EnumerationConstantImpl(name, position, this, ctx);

		enumerationConstantsByCtx.put(ctx, enumerationConstant);
		enumerationConstantsSymbolTable.put(getSymbol(name), enumerationConstant);

		module.getProgram().getASGElementRegistry().addASGElement(enumerationConstant);

		final ValueStmt valueStmt = module.addValueStmt(ctx.valueStmt());
		enumerationConstant.setValueStmt(valueStmt);
	}

	protected String determineName(final ParserRuleContext ctx) {
		return new NameResolverImpl().determineName(ctx);
	}

	protected Type determineType(final ParserRuleContext ctx) {
		final Program program = module.getProgram();
		return new TypeResolverImpl().determineType(ctx, program);
	}

	@Override
	public EnumerationStmtContext getCtx() {
		return ctx;
	}

	@Override
	public List<EnumerationCall> getEnumerationCalls() {
		return enumerationCalls;
	}

	@Override
	public EnumerationConstant getEnumerationConstant(final EnumerationStmt_ConstantContext ctx) {
		return enumerationConstantsByCtx.get(ctx);
	}

	@Override
	public EnumerationConstant getEnumerationConstant(final String name) {
		return enumerationConstantsSymbolTable.get(getSymbol(name));
	}

	@Override
	public Map<String, EnumerationConstant> getEnumerationConstants() {
		return enumerationConstantsSymbolTable;
	}

	@Override
	public Module getModule() {
		return module;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Scope getScope() {
		return module;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public VisibilityEnum getVisibility() {
		return visibility;
	}

	@Override
	public boolean isCollection() {
		return false;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
