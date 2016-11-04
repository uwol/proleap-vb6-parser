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

public interface Program extends VbScope {

	ClazzModule getClazzModule(final String name);

	Map<String, ClazzModule> getClazzModules();

	Module getModule(final String name);

	Collection<Module> getModules();

	StandardModule getStandardModule(final String name);

	Map<String, StandardModule> getStandardModules();

	void registerClazzModule(final ClazzModule clazzModule);

	void registerModule(final Module module);

	void registerStandardModule(final StandardModule standardModule);
}
