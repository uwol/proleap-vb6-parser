/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.runner.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Trees;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import io.proleap.vb6.ThrowingErrorListener;
import io.proleap.vb6.VisualBasic6Lexer;
import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.StartRuleContext;
import io.proleap.vb6.runner.VbParseTestRunner;

/**
 * VB6 parse runner for JUnit tests.
 */
public class VbParseTestRunnerImpl implements VbParseTestRunner {

	private final static Logger LOG = LogManager.getLogger(VbParseTestRunnerImpl.class);

	public final static String TREE_SUFFIX = ".tree";

	protected void doCompareParseTree(final File treeFile, final StartRuleContext startRule,
			final VisualBasic6Parser parser) throws IOException {

		final String treeFileData = FileUtils.readFileToString(treeFile);

		if (!Strings.isBlank(treeFileData)) {
			LOG.info("Comparing parse tree with file {}.", treeFile.getName());

			final String inputFileTree = Trees.toStringTree(startRule, parser);
			final String cleanedInputFileTree = io.proleap.vb6.util.StringUtils.cleanFileTree(inputFileTree);
			final String cleanedTreeFileData = io.proleap.vb6.util.StringUtils.cleanFileTree(treeFileData);

			assertEquals(cleanedTreeFileData, cleanedInputFileTree);
		} else {
			LOG.info("Ignoring empty parse tree file {}.", treeFile.getName());
		}
	}

	protected void doParse(final File inputFile) throws IOException {
		LOG.info("Parsing file {}.", inputFile.getName());

		final InputStream inputStream = new FileInputStream(inputFile);
		final VisualBasic6Lexer lexer = new VisualBasic6Lexer(new ANTLRInputStream(inputStream));

		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);

		final StartRuleContext startRule = parser.startRule();
		final File treeFile = new File(inputFile.getAbsolutePath() + TREE_SUFFIX);

		if (treeFile.exists()) {
			doCompareParseTree(treeFile, startRule, parser);
		}
	}

	private boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName());
		return "cls".equals(extension);
	}

	private boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName());
		return "bas".equals(extension);
	}

	@Override
	public void parseDirectory(final File inputDirectory) throws IOException {
		if (inputDirectory.isDirectory() && !inputDirectory.isHidden()) {
			for (final File inputFile : inputDirectory.listFiles()) {
				if (inputFile.isFile() && !inputFile.isHidden()) {
					parseFile(inputFile);
				}
			}
		}
	}

	@Override
	public void parseFile(final File inputFile) throws IOException {
		if (!isClazzModule(inputFile) && !isStandardModule(inputFile)) {
			LOG.info("Ignoring file {}.", inputFile.getName());
		} else {
			doParse(inputFile);
		}
	}
}
