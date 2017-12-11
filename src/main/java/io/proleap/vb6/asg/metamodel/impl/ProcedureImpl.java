/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import static io.proleap.vb6.asg.util.CastUtils.castApiEnumerationConstant;
import static io.proleap.vb6.asg.util.CastUtils.castComplexType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgDefaultValueContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.ModelElement;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.impl.ApiEnumerationConstantCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.type.Type;

public abstract class ProcedureImpl extends ScopeImpl implements Procedure {

	protected final LinkedHashMap<String, Arg> args = new LinkedHashMap<String, Arg>();

	protected final List<Arg> argsList = new ArrayList<Arg>();

	protected final Module module;

	protected final String name;

	protected final VisibilityEnum visibility;

	public ProcedureImpl(final String name, final VisibilityEnum visibility, final Module module,
			final ParserRuleContext ctx) {
		super(module.getProgram(), module, module, ctx);

		this.module = module;
		this.name = name;
		this.visibility = visibility;
	}

	@Override
	public Arg addArg(final ArgContext ctx) {
		Arg result = (Arg) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final boolean isOptional = ctx.OPTIONAL() != null;

			final Type type = determineType(ctx);

			result = new ArgImpl(name, type, isOptional, module, this, ctx);
			result.setProcedure(this);

			if (ctx.argDefaultValue() != null) {
				final Call argDefaultValueCall = addCall(type, ctx.argDefaultValue());
				result.setDefaultValueCall(argDefaultValueCall);
			}

			args.put(name, result);
			argsList.add(result);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final Type argType, final ArgDefaultValueContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type defaultValueLiteralType = determineType(ctx);

			final ComplexType complexArgType = castComplexType(argType);
			final List<ModelElement> referencedElements = getElements(null, complexArgType, name);
			final ApiEnumerationConstant apiEnumerationConstant = castApiEnumerationConstant(referencedElements);

			/*
			 * create call model element
			 */
			if (apiEnumerationConstant != null) {
				final ApiEnumerationConstantCall apiEnumerationConstantCall = new ApiEnumerationConstantCallImpl(name,
						apiEnumerationConstant, module, this, ctx);

				linkApiEnumerationConstantCallWithApiEnumerationConstant(apiEnumerationConstantCall,
						apiEnumerationConstant);

				result = apiEnumerationConstantCall;
			} else {
				result = new UndefinedCallImpl(name, defaultValueLiteralType, module, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public boolean existsArg(final String name) {
		return args.containsKey(name);
	}

	@Override
	public Map<String, Arg> getArgs() {
		return args;
	}

	@Override
	public Map<String, Arg> getArgs(final Arg excluding) {
		final Map<String, Arg> argsBeforeExcluding = new LinkedHashMap<String, Arg>();

		for (final Entry<String, Arg> argEntry : args.entrySet()) {
			if (argEntry.getValue().equals(excluding)) {
				break;
			}

			argsBeforeExcluding.put(argEntry.getKey(), argEntry.getValue());
		}

		return argsBeforeExcluding;
	}

	@Override
	public List<Arg> getArgsList() {
		return argsList;
	}

	@Override
	public List<Arg> getArgsList(final Arg excluding) {
		return new ArrayList<Arg>(this.getArgs(excluding).values());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Map<String, Arg> getOptionalArgs() {
		final Map<String, Arg> optionalArgs = new LinkedHashMap<String, Arg>();

		for (final Entry<String, Arg> argEntry : args.entrySet()) {
			if (argEntry.getValue().isOptional()) {
				optionalArgs.put(argEntry.getKey(), argEntry.getValue());
			}
		}

		return optionalArgs;
	}

	@Override
	public List<Arg> getOptionalArgsList() {
		return new ArrayList<Arg>(getOptionalArgs().values());
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else if (args != null && args.get(name) != null) {
			result = new ArrayList<ScopedElement>();
			result.add(args.get(name));
		} else {
			result = super.getScopedElementsInScope(name);
		}

		return result;
	}

	@Override
	public VisibilityEnum getVisibility() {
		return visibility;
	}

	@Override
	public boolean hasOptionalArgs() {
		return !getOptionalArgs().isEmpty();
	}

	@Override
	public boolean isLastArg(final String argName) {
		return !argsList.isEmpty() && !argsList.get(argsList.size() - 1).getName().equals(argName);
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
