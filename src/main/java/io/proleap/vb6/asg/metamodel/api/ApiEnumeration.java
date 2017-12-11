/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.api;

import java.util.List;

import io.proleap.vb6.asg.metamodel.ModelElement;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationCall;
import io.proleap.vb6.asg.metamodel.type.ComplexType;

public interface ApiEnumeration extends ModelElement, ComplexType {

	void addApiEnumerationCall(ApiEnumerationCall apiEnumerationCall);

	List<ApiEnumerationCall> getApiEnumerationCalls();

	ApiEnumerationConstant getApiEnumerationConstant(String name);

	void registerApiEnumerationConstant(ApiEnumerationConstant apiEnumerationConstant);

}
