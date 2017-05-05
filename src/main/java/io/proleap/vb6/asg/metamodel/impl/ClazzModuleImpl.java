/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;

public class ClazzModuleImpl extends ModuleImpl implements ClazzModule {

	public ClazzModuleImpl(final String name, final Program program, final ModuleContext ctx) {
		super(name, program, ctx);

		program.registerClazzModule(this);
		program.getTypeRegistry().registerType(this);
	}

}
