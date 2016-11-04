/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.parser.metamodel.oop.TypedElement;

public interface Literal extends VbScopedElement, TypedElement {

	@Override
	LiteralContext getCtx();

	String getValue();
}
