/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement;

public enum StatementTypeEnum implements StatementType {
	Constant, Enumeration, Exit, ForEach, ForNext, Function, If, Let, OnError, PropertyGet, PropertyLet, PropertySet, ReDim, Resume, Select, Set, Sub, While, With
}
