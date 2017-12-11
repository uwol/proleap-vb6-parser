/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.event.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.EventStmtContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.impl.ArgImpl;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.statement.StatementType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.event.Event;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.resolver.impl.NameResolverImpl;
import io.proleap.vb6.asg.resolver.impl.TypeResolverImpl;

public class EventImpl extends ScopedElementImpl implements Event {

	protected final LinkedHashMap<String, Arg> args = new LinkedHashMap<String, Arg>();

	protected final List<Arg> argsList = new ArrayList<Arg>();

	protected final EventStmtContext ctx;

	protected final StatementType statementType = StatementTypeEnum.EVENT;

	protected final VisibilityEnum visibility;

	public EventImpl(final VisibilityEnum visibility, final Module module, final Scope scope,
			final EventStmtContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.ctx = ctx;
		this.visibility = visibility;
	}

	@Override
	public Arg addArg(final ArgContext ctx) {
		Arg result = (Arg) program.getASGElementRegistry().getASGElement(ctx);

		if (result == null) {
			final String name = new NameResolverImpl().determineName(ctx);
			final boolean isOptional = ctx.OPTIONAL() != null;
			final Type type = new TypeResolverImpl().determineType(ctx, program);

			result = new ArgImpl(name, type, isOptional, module, module, ctx);
			result.setEvent(this);

			args.put(name, result);
			argsList.add(result);

			program.getASGElementRegistry().addASGElement(result);
		}

		return result;
	}

	@Override
	public EventStmtContext getCtx() {
		return ctx;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public VisibilityEnum getVisibility() {
		return visibility;
	}
}
