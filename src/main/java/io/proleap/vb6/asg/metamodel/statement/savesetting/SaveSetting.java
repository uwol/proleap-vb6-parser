/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.savesetting;

import io.proleap.vb6.VisualBasic6Parser.SaveSettingStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Saves or creates an application entry in the application's entry in the
 * Windows registry.
 */
public interface SaveSetting extends Statement {

	@Override
	SaveSettingStmtContext getCtx();

}
