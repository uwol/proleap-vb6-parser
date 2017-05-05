/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.Collection;
import java.util.Map;

import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.metamodel.registry.TypeRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiEnumerationRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiProcedureRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiPropertyRegistry;

public interface Program extends Scope {

	ApiEnumerationRegistry getApiEnumerationRegistry();

	ApiProcedureRegistry getApiProcedureRegistry();

	ApiPropertyRegistry getApiPropertyRegistry();

	ASGElementRegistry getASGElementRegistry();

	ClazzModule getClazzModule(String name);

	Map<String, ClazzModule> getClazzModules();

	Module getModule(String name);

	Collection<Module> getModules();

	StandardModule getStandardModule(String name);

	Map<String, StandardModule> getStandardModules();

	TypeRegistry getTypeRegistry();

	void registerClazzModule(ClazzModule clazzModule);

	void registerModule(Module module);

	void registerStandardModule(StandardModule standardModule);
}
