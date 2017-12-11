/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.resolver;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.type.Type;

public interface TypeResolver {

	Type determineType(ParseTree ctx, Program program);

}
