/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.List;

import io.proleap.vb6.asg.metamodel.type.BaseType;

/**
 * On module level sets the default data type for variables, arguments passed to
 * procedures, and the return types for Function and Property Get procedures
 * whose names start with the specified characters.
 */
public interface DefType {

	public interface LetterRange {
		String getLower();

		String getUpper();
	}

	void addLetterRange(String lower, String upper);

	BaseType getBaseType();

	List<LetterRange> getLetterRanges();
}
