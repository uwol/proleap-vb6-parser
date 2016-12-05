/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.registry.impl;

import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.registry.TypeNameSanitizer;

public class VbTypeNameSanitizerImpl implements TypeNameSanitizer {

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
