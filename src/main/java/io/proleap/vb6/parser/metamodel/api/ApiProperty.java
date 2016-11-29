/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.api;

import java.util.List;

import io.proleap.vb6.parser.metamodel.Declaration;
import io.proleap.vb6.parser.metamodel.ModelElement;
import io.proleap.vb6.parser.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.parser.metamodel.type.TypedElement;

public interface ApiProperty extends ModelElement, TypedElement, Declaration {

	void addApiPropertyCall(ApiPropertyCall apiPropertyCall);

	List<ApiPropertyCall> getApiPropertyCalls();
}
