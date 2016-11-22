/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel;

/**
 * Resumes execution after an error-handling routine is finished.
 */
public interface Resume extends VbScopedElement {

	LineLabel getLineLabel();
}
