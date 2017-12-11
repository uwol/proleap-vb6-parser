/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.api;

import io.proleap.vb6.asg.metamodel.ModelElement;
import io.proleap.vb6.asg.metamodel.type.ComplexType;

public interface ApiModule extends ModelElement, ComplexType {

	ApiProcedure getApiProcedure(String name);

	ApiProperty getApiProperty(String name);

	void registerApiProcedure(ApiProcedure apiProcedure);

	void registerApiProperty(ApiProperty apiProperty);
}
