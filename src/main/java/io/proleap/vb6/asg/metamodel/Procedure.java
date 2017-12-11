/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.List;
import java.util.Map;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgDefaultValueContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.type.Type;

public interface Procedure extends Scope, Declaration, Statement, VisibilityElement {

	Arg addArg(ArgContext ctx);

	Call addCall(Type argType, ArgDefaultValueContext ctx);

	boolean existsArg(String name);

	Map<String, Arg> getArgs();

	Map<String, Arg> getArgs(Arg excluding);

	List<Arg> getArgsList();

	List<Arg> getArgsList(Arg excluding);

	List<Call> getCalls();

	Map<String, Arg> getOptionalArgs();

	List<Arg> getOptionalArgsList();

	boolean hasOptionalArgs();

	boolean hasReturnType();

	boolean isLastArg(String argName);
}
