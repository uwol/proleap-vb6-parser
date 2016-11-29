/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import java.util.Collection;
import java.util.Map;

public interface Program extends Scope {

	ClazzModule getClazzModule(String name);

	Map<String, ClazzModule> getClazzModules();

	Module getModule(String name);

	Collection<Module> getModules();

	StandardModule getStandardModule(String name);

	Map<String, StandardModule> getStandardModules();

	void registerClazzModule(ClazzModule clazzModule);

	void registerModule(Module module);

	void registerStandardModule(StandardModule standardModule);
}
