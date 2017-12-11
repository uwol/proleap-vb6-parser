/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call;

import io.proleap.vb6.asg.metamodel.NamedElement;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

public interface Call extends ScopedElement, TypedElement, NamedElement {

	public enum CallContext {
		LET_LEFT_HAND_SIDE
	}

	public enum CallType {
		API_ENUMERATION_CALL, API_ENUMERATION_CONSTANT_CALL, API_PROCEDURE_CALL, API_PROPERTY_CALL, ARG_CALL, ARRAY_ELEMENT_CALL, CONSTANT_CALL, DICTIONARY_CALL, ELEMENT_VARIABLE_CALL, ENUMERATION_CALL, ENUMERATION_CONSTANT_CALL, FUNCTION_CALL, ME_CALL, MODULE_CALL, PROPERTY_GET_CALL, PROPERTY_LET_CALL, PROPERTY_SET_CALL, RETURN_VALUE_CALL, SUB_CALL, TYPE_ELEMENT_CALL, UNDEFINED_CALL, VARIABLE_CALL;
	}

	CallType getCallType();

	Call unwrap();
}
