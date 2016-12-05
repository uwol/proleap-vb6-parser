/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

public interface Operator {

	public enum VbOperator {
		AMP, AND, DIV, EQ, EQV, GEQ, GT, IMP, IS, LEQ, LIKE, LT, MINUS, MOD, MULT, NEQ, NOT, OR, PLUS, POW, XOR;
	}
}
