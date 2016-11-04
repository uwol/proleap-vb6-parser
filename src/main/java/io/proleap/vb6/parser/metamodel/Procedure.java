/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.List;
import java.util.Map;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgDefaultValueContext;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.oop.Declaration;
import io.proleap.vb6.parser.metamodel.oop.Type;

public interface Procedure extends VbScope, Declaration {

	Arg addArg(ArgContext ctx);

	Call addCall(Type argType, ArgDefaultValueContext ctx);

	boolean existsArg(String name);

	Map<String, Arg> getArgs();

	Map<String, Arg> getArgs(Arg excluding);

	List<Arg> getArgsList();

	List<Arg> getArgsList(Arg excluding);

	Map<String, Arg> getOptionalArgs();

	List<Arg> getOptionalArgsList();

	boolean hasOptionalArgs();

	boolean hasReturnType();

	boolean isLastArg(String argName);

}
