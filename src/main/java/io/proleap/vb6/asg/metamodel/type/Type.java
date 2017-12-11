/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.type;

import io.proleap.vb6.asg.metamodel.Declaration;

public interface Type extends Declaration {

	@Override
	String getName();

	boolean isCollection();
}
