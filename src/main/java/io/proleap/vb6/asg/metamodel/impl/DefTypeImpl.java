/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.asg.metamodel.DefType;
import io.proleap.vb6.asg.metamodel.type.BaseType;

public class DefTypeImpl implements DefType {

	public class LetterRangeImpl implements LetterRange {

		protected String lower;

		protected String upper;

		public LetterRangeImpl(final String lower, final String upper) {
			this.lower = lower;
			this.upper = upper;
		}

		@Override
		public String getLower() {
			return lower;
		}

		@Override
		public String getUpper() {
			return upper;
		}

		@Override
		public String toString() {
			return lower + "-" + upper;
		}
	}

	protected BaseType baseType;

	protected List<LetterRange> letterRanges = new ArrayList<LetterRange>();

	public DefTypeImpl(final BaseType baseType) {
		this.baseType = baseType;
	}

	@Override
	public void addLetterRange(final String lower, final String upper) {
		letterRanges.add(new LetterRangeImpl(lower, upper));
	}

	@Override
	public BaseType getBaseType() {
		return baseType;
	}

	@Override
	public List<LetterRange> getLetterRanges() {
		return letterRanges;
	}

	@Override
	public String toString() {
		return baseType + ": " + letterRanges;
	}
}
