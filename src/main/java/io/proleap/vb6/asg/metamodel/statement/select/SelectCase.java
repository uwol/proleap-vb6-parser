/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.select;

import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

public interface SelectCase extends ScopedElement, TypedElement {

	@Override
	SC_CaseContext getCtx();

	Select getSelect();

	SelectCaseCond getSelectCaseCond();

	boolean hasBlock();

	void setSelect(Select select);

	void setSelectCaseCond(SelectCaseCond selectCaseCond);
}
