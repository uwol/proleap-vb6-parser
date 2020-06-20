/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.VisualBasic6Parser.StartRuleContext;
import io.proleap.vb6.asg.util.FilenameUtils;
import io.proleap.vb6.util.TreeUtils;

public class TestGenerator {

	private final static String[] DIRECTORIES_EXCLUDED = new String[] { "asg" };

	private final static File INPUT_DIRECTORY = new File("src/test/resources");

	private static final String JAVA_EXTENSION = ".java";

	private final static Logger LOG = LoggerFactory.getLogger(TestGenerator.class);

	private final static File OUTPUT_DIRECTORY = new File("src/test/java");

	private final static String OUTPUT_FILE_SUFFIX = "Test";

	private final static boolean RENEW_TREE_FILE = false;

	private static final String TREE_EXTENSION = ".tree";

	public static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateTestClass(final File vb6InputFile, final File outputDirectory, final String packageName)
			throws IOException {
		final String inputFilename = getInputFilename(vb6InputFile);
		final File outputFile = new File(outputDirectory + "/" + inputFilename + OUTPUT_FILE_SUFFIX + JAVA_EXTENSION);

		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			LOG.info("Creating unit test {}.", outputFile);

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));
			final String vb6InputFileName = vb6InputFile.getPath().replace("\\", "/");

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("\n");
			pWriter.write("import org.junit.Test;\n");
			pWriter.write("import io.proleap.vb6.runner.VbParseTestRunner;\n");
			pWriter.write("import io.proleap.vb6.runner.impl.VbParseTestRunnerImpl;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		final File inputFile = new File(\"" + vb6InputFileName + "\");\n");
			pWriter.write("		final VbParseTestRunner runner = new VbParseTestRunnerImpl();\n");
			pWriter.write("		runner.parseFile(inputFile);\n");
			pWriter.write("	}\n");
			pWriter.write("}");

			pWriter.flush();
			pWriter.close();
		}
	}

	public static void generateTestClasses(final File inputDirectory, final File outputDirectory,
			final String packageName) throws IOException {
		final String outputDirectoryPath = outputDirectory.getPath();

		if (inputDirectory.isDirectory()) {
			// for each of the files in the directory
			for (final File inputDirectoryFile : inputDirectory.listFiles()) {
				// if the file is a VB6 relevant file
				if (inputDirectoryFile.isFile() && !inputDirectoryFile.isHidden() && (isClazzModule(inputDirectoryFile)
						|| isStandardModule(inputDirectoryFile) || isForm(inputDirectoryFile))) {
					generateTestClass(inputDirectoryFile, outputDirectory, packageName);
					generateTreeFile(inputDirectoryFile, inputDirectory);
				}
				// else, if the file is a relevant directory
				else if (inputDirectoryFile.isDirectory() && !isDirectoryExcluded(inputDirectoryFile)) {
					final File subInputDirectory = inputDirectoryFile;
					final String subInputDirectoryName = subInputDirectory.getName();

					if (!".".equals(subInputDirectoryName) && !"..".equals(subInputDirectoryName)) {
						/*
						 * determine the output directory, where test classes should be placed
						 */
						final File subOutputDirectory = new File(outputDirectoryPath + "/" + subInputDirectoryName);
						subOutputDirectory.mkdirs();

						// determine the package name of test classes
						final String subPackageName = packageName == null || packageName.isEmpty()
								? subInputDirectoryName
								: packageName + "." + subInputDirectoryName;

						generateTestClasses(subInputDirectory, subOutputDirectory, subPackageName);
					}
				}
			}
		}
	}

	public static void generateTreeFile(final File vb6InputFile, final File outputDirectory) throws IOException {
		final File outputFile = new File(outputDirectory + "/" + vb6InputFile.getName() + TREE_EXTENSION);

		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile || RENEW_TREE_FILE) {
			LOG.info("Creating tree file {}.", outputFile);

			final InputStream inputStream = new FileInputStream(vb6InputFile);
			final VisualBasic6Lexer lexer = new VisualBasic6Lexer(CharStreams.fromStream(inputStream));
			final CommonTokenStream tokens = new CommonTokenStream(lexer);
			final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);
			final StartRuleContext startRule = parser.startRule();
			final String inputFileTree = TreeUtils.toStringTree(startRule, parser);

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));

			pWriter.write(inputFileTree);
			pWriter.flush();
			pWriter.close();
		}
	}

	protected static String getInputFilename(final File inputFile) {
		final String result = firstToUpper(FilenameUtils.removeExtension(inputFile.getName()));
		return result;
	}

	protected static boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "cls".equals(extension);
	}

	protected static boolean isDirectoryExcluded(final File directory) {
		final String directoryName = directory.getName();
		return Arrays.asList(DIRECTORIES_EXCLUDED).contains(directoryName);
	}

	protected static boolean isForm(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "frm".equals(extension);
	}

	protected static boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "bas".equals(extension);
	}

	public static void main(final String[] args) throws IOException {
		generateTestClasses(INPUT_DIRECTORY, OUTPUT_DIRECTORY, "");
	}
}
