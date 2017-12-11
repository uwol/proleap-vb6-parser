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

import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.metamodel.registry.EnumerationRegistry;
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

	EnumerationRegistry getEnumerationRegistry();

	Module getModule(String name);

	List<Module> getModules();

	StandardModule getStandardModule(String name);

	Map<String, StandardModule> getStandardModules();

	TypeRegistry getTypeRegistry();

	void registerClazzModule(ClazzModule clazzModule);

	void registerModule(Module module);

	void registerStandardModule(StandardModule standardModule);
}
