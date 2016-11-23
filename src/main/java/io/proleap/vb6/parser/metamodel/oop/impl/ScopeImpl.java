/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.oop.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.ASGElement;
import io.proleap.vb6.parser.metamodel.oop.Declaration;
import io.proleap.vb6.parser.metamodel.oop.NamedElement;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.metamodel.oop.ScopedElement;
import io.proleap.vb6.parser.metamodel.oop.Type;

public abstract class ScopeImpl extends ScopedElementImpl implements Scope {

	private final static Logger LOG = LogManager.getLogger(ScopeImpl.class);

	protected final List<ScopedElement> scopedElements = new ArrayList<ScopedElement>();

	protected final Map<String, List<ScopedElement>> scopedElementsSymbolTable = new LinkedHashMap<String, List<ScopedElement>>();

	public ScopeImpl(final Scope superScope, final ParseTree ctx) {
		super(superScope, ctx);
	}

	protected String determineName(final ParseTree ctx) {
		return VbParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	protected Type determineType(final ParseTree ctx) {
		return VbParserContext.getInstance().getTypeResolver().determineType(ctx);
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = VbParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	private String getScopedElementKey(final String name) {
		return name.toLowerCase();
	}

	@Override
	public List<ScopedElement> getScopedElements() {
		return scopedElements;
	}

	@Override
	public List<ScopedElement> getScopedElementsInHierarchy(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			final List<ScopedElement> scopedElementInScope = getScopedElementsInScope(name);

			if (scopedElementInScope != null) {
				result = scopedElementInScope;
			} else if (superScope != null) {
				result = superScope.getScopedElementsInHierarchy(name);
			} else {
				result = null;
			}
		}

		return result;
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			final String scopedElementKey = getScopedElementKey(name);
			final List<ScopedElement> scopedElementInScope = scopedElementsSymbolTable.get(scopedElementKey);

			result = scopedElementInScope;
		}

		return result;
	}

	@Override
	public List<Scope> getSubScopes() {
		final List<Scope> result = new ArrayList<Scope>();

		for (final ScopedElement scopedElement : scopedElements) {
			if (scopedElement instanceof Scope) {
				final Scope scope = (Scope) scopedElement;
				result.add(scope);
			}
		}

		return result;
	}

	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		VbParserContext.getInstance().getASGElementRegistry().addASGElement(asgElement);
	}

	@Override
	public void storeScopedElement(final ScopedElement scopedElement) {
		assert scopedElement != null;
		assert scopedElement.getCtx() != null;

		registerASGElement(scopedElement);

		scopedElements.add(scopedElement);

		/*
		 * expressions should not be stored under their name, as they collide
		 * with declarations under the same name -> only declarations
		 */
		if (scopedElement instanceof Declaration) {
			final NamedElement namedElement = (NamedElement) scopedElement;
			final String scopedElementKey = getScopedElementKey(namedElement.getName());

			if (scopedElementsSymbolTable.get(scopedElementKey) == null) {
				scopedElementsSymbolTable.put(scopedElementKey, new ArrayList<ScopedElement>());
			}

			scopedElementsSymbolTable.get(scopedElementKey).add(scopedElement);
		}
	}
}
