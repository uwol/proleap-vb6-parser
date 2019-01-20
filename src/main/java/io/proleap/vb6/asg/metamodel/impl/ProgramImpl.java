/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.proleap.vb6.asg.exception.VbParserException;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.VisibilityElement;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.metamodel.registry.EnumerationRegistry;
import io.proleap.vb6.asg.metamodel.registry.TypeRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiEnumerationRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiProcedureRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiPropertyRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.impl.ApiEnumerationRegistryImpl;
import io.proleap.vb6.asg.metamodel.registry.api.impl.ApiProcedureRegistryImpl;
import io.proleap.vb6.asg.metamodel.registry.api.impl.ApiPropertyRegistryImpl;
import io.proleap.vb6.asg.metamodel.registry.impl.ASGElementRegistryImpl;
import io.proleap.vb6.asg.metamodel.registry.impl.EnumerationRegistryImpl;
import io.proleap.vb6.asg.metamodel.registry.impl.TypeRegistryImpl;
import io.proleap.vb6.asg.resolver.NameResolver;
import io.proleap.vb6.asg.resolver.impl.NameResolverImpl;

public class ProgramImpl extends ScopeImpl implements Program {

	protected ApiEnumerationRegistry apiEnumerationRegistry = new ApiEnumerationRegistryImpl();

	protected ApiProcedureRegistry apiProcedureRegistry = new ApiProcedureRegistryImpl();

	protected ApiPropertyRegistry apiPropertyRegistry = new ApiPropertyRegistryImpl();

	protected ASGElementRegistry asgElementRegistry = new ASGElementRegistryImpl();

	protected final Map<String, ClazzModule> clazzModules = new LinkedHashMap<String, ClazzModule>();

	protected EnumerationRegistry enumerationRegistry = new EnumerationRegistryImpl();

	protected final NameResolver nameResolver = new NameResolverImpl();

	protected final Map<String, StandardModule> standardModules = new LinkedHashMap<String, StandardModule>();

	protected TypeRegistry typeRegistry = new TypeRegistryImpl();

	public ProgramImpl() {
		super(null, null, null, null);
	}

	private List<ScopedElement> filterPrivateScopedElements(final List<ScopedElement> scopedElements) {
		final List<ScopedElement> result = new ArrayList<ScopedElement>();

		for (final ScopedElement scopedElement : scopedElements) {
			final boolean isVisibilityElement = scopedElement instanceof VisibilityElement;

			if (!isVisibilityElement) {
				result.add(scopedElement);
			} else {
				final VisibilityElement visibilityElement = (VisibilityElement) scopedElement;
				final VisibilityEnum visibility = visibilityElement.getVisibility();

				if (visibility == null) {
				} else {
					switch (visibility) {
					case PRIVATE:
						break;
					default:
						result.add(scopedElement);
						break;
					}
				}
			}
		}

		return result;
	}

	@Override
	public ApiEnumerationRegistry getApiEnumerationRegistry() {
		return apiEnumerationRegistry;
	}

	@Override
	public ApiProcedureRegistry getApiProcedureRegistry() {
		return apiProcedureRegistry;
	}

	@Override
	public ApiPropertyRegistry getApiPropertyRegistry() {
		return apiPropertyRegistry;
	}

	@Override
	public ASGElementRegistry getASGElementRegistry() {
		return asgElementRegistry;
	}

	@Override
	public ClazzModule getClazzModule(final String name) {
		final String moduleKey = getModuleKey(name);
		return clazzModules.get(moduleKey);
	}

	@Override
	public Map<String, ClazzModule> getClazzModules() {
		return clazzModules;
	}

	@Override
	public EnumerationRegistry getEnumerationRegistry() {
		return enumerationRegistry;
	}

	@Override
	public Module getModule(final String name) {
		final Module result;

		final ClazzModule clazzModule = getClazzModule(name);
		final StandardModule standardModule = getStandardModule(name);

		if (clazzModule != null) {
			result = clazzModule;
		} else if (standardModule != null) {
			result = standardModule;
		} else {
			result = null;
		}

		return result;
	}

	private String getModuleKey(final String name) {
		return name.toLowerCase();
	}

	@Override
	public List<Module> getModules() {
		final List<Module> modules = new ArrayList<Module>();

		modules.addAll(clazzModules.values());
		modules.addAll(standardModules.values());

		return modules;
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			List<ScopedElement> scopedElementsInStandardModule = null;

			// search in modules ...
			for (final Module module : getModules()) {
				// ... for scoped elements with name
				final List<ScopedElement> scopedElements = module.getScopedElementsInScope(name);

				// ... until there are scoped elements found
				if (scopedElements != null && !scopedElements.isEmpty()) {
					scopedElementsInStandardModule = filterPrivateScopedElements(scopedElements);

					if (scopedElementsInStandardModule != null && !scopedElementsInStandardModule.isEmpty()) {
						break;
					}
				}
			}

			// if elements have been found
			if (scopedElementsInStandardModule != null && !scopedElementsInStandardModule.isEmpty()) {
				result = scopedElementsInStandardModule;
			} else {
				result = super.getScopedElementsInScope(name);
			}
		}

		return result;
	}

	@Override
	public StandardModule getStandardModule(final String name) {
		final String moduleKey = getModuleKey(name);
		return standardModules.get(moduleKey);
	}

	@Override
	public Map<String, StandardModule> getStandardModules() {
		return standardModules;
	}

	@Override
	public TypeRegistry getTypeRegistry() {
		return typeRegistry;
	}

	@Override
	public void registerClazzModule(final ClazzModule clazzModule) {
		final String moduleKey = getModuleKey(clazzModule.getName());
		clazzModules.put(moduleKey, clazzModule);
	}

	@Override
	public void registerModule(final Module module) {
		if (module instanceof ClazzModule) {
			registerClazzModule((ClazzModule) module);
		} else if (module instanceof StandardModule) {
			registerStandardModule((StandardModule) module);
		} else {
			throw new VbParserException("unknown module type " + module);
		}
	}

	@Override
	public void registerStandardModule(final StandardModule standardModule) {
		final String moduleKey = getModuleKey(standardModule.getName());
		standardModules.put(moduleKey, standardModule);
	}
}
