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

package org.vb6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;

public class TestGenerator {

	final static File gplInputDirectory = new File(
			"src/test/resources/org/vb6/gpl/statements/");
	final static File gplOutputDirectory = new File(
			"src/test/java/org/vb6/gpl/statements/");

	final static File msdnInputDirectory = new File(
			"src/test/resources/org/vb6/msdn/statements/");
	final static File msdnOutputDirectory = new File(
			"src/test/java/org/vb6/msdn/statements/");

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

			System.out.println("Creating " + outputFile);
			outputFile.createNewFile();

			final PrintWriter pWriter = new PrintWriter(new FileWriter(
					outputFile));

			final String vb6InputFileName = vb6InputFile.getPath().replace(
					"\\", "/");

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("import java.io.FileInputStream;\n");
			pWriter.write("import java.io.InputStream;\n");
			pWriter.write("\n");
			pWriter.write("import org.antlr.v4.runtime.ANTLRInputStream;\n");
			pWriter.write("import org.antlr.v4.runtime.CommonTokenStream;\n");
			pWriter.write("import org.junit.Test;\n");
			pWriter.write("import org.vb6.ThrowingErrorListener;\n");
			pWriter.write("import org.vb6.VisualBasic6Lexer;\n");
			pWriter.write("import org.vb6.VisualBasic6Parser;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		final File inputFile = new File(\""
					+ vb6InputFileName + "\");\n");
			pWriter.write("\n");
			pWriter.write("		final InputStream inputStream = new FileInputStream(inputFile);\n");
			pWriter.write("		final VisualBasic6Lexer lexer = new VisualBasic6Lexer(new ANTLRInputStream(inputStream));\n");
			pWriter.write("\n");
			pWriter.write("		lexer.removeErrorListeners();\n");
			pWriter.write("		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);\n");
			pWriter.write("\n");
			pWriter.write("		final CommonTokenStream tokens = new CommonTokenStream(lexer);\n");
			pWriter.write("		final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);\n");
			pWriter.write("\n");
			pWriter.write("		parser.removeErrorListeners();\n");
			pWriter.write("		parser.addErrorListener(ThrowingErrorListener.INSTANCE);\n");
			pWriter.write("\n");
			pWriter.write("		parser.startRule();\n");
			pWriter.write("	}\n");
			pWriter.write("}");

			pWriter.flush();
			pWriter.close();
		}
	}

	public static void generateTestClasses(final File inputDirectory,
			final File outputDirectory, final String packageName)
			throws IOException {
		if (inputDirectory.isDirectory()) {
			for (final File inputFile : inputDirectory.listFiles()) {
				generateTestClass(inputFile, outputDirectory, packageName);
			}
		}
	}

	public static void main(final String[] args) throws IOException {
		generateTestClasses(msdnInputDirectory, msdnOutputDirectory,
				"org.vb6.msdn.statements");
		generateTestClasses(gplInputDirectory, gplOutputDirectory,
				"org.vb6.gpl.statements");
	}
}
