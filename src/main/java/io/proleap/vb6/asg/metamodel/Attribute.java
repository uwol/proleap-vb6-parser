/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

public interface Attribute extends TypedElement, Declaration, ScopedElement {

	@Override
	AttributeStmtContext getCtx();

	Literal getValue();

	void setValue(Literal value);
}
