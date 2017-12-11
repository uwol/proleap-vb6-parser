/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.util;

import java.util.Collection;

import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.ModelElement;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.api.ApiModule;
import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.BaseType;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.metamodel.type.Type;

public class CastUtils {

	public static ApiEnumeration castApiEnumeration(final Collection<ModelElement> elements) {
		ApiEnumeration result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final ApiEnumeration enumeration = castApiEnumeration(element);

				if (enumeration != null) {
					result = enumeration;
					break;
				}
			}
		}

		return result;
	}

	public static ApiEnumeration castApiEnumeration(final ModelElement element) {
		return element != null && element instanceof ApiEnumeration ? (ApiEnumeration) element : null;
	}

	public static ApiEnumeration castApiEnumeration(final Type type) {
		return type != null && type instanceof ApiEnumeration ? (ApiEnumeration) type : null;
	}

	public static ApiEnumerationConstant castApiEnumerationConstant(final Collection<ModelElement> elements) {
		ApiEnumerationConstant result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final ApiEnumerationConstant apiEnumerationConstant = castApiEnumerationConstant(element);

				if (apiEnumerationConstant != null) {
					result = apiEnumerationConstant;
					break;
				}
			}
		}

		return result;
	}

	public static ApiEnumerationConstant castApiEnumerationConstant(final ModelElement element) {
		return element != null && element instanceof ApiEnumerationConstant ? (ApiEnumerationConstant) element : null;
	}

	public static ApiModule castApiModule(final Type type) {
		return type != null && type instanceof ApiModule ? (ApiModule) type : null;
	}

	public static ApiProcedure castApiProcedure(final Collection<ModelElement> elements) {
		ApiProcedure result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final ApiProcedure apiProcedure = castApiProcedure(element);

				if (apiProcedure != null) {
					result = apiProcedure;
					break;
				}
			}
		}

		return result;
	}

	public static ApiProcedure castApiProcedure(final ModelElement element) {
		return element != null && element instanceof ApiProcedure ? (ApiProcedure) element : null;
	}

	public static ApiProperty castApiProperty(final Collection<ModelElement> elements) {
		ApiProperty result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final ApiProperty apiProperty = castApiProperty(element);

				if (apiProperty != null) {
					result = apiProperty;
					break;
				}
			}
		}

		return result;
	}

	public static ApiProperty castApiProperty(final ModelElement element) {
		return element != null && element instanceof ApiProperty ? (ApiProperty) element : null;
	}

	public static Arg castArg(final Collection<ModelElement> elements) {
		Arg result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Arg arg = castArg(element);

				if (arg != null) {
					result = arg;
					break;
				}
			}
		}

		return result;
	}

	public static Arg castArg(final ModelElement element) {
		return element != null && element instanceof Arg ? (Arg) element : null;
	}

	public static BaseType castBaseType(final Type type) {
		return type != null && type instanceof BaseType ? (BaseType) type : null;
	}

	public static ComplexType castComplexType(final Type type) {
		return type != null && type instanceof ComplexType ? (ComplexType) type : null;
	}

	public static Constant castConst(final Collection<ModelElement> elements) {
		Constant result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Constant constant = castConst(element);

				if (constant != null) {
					result = constant;
					break;
				}
			}
		}

		return result;
	}

	public static Constant castConst(final ModelElement element) {
		return element != null && element instanceof Constant ? (Constant) element : null;
	}

	public static ElementVariable castElementVariable(final Collection<ModelElement> elements) {
		ElementVariable result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final ElementVariable elementVariable = castElementVariable(element);

				if (elementVariable != null) {
					result = elementVariable;
					break;
				}
			}
		}

		return result;
	}

	public static ElementVariable castElementVariable(final ModelElement element) {
		return element != null && element instanceof ElementVariable ? (ElementVariable) element : null;
	}

	public static Enumeration castEnumeration(final Collection<ModelElement> elements) {
		Enumeration result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Enumeration enumeration = castEnumeration(element);

				if (enumeration != null) {
					result = enumeration;
					break;
				}
			}
		}

		return result;
	}

	public static Enumeration castEnumeration(final ModelElement element) {
		return element != null && element instanceof Enumeration ? (Enumeration) element : null;
	}

	public static Enumeration castEnumeration(final Type type) {
		return type != null && type instanceof Enumeration ? (Enumeration) type : null;
	}

	public static EnumerationConstant castEnumerationConstant(final Collection<ModelElement> elements) {
		EnumerationConstant result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final EnumerationConstant enumerationConstant = castEnumerationConstant(element);

				if (enumerationConstant != null) {
					result = enumerationConstant;
					break;
				}
			}
		}

		return result;
	}

	public static EnumerationConstant castEnumerationConstant(final ModelElement element) {
		return element != null && element instanceof EnumerationConstant ? (EnumerationConstant) element : null;
	}

	public static Function castFunction(final Collection<ModelElement> elements) {
		Function result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Function function = castFunction(element);

				if (function != null) {
					result = function;
					break;
				}
			}
		}

		return result;
	}

	public static Function castFunction(final ModelElement element) {
		return element != null && element instanceof Function ? (Function) element : null;
	}

	public static LineLabel castLineLabel(final Collection<ModelElement> elements) {
		LineLabel result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final LineLabel lineLabel = castLineLabel(element);

				if (lineLabel != null) {
					result = lineLabel;
					break;
				}
			}
		}

		return result;
	}

	public static LineLabel castLineLabel(final ModelElement element) {
		return element != null && element instanceof LineLabel ? (LineLabel) element : null;
	}

	public static Module castModule(final Type type) {
		return type != null && type instanceof Module ? (Module) type : null;
	}

	public static Procedure castProcedure(final Collection<ModelElement> elements) {
		Procedure result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Procedure procedure = castProcedure(element);

				if (procedure != null) {
					result = procedure;
					break;
				}
			}
		}

		return result;
	}

	public static Procedure castProcedure(final ModelElement element) {
		return element != null && element instanceof Procedure ? (Procedure) element : null;
	}

	public static PropertyGet castPropertyGet(final Collection<ModelElement> elements) {
		PropertyGet result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final PropertyGet propertyGet = castPropertyGet(element);

				if (propertyGet != null) {
					result = propertyGet;
					break;
				}
			}
		}

		return result;
	}

	public static PropertyGet castPropertyGet(final ModelElement element) {
		return element != null && element instanceof PropertyGet ? (PropertyGet) element : null;
	}

	public static PropertyLet castPropertyLet(final Collection<ModelElement> elements) {
		PropertyLet result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final PropertyLet propertyLet = castPropertyLet(element);

				if (propertyLet != null) {
					result = propertyLet;
					break;
				}
			}
		}

		return result;
	}

	public static PropertyLet castPropertyLet(final ModelElement element) {
		return element != null && element instanceof PropertyLet ? (PropertyLet) element : null;
	}

	public static PropertySet castPropertySet(final Collection<ModelElement> elements) {
		PropertySet result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final PropertySet propertySet = castPropertySet(element);

				if (propertySet != null) {
					result = propertySet;
					break;
				}
			}
		}

		return result;
	}

	public static PropertySet castPropertySet(final ModelElement element) {
		return element != null && element instanceof PropertySet ? (PropertySet) element : null;
	}

	public static Sub castSub(final Collection<ModelElement> elements) {
		Sub result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Sub sub = castSub(element);

				if (sub != null) {
					result = sub;
					break;
				}
			}
		}

		return result;
	}

	public static Sub castSub(final ModelElement element) {
		return element != null && element instanceof Sub ? (Sub) element : null;
	}

	public static io.proleap.vb6.asg.metamodel.TypeElement castTypeElement(final Collection<ModelElement> elements) {
		io.proleap.vb6.asg.metamodel.TypeElement result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final io.proleap.vb6.asg.metamodel.TypeElement typeElement = castTypeElement(element);

				if (typeElement != null) {
					result = typeElement;
					break;
				}
			}
		}

		return result;
	}

	public static io.proleap.vb6.asg.metamodel.TypeElement castTypeElement(final ModelElement element) {
		return element != null && element instanceof io.proleap.vb6.asg.metamodel.TypeElement
				? (io.proleap.vb6.asg.metamodel.TypeElement) element
				: null;
	}

	public static io.proleap.vb6.asg.metamodel.Type castTypeStmtType(final Type type) {
		return type != null && type instanceof io.proleap.vb6.asg.metamodel.Type
				? (io.proleap.vb6.asg.metamodel.Type) type
				: null;
	}

	public static Variable castVariable(final Collection<ModelElement> elements) {
		Variable result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Variable variable = castVariable(element);

				if (variable != null) {
					result = variable;
					break;
				}
			}
		}

		return result;
	}

	public static Variable castVariable(final ModelElement element) {
		return element != null && element instanceof Variable ? (Variable) element : null;
	}
}
