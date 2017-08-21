/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import org.antlr.v4.runtime.CommonTokenStream;

import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.StandardModule;

public class StandardModuleImpl extends ModuleImpl implements StandardModule {

	public StandardModuleImpl(final String name, final Program program, final CommonTokenStream tokens,
			final ModuleContext ctx) {
		super(name, program, tokens, ctx);

		program.registerStandardModule(this);
	}

}
