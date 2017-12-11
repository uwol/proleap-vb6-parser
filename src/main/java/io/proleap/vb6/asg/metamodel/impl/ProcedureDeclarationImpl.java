/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.ProcedureDeclaration;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.resolver.impl.NameResolverImpl;
import io.proleap.vb6.asg.resolver.impl.TypeResolverImpl;

public class ProcedureDeclarationImpl extends ScopedElementImpl implements ProcedureDeclaration {

	protected final LinkedHashMap<String, Arg> args = new LinkedHashMap<String, Arg>();

	protected final List<Arg> argsList = new ArrayList<Arg>();

	protected final String name;

	public ProcedureDeclarationImpl(final String name, final Module module, final Scope scope,
			final ParserRuleContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.name = name;
	}

	@Override
	public Arg addArg(final ArgContext ctx) {
		Arg result = (Arg) program.getASGElementRegistry().getASGElement(ctx);

		if (result == null) {
			final String name = new NameResolverImpl().determineName(ctx);
			final boolean isOptional = ctx.OPTIONAL() != null;
			final Type type = new TypeResolverImpl().determineType(ctx, program);

			result = new ArgImpl(name, type, isOptional, module, module, ctx);
			result.setProcedureDeclaration(this);

			args.put(name, result);
			argsList.add(result);

			program.getASGElementRegistry().addASGElement(result);
		}

		return result;
	}

	@Override
	public String getName() {
		return name;
	}
}
