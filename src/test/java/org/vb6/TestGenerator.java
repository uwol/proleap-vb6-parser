/*
Copyright (C) 2014 u.wol@wwu.de

This file is part of vb6grammar.

vb6grammar is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

vb6grammar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with vb6grammar. If not, see <http://www.gnu.org/licenses/>.
 */

package org.vb6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestGenerator {

	private final static File inputDirectory = new File(
			"src/test/resources/org/vb6");

	private final static Logger LOG = LogManager.getLogger(TestGenerator.class);

	private final static File outputDirectory = new File(
			"src/test/java/org/vb6");

	public static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateTestClass(final File vb6InputFile,
			final File outputDirectory, final String packageName)
			throws IOException {
		if (vb6InputFile.isFile() && !vb6InputFile.isHidden()) {
			final String inputFilename = firstToUpper(FilenameUtils
					.removeExtension(vb6InputFile.getName()));

			final File outputFile = new File(outputDirectory + "/"
					+ inputFilename + "Test.java");

			LOG.info("Creating {}.", outputFile);
			outputFile.createNewFile();

			final PrintWriter pWriter = new PrintWriter(new FileWriter(
					outputFile));

			final String vb6InputFileName = vb6InputFile.getPath().replace(
					"\\", "/");

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("\n");
			pWriter.write("import org.junit.Test;\n");
			pWriter.write("import org.vb6.runner.VbParseTestRunner;\n");
			pWriter.write("import org.vb6.runner.impl.VbParseTestRunnerImpl;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		final File inputFile = new File(\""
					+ vb6InputFileName + "\");\n");
			pWriter.write("		final VbParseTestRunner runner = new VbParseTestRunnerImpl();\n");
			pWriter.write("		runner.parseFile(inputFile);\n");
			pWriter.write("	}\n");
			pWriter.write("}");

			pWriter.flush();
			pWriter.close();
		}
	}

	public static void generateTestClasses(final File inputDirectory,
			final File outputDirectory, final String packageName)
			throws IOException {
		final String outputDirectoryPath = outputDirectory.getPath();

		if (inputDirectory.isDirectory()) {
			// for each of the files in the directory
			for (final File inputDirectoryFile : inputDirectory.listFiles()) {
				// if the file is a VB6 relevant file
				if (isClazzModule(inputDirectoryFile)
						|| isStandardModule(inputDirectoryFile)) {
					generateTestClass(inputDirectoryFile, outputDirectory,
							packageName);
				}
				// else, if the file is a directory
				else if (inputDirectoryFile.isDirectory()) {
					final File subInputDirectory = inputDirectoryFile;
					final String subInputDirectoryName = subInputDirectory
							.getName();

					if (!".".equals(subInputDirectoryName)
							&& !"..".equals(subInputDirectoryName)) {
						/*
						 * determine the output directory, where test classes
						 * should be placed
						 */
						final File subOutputDirectory = new File(
								outputDirectoryPath + "/"
										+ subInputDirectoryName);

						// determine the package name of test classes
						final String subPackageName = packageName + "."
								+ subInputDirectoryName;

						generateTestClasses(subInputDirectory,
								subOutputDirectory, subPackageName);
					}
				}
			}
		}
	}

	protected static boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils
				.getExtension(inputFile.getName()).toLowerCase();
		return "cls".equals(extension);
	}

	protected static boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils
				.getExtension(inputFile.getName()).toLowerCase();
		return "bas".equals(extension);
	}

	public static void main(final String[] args) throws IOException {
		generateTestClasses(inputDirectory, outputDirectory, "org.vb6");
	}
}
