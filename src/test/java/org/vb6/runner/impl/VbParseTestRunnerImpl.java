/*
Copyright (C) 2014 u.wol@wwu.de

This file is part of vb6transform.

vb6transform is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

vb6transform is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with vb6transform. If not, see <http://www.gnu.org/licenses/>.
 */

package org.vb6.runner.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.vb6.ThrowingErrorListener;
import org.vb6.VisualBasic6Lexer;
import org.vb6.VisualBasic6Parser;
import org.vb6.runner.VbParseTestRunner;

public class VbParseTestRunnerImpl implements VbParseTestRunner {

	private boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils
				.getExtension(inputFile.getName());
		return "cls".equals(extension);
	}

	private boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils
				.getExtension(inputFile.getName());
		return "bas".equals(extension);
	}

	@Override
	public void parseDirectory(final File inputDirectory) throws IOException {
		for (final File inputFile : inputDirectory.listFiles()) {
			if (inputFile.isFile() && !inputFile.isHidden()) {
				parseFile(inputFile);
			}
		}
	}

	@Override
	public void parseFile(final File inputFile) throws IOException {
		if (!isClazzModule(inputFile) && !isStandardModule(inputFile)) {
			System.out.println("Ignoring " + inputFile.getName());
		} else {
			System.out.println("Parsing " + inputFile.getName());

			final InputStream inputStream = new FileInputStream(inputFile);
			final VisualBasic6Lexer lexer = new VisualBasic6Lexer(
					new ANTLRInputStream(inputStream));

			lexer.removeErrorListeners();
			lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

			final CommonTokenStream tokens = new CommonTokenStream(lexer);
			final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);

			parser.removeErrorListeners();
			parser.addErrorListener(ThrowingErrorListener.INSTANCE);

			parser.startRule();
		}
	}
}
