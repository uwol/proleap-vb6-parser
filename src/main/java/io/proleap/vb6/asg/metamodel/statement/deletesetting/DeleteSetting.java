/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.statement.deletesetting;

import io.proleap.vb6.VisualBasic6Parser.DeleteSettingStmtContext;
import io.proleap.vb6.asg.metamodel.statement.Statement;

/**
 * Deletes a section or key setting from an application's entry in the Windows
 * registry.
 */
public interface DeleteSetting extends Statement {

	@Override
	DeleteSettingStmtContext getCtx();

}
