/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.type;

public enum VbBaseType implements BaseType {

	BOOLEAN("Boolean", false), BYTE("Byte", false), COLLECTION("Collection", true), COLOR("Color", false), CURRENCY(
			"Currency", false), DATE("Date", false), DOUBLE("Double", false), FILENUMBER("FileNumber",
					false), INTEGER("Integer", false), LONG("Long",
							false), SINGLE("Single", false), STRING("String", false), VARIANT("Variant", false);

	public static VbBaseType forString(final String name) {
		for (final VbBaseType vbType : values()) {
			if (vbType.getName().equals(name)) {
				return vbType;
			}
		}

		return null;
	}

	protected final boolean isCollection;

	protected final String name;

	private VbBaseType(final String name, final boolean isCollection) {
		this.name = name;
		this.isCollection = isCollection;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}
}
