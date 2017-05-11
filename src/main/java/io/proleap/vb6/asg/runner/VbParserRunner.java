/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.runner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import io.proleap.vb6.asg.metamodel.Program;

public interface VbParserRunner {

	Program analyzeFile(File inputFile) throws IOException;

	Program analyzeFile(File inputFile, Charset charset) throws IOException;

	Program analyzeFiles(List<File> inputFiles) throws IOException;

	Program analyzeFiles(List<File> inputFiles, Charset charset) throws IOException;

}
