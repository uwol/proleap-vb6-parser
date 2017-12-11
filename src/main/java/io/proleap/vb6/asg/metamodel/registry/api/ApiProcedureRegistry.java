/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.registry.api;

import io.proleap.vb6.asg.metamodel.api.ApiProcedure;

public interface ApiProcedureRegistry {

	ApiProcedure getApiProcedure(String name);

	void registerApiProcedure(ApiProcedure apiProcedure);
}
