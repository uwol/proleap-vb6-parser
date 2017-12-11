/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.resolver.impl;

import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.resolver.TypeNameSanitizer;

public class TypeNameSanitizerImpl implements TypeNameSanitizer {

	@Override
	public String sanitize(final String typeName) {
		String result = typeName;
		result = result.replaceAll("^ADODB.", "");
		result = result.replaceAll("^Adodb.", "");
		result = result.replaceAll("^adodb.", "");

		return result;
	}

	@Override
	public String sanitize(final Type type) {
		final String typeName = type.getName();
		return sanitize(typeName);
	}

}
