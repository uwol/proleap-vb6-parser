/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.runner;

import java.io.File;
import java.io.IOException;

import io.proleap.vb6.parser.metamodel.Program;

public interface VbParserRunner {

	public Program analyzeDirectory(File inputDirectory) throws IOException;

	public Program analyzeFile(File inputFile) throws IOException;

}
