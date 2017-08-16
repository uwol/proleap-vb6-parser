/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call;

import java.util.List;

import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;

public interface ArgValueAssignmentsContainer {

	void addArgValueAssignment(ArgValueAssignment argValueAssignment);

	List<ArgValueAssignment> getArgValueAssignments();
}
