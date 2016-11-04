/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.antlr;

import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.SemanticGraphElement;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.registry.SemanticGraphElementRegistry;

public interface ASTTraverser {

	<T extends SemanticGraphElement> Collection<T> findAncestors(Class<? extends SemanticGraphElement> type,
			ParseTree from, SemanticGraphElementRegistry semanticGraphElementRegistry);

	<T extends SemanticGraphElement> List<T> findChildren(Class<? extends SemanticGraphElement> type, ParseTree ctx,
			SemanticGraphElementRegistry semanticGraphElementRegistry);

	List<ParseTree> findChildren(ParseTree ctx);

	<T extends SemanticGraphElement> T findParent(Class<? extends SemanticGraphElement> type, ParseTree from,
			SemanticGraphElementRegistry semanticGraphElementRegistry);

	/**
	 * Identifies the scope element (e. g. function) containing the given parse
	 * tree.
	 */
	Scope findParentScope(ParseTree ctx, SemanticGraphElementRegistry semanticGraphElementRegistry);

	/**
	 * Identifies the parent semantic graph element, comparable to the AST
	 * parent.
	 */
	SemanticGraphElement findParentSemanticGraphElement(ParseTree ctx,
			SemanticGraphElementRegistry semanticGraphElementRegistry);

	List<SemanticGraphElement> findSemanticGraphElementChildren(ParseTree from,
			SemanticGraphElementRegistry semanticGraphElementRegistry);
}
