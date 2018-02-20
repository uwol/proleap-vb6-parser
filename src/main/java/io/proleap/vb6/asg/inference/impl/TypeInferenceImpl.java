/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.inference.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import io.proleap.vb6.asg.inference.TypeInference;
import io.proleap.vb6.asg.metamodel.DefType;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.DefType.LetterRange;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.util.AsgStringUtils;

public class TypeInferenceImpl implements TypeInference {

	@Override
	public Type inferType(final Type declaredType, final Type defType, final Set<Type> assignedTypes) {
		final Type variantType = VbBaseType.VARIANT;

		final Type doubleType = VbBaseType.DOUBLE;
		final Type singleType = VbBaseType.SINGLE;

		final Type longType = VbBaseType.LONG;
		final Type integerType = VbBaseType.INTEGER;
		final Type byteType = VbBaseType.BYTE;

		/*
		 * collect all candidates
		 */
		final Set<Type> typeCandidates = new LinkedHashSet<Type>();
		typeCandidates.add(declaredType);
		typeCandidates.add(defType);
		typeCandidates.addAll(assignedTypes);

		/*
		 * sanitize candidates
		 */
		typeCandidates.remove(null);
		typeCandidates.remove(variantType);

		// DOUBLE exceeds SINGLE
		if (typeCandidates.contains(doubleType)) {
			typeCandidates.remove(singleType);
		}

		// LONG exceeds INTEGER and BYTE
		if (typeCandidates.contains(longType)) {
			typeCandidates.remove(integerType);
			typeCandidates.remove(byteType);
		}

		// INTEGER exceeds BYTE
		if (typeCandidates.contains(integerType)) {
			typeCandidates.remove(byteType);
		}

		/*
		 * determine result from candidates
		 */
		final Type result;

		// if the perfect candidate could be determined, even better than the
		// declared type
		if (typeCandidates.size() == 1) {
			result = typeCandidates.iterator().next();
		}
		// if an explicit type has been declared
		else if (declaredType != null && !variantType.equals(declaredType)) {
			result = declaredType;
		}
		// if an implicit type has been declared
		else if (defType != null && !variantType.equals(defType)) {
			result = defType;
		}
		// if nothing could be determined at all
		else {
			result = variantType;
		}

		return result;
	}

	@Override
	public Type inferTypeFromDefType(final Module module, final String variableName) {
		final Type result;

		if (variableName == null || variableName.isEmpty()) {
			result = null;
		} else {
			final Character firstLetter = variableName.charAt(0);

			Type typeFromDefType = null;

			for (final DefType defType : module.getDefTypes()) {
				for (final LetterRange letterRange : defType.getLetterRanges()) {
					final Character lower = letterRange.getLower().charAt(0);
					final Character upper;

					if (letterRange.getUpper() != null) {
						upper = letterRange.getUpper().charAt(0);
					} else {
						upper = null;
					}

					final boolean geq = AsgStringUtils.geq(firstLetter, lower);
					final boolean leq = AsgStringUtils.leq(firstLetter, upper);

					if (geq && leq) {
						typeFromDefType = defType.getBaseType();
					}
				}
			}

			result = typeFromDefType;
		}

		return result;
	}
}
