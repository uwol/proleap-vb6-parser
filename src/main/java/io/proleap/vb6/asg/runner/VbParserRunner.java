/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.runner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.params.VbParserParams;

public interface VbParserRunner {

	Program analyzeFile(File inputFile) throws IOException;

	Program analyzeFile(File inputFile, VbParserParams params) throws IOException;

	Program analyzeFiles(List<File> inputFiles) throws IOException;

	Program analyzeFiles(List<File> inputFiles, VbParserParams params) throws IOException;

}
