/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call;

import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;

public interface PropertyLetCall extends Call, ArgValueAssignmentsContainer {

	PropertyLet getPropertyLet();

}
