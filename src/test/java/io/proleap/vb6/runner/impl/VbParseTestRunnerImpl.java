/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.runner.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Trees;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.VisualBasic6Lexer;
import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.StartRuleContext;
import io.proleap.vb6.asg.params.VbParserParams;
import io.proleap.vb6.asg.params.impl.VbParserParamsImpl;
import io.proleap.vb6.asg.runner.ThrowingErrorListener;
import io.proleap.vb6.runner.VbParseTestRunner;

/**
 * VB6 parse runner for JUnit tests.
 */
public class VbParseTestRunnerImpl implements VbParseTestRunner {

	private final static Logger LOG = LoggerFactory.getLogger(VbParseTestRunnerImpl.class);

	public final static String TREE_SUFFIX = ".tree";

	protected static boolean isForm(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "frm".equals(extension);
	}

	protected VbParserParams createDefaultParams() {
		final VbParserParams result = new VbParserParamsImpl();
		return result;
	}

	protected void doCompareParseTree(final File treeFile, final StartRuleContext startRule,
			final VisualBasic6Parser parser) throws IOException {

		final String treeFileData = FileUtils.readFileToString(treeFile, StandardCharsets.UTF_8);

		if (!StringUtils.isEmpty(treeFileData)) {
			LOG.info("Comparing parse tree with file {}.", treeFile.getName());

			final String inputFileTree = Trees.toStringTree(startRule, parser);
			final String cleanedInputFileTree = io.proleap.vb6.util.VbTestStringUtils.cleanFileTree(inputFileTree);
			final String cleanedTreeFileData = io.proleap.vb6.util.VbTestStringUtils.cleanFileTree(treeFileData);

			assertEquals(cleanedTreeFileData, cleanedInputFileTree);
		} else {
			LOG.info("Ignoring empty parse tree file {}.", treeFile.getName());
		}
	}

	protected void doParse(final File inputFile, final VbParserParams params) throws IOException {
		final Charset charset = params.getCharset();

		LOG.info("Parsing file {} with charset {}.", inputFile.getName(), charset);

		final InputStream inputStream = new FileInputStream(inputFile);
		final VisualBasic6Lexer lexer = new VisualBasic6Lexer(CharStreams.fromStream(inputStream, charset));

		if (!params.getIgnoreSyntaxErrors()) {
			lexer.removeErrorListeners();
			lexer.addErrorListener(new ThrowingErrorListener());
		}

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);

		if (!params.getIgnoreSyntaxErrors()) {
			parser.removeErrorListeners();
			parser.addErrorListener(new ThrowingErrorListener());
		}

		final StartRuleContext startRule = parser.startRule();
		final File treeFile = new File(inputFile.getAbsolutePath() + TREE_SUFFIX);

		if (treeFile.exists()) {
			doCompareParseTree(treeFile, startRule, parser);
		}
	}

	protected boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName());
		return "cls".equals(extension);
	}

	protected boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName());
		return "bas".equals(extension);
	}

	@Override
	public void parseFile(final File inputFile) throws IOException {
		if (!isClazzModule(inputFile) && !isStandardModule(inputFile) && !isForm(inputFile)) {
			LOG.info("Ignoring file {}.", inputFile.getName());
		} else {
			doParse(inputFile, createDefaultParams());
		}
	}
}
