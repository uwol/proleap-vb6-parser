/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.oop.api;

import io.proleap.vb6.parser.metamodel.ModelElement;
import io.proleap.vb6.parser.metamodel.oop.ComplexType;

public interface ApiModule extends ModelElement, ComplexType {

	ApiProcedure getApiProcedure(String name);

	ApiProperty getApiProperty(String name);

	void registerApiProcedure(ApiProcedure apiProcedure);

	void registerApiProperty(ApiProperty apiProperty);
}
