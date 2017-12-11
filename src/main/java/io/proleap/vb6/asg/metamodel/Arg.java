/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.List;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.asg.metamodel.call.ArgCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.event.Event;
import io.proleap.vb6.asg.metamodel.type.AssignableTypedElement;

public interface Arg extends AssignableTypedElement, NamedElement, ScopedElement {

	void addArgCall(ArgCall argCall);

	List<ArgCall> getArgCalls();

	@Override
	ArgContext getCtx();

	Call getDefaultValueCall();

	Event getEvent();

	Procedure getProcedure();

	ProcedureDeclaration getProcedureDeclaration();

	boolean isCollection();

	boolean isOptional();

	void setDefaultValueCall(Call defaultValueCall);

	void setEvent(Event event);

	void setProcedure(Procedure procedure);

	void setProcedureDeclaration(ProcedureDeclaration procedureDeclaration);
}
