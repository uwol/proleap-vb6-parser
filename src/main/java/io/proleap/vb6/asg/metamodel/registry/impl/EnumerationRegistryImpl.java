/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.registry.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.asg.metamodel.registry.EnumerationRegistry;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;

public class EnumerationRegistryImpl implements EnumerationRegistry {

	final List<Enumeration> enumerations = new ArrayList<Enumeration>();

	@Override
	public EnumerationConstant getEnumerationConstant(final String name) {
		for (final Enumeration enumeration : enumerations) {
			if (enumeration.getEnumerationConstant(name) != null) {
				return enumeration.getEnumerationConstant(name);
			}
		}

		return null;
	}

	@Override
	public void registerEnumeration(final Enumeration enumeration) {
		enumerations.add(enumeration);
	}
}
