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

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.ProcedureDeclaration;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.Type;

public class ProcedureDeclarationImpl extends VbScopedElementImpl implements ProcedureDeclaration {

	protected final LinkedHashMap<String, Arg> args = new LinkedHashMap<String, Arg>();

	protected final List<Arg> argsList = new ArrayList<Arg>();

	protected final String name;

	public ProcedureDeclarationImpl(final String name, final Module module, final Scope superScope,
			final ParseTree ctx) {
		super(module, superScope, ctx);

		this.name = name;
	}

	@Override
	public Arg addArg(final ArgContext ctx) {
		final String name = VbParserContext.getInstance().getNameResolver().determineName(ctx);
		final boolean isOptional = ctx.OPTIONAL() != null;
		final Type type = VbParserContext.getInstance().getTypeResolver().determineType(ctx);

		final Arg arg = new ArgImpl(name, type, isOptional, module, module, ctx);
		arg.setProcedureDeclaration(this);

		args.put(name, arg);
		argsList.add(arg);

		VbParserContext.getInstance().getSemanticGraphElementRegistry().addSemanticGraphElement(arg);

		return arg;
	}

	@Override
	public String getName() {
		return name;
	}

}
