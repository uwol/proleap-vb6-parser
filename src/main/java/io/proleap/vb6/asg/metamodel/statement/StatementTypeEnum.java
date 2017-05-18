/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement;

public enum StatementTypeEnum implements StatementType {
	APP_ACTIVATE, BEEP, BLOCK_IF_THEN_ELSE, CH_DIR, CH_DRIVE, CLOSE, CONSTANT, DATE, DEFTYPE, DELETE_SETTING, DO_LOOP, ENUMERATION, EVENT, EXIT, FOR_EACH, FOR_NEXT, FUNCTION, IF, INLINE_IF_THEN_ELSE, LET, ON_ERROR, OPEN, PRINT, PROPERTY_GET, PROPERTY_LET, PROPERTY_SET, RE_DIM, RESUME, SAVE_SETTING, SELECT, SET, SUB, WHILE, WITH, WRITE
}
