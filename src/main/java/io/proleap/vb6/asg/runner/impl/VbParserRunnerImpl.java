/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.runner.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.vb6.VisualBasic6Lexer;
import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.StartRuleContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiModule;
import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.metamodel.api.impl.ApiEnumerationConstantImpl;
import io.proleap.vb6.asg.metamodel.api.impl.ApiEnumerationImpl;
import io.proleap.vb6.asg.metamodel.api.impl.ApiModuleImpl;
import io.proleap.vb6.asg.metamodel.api.impl.ApiProcedureImpl;
import io.proleap.vb6.asg.metamodel.api.impl.ApiPropertyImpl;
import io.proleap.vb6.asg.metamodel.impl.ProgramImpl;
import io.proleap.vb6.asg.metamodel.registry.TypeRegistry;
import io.proleap.vb6.asg.metamodel.registry.api.ApiProcedureRegistry;
import io.proleap.vb6.asg.runner.VbParserRunner;
import io.proleap.vb6.asg.visitor.ParserVisitor;
import io.proleap.vb6.asg.visitor.impl.VbDeclarationVisitorImpl;
import io.proleap.vb6.asg.visitor.impl.VbExpressionVisitorImpl;
import io.proleap.vb6.asg.visitor.impl.VbModuleNameAnalyzerVisitorImpl;
import io.proleap.vb6.asg.visitor.impl.VbTypeAssignmentVisitorImpl;
import io.proleap.vb6.asg.visitor.impl.VbTypeVisitorImpl;

public class VbParserRunnerImpl implements VbParserRunner {

	protected final static Logger LOG = LogManager.getLogger(VbParserRunnerImpl.class);

	/**
	 * determines, how deep expressions and their type assignments should be
	 * analysed
	 */
	protected final int TYPE_ANALYSIS_DEPTH = 4;

	protected void analyze(final Program program) {
		registerModelElements(program);

		analyzeDeclarations(program);
		analyzeExpressions(program);

		for (int i = 0; i < TYPE_ANALYSIS_DEPTH; i++) {
			analyzeTypeAssignments(program);
		}
	}

	protected void analyzeDeclarations(final Program program) {
		for (final Module module : program.getModules()) {
			final ParserVisitor visitor = new VbDeclarationVisitorImpl(module);

			LOG.info("Analyzing declaration of module {}.", module.getName());
			visitor.visit(module.getCtx());
		}
	}

	/**
	 * VB modules can have their module name declared by an attribute named
	 * VB_NAME, hat can deviate from the module file name. The value of
	 * attribute VB_NAME is returned by this method.
	 */
	protected String analyzeDeclaredModuleName(final StartRuleContext ctx) {
		final VbModuleNameAnalyzerVisitorImpl visitor = new VbModuleNameAnalyzerVisitorImpl();
		final String moduleName = visitor.visit(ctx);

		if (moduleName != null) {
			LOG.info("Found declared module name {}.", moduleName);
		}

		return moduleName;
	}

	protected void analyzeExpressions(final Program program) {
		for (final Module module : program.getModules()) {
			final ParserVisitor visitor = new VbExpressionVisitorImpl(module);

			LOG.info("Analyzing expressions of module {}.", module.getName());
			visitor.visit(module.getCtx());
		}
	}

	@Override
	public Program analyzeFile(final File inputFile) throws IOException {
		return analyzeFile(inputFile, Charset.defaultCharset());
	}

	@Override
	public Program analyzeFile(final File inputFile, final Charset charset) throws IOException {
		final Program program = new ProgramImpl();

		parseFile(inputFile, charset, program);
		analyze(program);

		return program;
	}

	@Override
	public Program analyzeFiles(final List<File> inputFiles) throws IOException {
		return analyzeFiles(inputFiles, null);
	}

	@Override
	public Program analyzeFiles(final List<File> inputFiles, final Charset charset) throws IOException {
		final Program program = new ProgramImpl();

		for (final File inputFile : inputFiles) {
			parseFile(inputFile, charset, program);
		}

		analyze(program);

		return program;
	}

	protected void analyzeTypeAssignments(final Program program) {
		for (final Module module : program.getModules()) {
			final ParserVisitor visitor = new VbTypeAssignmentVisitorImpl(module);

			LOG.info("Analyzing type assignments of module {}.", module.getName());
			visitor.visit(module.getCtx());
		}
	}

	protected String getModuleName(final File inputFile) {
		return StringUtils.capitalize(FilenameUtils.removeExtension(inputFile.getName()));
	}

	protected boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "cls".equals(extension);
	}

	protected boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "bas".equals(extension);
	}

	protected void parseFile(final File inputFile, final Charset charset, final Program program) throws IOException {
		if (!inputFile.isFile()) {
			LOG.warn("Could not find file {}", inputFile.getAbsolutePath());
		} else {
			LOG.info("Parsing file {}.", inputFile.getName());

			final InputStream inputStream = new FileInputStream(inputFile);

			final VisualBasic6Lexer lexer = new VisualBasic6Lexer(CharStreams.fromStream(inputStream, charset));

			// get a list of matched tokens
			final CommonTokenStream tokens = new CommonTokenStream(lexer);

			// pass the tokens to the parser
			final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);

			// specify our entry point
			final StartRuleContext ctx = parser.startRule();

			// determine the module name
			final String declaredModuleName = analyzeDeclaredModuleName(ctx);
			final String moduleName;

			if (declaredModuleName != null && !declaredModuleName.isEmpty()) {
				moduleName = declaredModuleName;
			} else {
				moduleName = getModuleName(inputFile);
			}

			// analyze contained modules and types
			final boolean isClazzModule = isClazzModule(inputFile);
			final boolean isStandardModule = isStandardModule(inputFile);

			final ParserVisitor visitor = new VbTypeVisitorImpl(moduleName, isClazzModule, isStandardModule, program);

			LOG.info("Collecting types in file {}.", inputFile.getName());
			visitor.visit(ctx);
		}
	}

	private void registerApiEnumeration(final Program program, final ApiEnumeration apiEnumeration) {
		program.getTypeRegistry().registerType(apiEnumeration);
		program.getApiEnumerationRegistry().registerApiEnumeration(apiEnumeration);
	}

	protected void registerApiEnumerations(final Program program) {
		{
			final ApiEnumeration adodbCursorLocationEnum = new ApiEnumerationImpl("ADODB.CursorLocationEnum");
			registerApiEnumeration(program, adodbCursorLocationEnum);

			adodbCursorLocationEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adUseClient", adodbCursorLocationEnum));
		}

		{
			final ApiEnumeration adodbConnectOptionEnum = new ApiEnumerationImpl("ADODB.ConnectOptionEnum");
			registerApiEnumeration(program, adodbConnectOptionEnum);

			adodbConnectOptionEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adConnectUnspecified", adodbConnectOptionEnum));
			adodbConnectOptionEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adAsyncConnect", adodbConnectOptionEnum));
		}

		{
			final ApiEnumeration adodbCursorTypeEnum = new ApiEnumerationImpl("ADODB.CursorTypeEnum");
			registerApiEnumeration(program, adodbCursorTypeEnum);

			adodbCursorTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adOpenUnspecified", adodbCursorTypeEnum));
			adodbCursorTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adOpenForwardOnly", adodbCursorTypeEnum));
			adodbCursorTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adOpenKeyset", adodbCursorTypeEnum));
			adodbCursorTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adOpenDynamic", adodbCursorTypeEnum));
			adodbCursorTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adOpenStatic", adodbCursorTypeEnum));
		}

		{
			final ApiEnumeration adodbEventStatusEnum = new ApiEnumerationImpl("ADODB.EventStatusEnum");
			registerApiEnumeration(program, adodbEventStatusEnum);

			adodbEventStatusEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStatusCancel", adodbEventStatusEnum));
			adodbEventStatusEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStatusCantDeny", adodbEventStatusEnum));
			adodbEventStatusEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStatusErrorsOccurred", adodbEventStatusEnum));
			adodbEventStatusEnum
					.registerApiEnumerationConstant(new ApiEnumerationConstantImpl("adStatusOK", adodbEventStatusEnum));
			adodbEventStatusEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStatusUnwantedEvent", adodbEventStatusEnum));
		}

		{
			final ApiEnumeration adodbFilterGroupEnum = new ApiEnumerationImpl("ADODB.FilterGroupEnum");
			registerApiEnumeration(program, adodbFilterGroupEnum);

			adodbFilterGroupEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adFilterNone", adodbFilterGroupEnum));
			adodbFilterGroupEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adFilterPendingRecords", adodbFilterGroupEnum));
			adodbFilterGroupEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adFilterAffectedRecords", adodbFilterGroupEnum));
			adodbFilterGroupEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adFilterFetchedRecords", adodbFilterGroupEnum));
			adodbFilterGroupEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adFilterConflictingRecords", adodbFilterGroupEnum));
		}

		{
			final ApiEnumeration adodbLockTypeEnum = new ApiEnumerationImpl("ADODB.LockTypeEnum");
			registerApiEnumeration(program, adodbLockTypeEnum);

			adodbLockTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adLockUnspecified", adodbLockTypeEnum));
			adodbLockTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adLockReadOnly", adodbLockTypeEnum));
			adodbLockTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adLockPessimistic", adodbLockTypeEnum));
			adodbLockTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adLockOptimistic", adodbLockTypeEnum));
			adodbLockTypeEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adLockBatchOptimistic", adodbLockTypeEnum));
		}

		{
			final ApiEnumeration adodbObjectStateEnum = new ApiEnumerationImpl("ADODB.ObjectStateEnum");
			registerApiEnumeration(program, adodbObjectStateEnum);

			adodbObjectStateEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStateClosed", adodbObjectStateEnum));
			adodbObjectStateEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStateOpen", adodbObjectStateEnum));
			adodbObjectStateEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStateConnecting", adodbObjectStateEnum));
			adodbObjectStateEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStateExecuting", adodbObjectStateEnum));
			adodbObjectStateEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adStateFetching", adodbObjectStateEnum));
		}

		{
			final ApiEnumeration adodbSearchDirectionEnum = new ApiEnumerationImpl("ADODB.SearchDirectionEnum");
			registerApiEnumeration(program, adodbSearchDirectionEnum);

			adodbSearchDirectionEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adSearchBackward", adodbSearchDirectionEnum));
			adodbSearchDirectionEnum.registerApiEnumerationConstant(
					new ApiEnumerationConstantImpl("adSearchForward", adodbSearchDirectionEnum));
		}
	}

	protected void registerApiModules(final Program program) {
		final TypeRegistry typeRegistry = program.getTypeRegistry();

		final ApiEnumeration objectStateEnum = program.getApiEnumerationRegistry()
				.getApiEnumeration("ADODB.ObjectStateEnum");
		final ApiModule adodbErrors;

		{
			adodbErrors = new ApiModuleImpl("ADODB.Errors", false);
			typeRegistry.registerType(adodbErrors);
		}

		{
			final ApiModule adodbConnection = new ApiModuleImpl("ADODB.Connection", false);
			typeRegistry.registerType(adodbConnection);

			final ApiProperty apiPropertyErrors = new ApiPropertyImpl("Errors", adodbErrors);
			adodbConnection.registerApiProperty(apiPropertyErrors);

			final ApiProperty apiPropertyState = new ApiPropertyImpl("State", objectStateEnum);
			adodbConnection.registerApiProperty(apiPropertyState);

			final ApiProcedure apiProcedureBeginTrans = new ApiProcedureImpl("BeginTrans", null);
			adodbConnection.registerApiProcedure(apiProcedureBeginTrans);
		}

		{
			final ApiModule adodbRecordSet = new ApiModuleImpl("ADODB.Recordset", true);
			typeRegistry.registerType(adodbRecordSet);

			adodbRecordSet.registerApiProperty(new ApiPropertyImpl("ActiveConnection", VbBaseType.STRING));

			final ApiProcedure apiProcedureBof = new ApiProcedureImpl("BOF", VbBaseType.BOOLEAN);
			adodbRecordSet.registerApiProcedure(apiProcedureBof);

			final ApiProcedure apiProcedureEof = new ApiProcedureImpl("EOF", VbBaseType.BOOLEAN);
			adodbRecordSet.registerApiProcedure(apiProcedureEof);

			final ApiProcedure apiProcedureFields = new ApiProcedureImpl("Fields", VbBaseType.COLLECTION);
			adodbRecordSet.registerApiProcedure(apiProcedureFields);

			final ApiProcedure apiProcedureRecordCount = new ApiProcedureImpl("RecordCount", VbBaseType.INTEGER);
			adodbRecordSet.registerApiProcedure(apiProcedureRecordCount);
		}
	}

	protected void registerApiProcedures(final Program program) {
		final ApiProcedureRegistry apiProcedureRegistry = program.getApiProcedureRegistry();

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Asc", VbBaseType.INTEGER));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CBool", VbBaseType.BOOLEAN));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CByte", VbBaseType.BYTE));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CDate", VbBaseType.DATE));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CDbl", VbBaseType.DOUBLE));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Chr", VbBaseType.STRING));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CInt", VbBaseType.INTEGER));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CLng", VbBaseType.LONG));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CreateObject", VbBaseType.VARIANT));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("CVDate", VbBaseType.DATE));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Format", VbBaseType.STRING));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("FreeFile", VbBaseType.INTEGER));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("InStr", VbBaseType.LONG));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Int", VbBaseType.INTEGER));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("InStrRev", VbBaseType.LONG));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("LCase", VbBaseType.STRING));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("LBound", VbBaseType.INTEGER));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Len", VbBaseType.LONG));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Left", VbBaseType.STRING));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Mid", VbBaseType.STRING));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Replace", VbBaseType.STRING));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Right", VbBaseType.STRING));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Round", VbBaseType.DOUBLE));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Space", VbBaseType.STRING));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("StrComp", VbBaseType.BOOLEAN));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("String", VbBaseType.STRING));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Time", VbBaseType.DATE));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("Trim", VbBaseType.STRING));

		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("UBound", VbBaseType.INTEGER));
		apiProcedureRegistry.registerApiProcedure(new ApiProcedureImpl("UCase", VbBaseType.STRING));
	}

	protected void registerApiProperties(final Program program) {
		program.getApiPropertyRegistry().registerApiProperty(new ApiPropertyImpl("AppMajor", VbBaseType.INTEGER));
	}

	protected void registerModelElements(final Program program) {
		registerVbBaseTypes(program);
		registerApiEnumerations(program);
		registerApiProperties(program);
		registerApiProcedures(program);
		registerApiModules(program);
	}

	protected void registerVbBaseTypes(final Program program) {
		for (final VbBaseType vbType : VbBaseType.values()) {
			program.getTypeRegistry().registerType(vbType);
		}
	}
}
