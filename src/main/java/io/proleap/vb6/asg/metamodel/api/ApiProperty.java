/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.api;

import java.util.List;

import io.proleap.vb6.asg.metamodel.Declaration;
import io.proleap.vb6.asg.metamodel.ModelElement;
import io.proleap.vb6.asg.metamodel.call.ApiPropertyCall;
import io.proleap.vb6.asg.metamodel.type.TypedElement;

public interface ApiProperty extends ModelElement, TypedElement, Declaration {

	void addApiPropertyCall(ApiPropertyCall apiPropertyCall);

	List<ApiPropertyCall> getApiPropertyCalls();
}
