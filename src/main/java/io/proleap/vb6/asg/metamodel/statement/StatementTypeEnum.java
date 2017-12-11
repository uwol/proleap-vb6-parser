/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement;

public enum StatementTypeEnum implements StatementType {
	APP_ACTIVATE, BEEP, BLOCK_IF_THEN_ELSE, CALL, CH_DIR, CH_DRIVE, CLOSE, CONSTANT, DATE, DEFTYPE, DELETE_SETTING, DO_LOOP, ENUMERATION, EVENT, EXIT, EXPLICIT_CALL, FOR_EACH, FOR_NEXT, FUNCTION, IF, INLINE_IF_THEN_ELSE, LET, ON_ERROR, OPEN, PRINT, PROPERTY_GET, PROPERTY_LET, PROPERTY_SET, RE_DIM, RESUME, SAVE_SETTING, SELECT, SET, SUB, WHILE, WITH, WRITE
}
