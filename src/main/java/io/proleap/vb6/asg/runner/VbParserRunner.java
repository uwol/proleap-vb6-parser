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

	Program analyzeCode(String vbCode, String moduleName, VbParserParams params) throws IOException;

	Program analyzeFile(File vbFile) throws IOException;

	Program analyzeFile(File vbFile, VbParserParams params) throws IOException;

	Program analyzeFiles(List<File> vbFiles) throws IOException;

	Program analyzeFiles(List<File> vbFiles, VbParserParams params) throws IOException;
}
