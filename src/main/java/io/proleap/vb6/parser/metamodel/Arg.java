/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.parser.metamodel.call.Call;
import io.proleap.vb6.parser.metamodel.oop.AssignableTypedElement;
import io.proleap.vb6.parser.metamodel.oop.NamedElement;

public interface Arg extends AssignableTypedElement, NamedElement, VbScopedElement {

	@Override
	ArgContext getCtx();

	Call getDefaultValueCall();

	Procedure getProcedure();

	ProcedureDeclaration getProcedureDeclaration();

	boolean isCollection();

	boolean isOptional();

	void setDefaultValueCall(Call defaultValueCall);

	void setProcedure(Procedure procedure);

	void setProcedureDeclaration(ProcedureDeclaration procedureDeclaration);
}
