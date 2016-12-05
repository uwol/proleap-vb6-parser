/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.registry.api;

import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;

public interface ApiEnumerationRegistry {

	ApiEnumeration getApiEnumeration(String name);

	ApiEnumerationConstant getApiEnumerationConstant(String name);

	void registerApiEnumeration(ApiEnumeration apiEnumeration);
}
