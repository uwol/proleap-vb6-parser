/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Enumeration;
import io.proleap.vb6.parser.metamodel.EnumerationConstant;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.VbScope;
import io.proleap.vb6.parser.metamodel.call.EnumerationCall;
import io.proleap.vb6.parser.metamodel.oop.Type;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class EnumerationImpl extends VbScopedElementImpl implements Enumeration {

	protected final EnumerationStmtContext ctx;

	protected final List<EnumerationCall> enumerationCalls = new ArrayList<EnumerationCall>();

	protected final Map<EnumerationStmt_ConstantContext, EnumerationConstant> enumerationConstantsByCtx = new LinkedHashMap<EnumerationStmt_ConstantContext, EnumerationConstant>();

	protected final Map<String, EnumerationConstant> enumerationConstantsByName = new LinkedHashMap<String, EnumerationConstant>();

	protected final Module module;

	protected final String name;

	public EnumerationImpl(final String name, final Module module, final EnumerationStmtContext ctx) {
		super(module, module, ctx);

		this.ctx = ctx;
		this.module = module;
		this.name = name;
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
		enumerationConstantsByName.put(name, enumerationConstant);

		VbParserContext.getInstance().getASGElementRegistry().addASGElement(enumerationConstant);

		final ValueStmt valueStmt = module.addValueStmt(ctx.valueStmt());
		enumerationConstant.setValueStmt(valueStmt);
	}

	protected String determineName(final ParseTree ctx) {
		return VbParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	protected Type determineType(final ParseTree ctx) {
		return VbParserContext.getInstance().getTypeResolver().determineType(ctx);
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
		return enumerationConstantsByName.get(name);
	}

	@Override
	public Map<String, EnumerationConstant> getEnumerationConstants() {
		return enumerationConstantsByName;
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
	public VbScope getSuperScope() {
		return module;
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
