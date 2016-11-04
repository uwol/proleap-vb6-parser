/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.applicationcontext;

import io.proleap.vb6.parser.antlr.ASTTraverser;
import io.proleap.vb6.parser.antlr.NameResolver;
import io.proleap.vb6.parser.antlr.TypeResolver;
import io.proleap.vb6.parser.inference.TypeAssignmentInference;
import io.proleap.vb6.parser.inference.TypeInference;
import io.proleap.vb6.parser.registry.SemanticGraphElementRegistry;
import io.proleap.vb6.parser.registry.TypeNameSanitizer;
import io.proleap.vb6.parser.registry.TypeRegistry;
import io.proleap.vb6.parser.registry.api.ApiEnumerationRegistry;
import io.proleap.vb6.parser.registry.api.ApiProcedureRegistry;
import io.proleap.vb6.parser.registry.api.ApiPropertyRegistry;
import io.proleap.vb6.parser.runner.VbParserRunner;

public class VbParserContext {

	protected static VbParserContext instance;

	public static VbParserContext getInstance() {
		if (instance == null) {
			instance = new VbParserContext();
		}

		return instance;
	}

	protected ApiEnumerationRegistry apiEnumerationRegistry;

	protected ApiProcedureRegistry apiProcedureRegistry;

	protected ApiPropertyRegistry apiPropertyRegistry;

	protected ASTTraverser astTraverser;

	protected NameResolver nameResolver;

	protected VbParserRunner parserRunner;

	protected SemanticGraphElementRegistry semanticGraphElementRegistry;

	protected TypeAssignmentInference typeAssignmentInference;

	protected TypeInference typeInference;

	protected TypeNameSanitizer typeNameSanitizer;

	protected TypeRegistry typeRegistry;

	protected TypeResolver typeResolver;

	private VbParserContext() {
		super();
	}

	public ApiEnumerationRegistry getApiEnumerationRegistry() {
		return apiEnumerationRegistry;
	}

	public ApiProcedureRegistry getApiProcedureRegistry() {
		return apiProcedureRegistry;
	}

	public ApiPropertyRegistry getApiPropertyRegistry() {
		return apiPropertyRegistry;
	}

	public ASTTraverser getAstTraverser() {
		return astTraverser;
	}

	public NameResolver getNameResolver() {
		return nameResolver;
	}

	public VbParserRunner getParserRunner() {
		return parserRunner;
	}

	public SemanticGraphElementRegistry getSemanticGraphElementRegistry() {
		return semanticGraphElementRegistry;
	}

	public TypeAssignmentInference getTypeAssignmentInference() {
		return typeAssignmentInference;
	}

	public TypeInference getTypeInference() {
		return typeInference;
	}

	public TypeNameSanitizer getTypeNameSanitizer() {
		return typeNameSanitizer;
	}

	public TypeRegistry getTypeRegistry() {
		return typeRegistry;
	}

	public TypeResolver getTypeResolver() {
		return typeResolver;
	}

	public void reset() {
		instance = null;
	}

	public void setApiEnumerationRegistry(final ApiEnumerationRegistry apiEnumerationRegistry) {
		this.apiEnumerationRegistry = apiEnumerationRegistry;
	}

	public void setApiProcedureRegistry(final ApiProcedureRegistry apiProcedureRegistry) {
		this.apiProcedureRegistry = apiProcedureRegistry;
	}

	public void setApiPropertyRegistry(final ApiPropertyRegistry apiPropertyRegistry) {
		this.apiPropertyRegistry = apiPropertyRegistry;
	}

	public void setAstTraverser(final ASTTraverser astTraverser) {
		this.astTraverser = astTraverser;
	}

	public void setNameResolver(final NameResolver nameResolver) {
		this.nameResolver = nameResolver;
	}

	public void setParserRunner(final VbParserRunner parserRunner) {
		this.parserRunner = parserRunner;
	}

	public void setSemanticGraphElementRegistry(final SemanticGraphElementRegistry semanticGraphElementRegistry) {
		this.semanticGraphElementRegistry = semanticGraphElementRegistry;
	}

	public void setTypeAssignmentInference(final TypeAssignmentInference typeAssignmentInference) {
		this.typeAssignmentInference = typeAssignmentInference;
	}

	public void setTypeInference(final TypeInference typeInference) {
		this.typeInference = typeInference;
	}

	public void setTypeNameSanitizer(final TypeNameSanitizer typeNameSanitizer) {
		this.typeNameSanitizer = typeNameSanitizer;
	}

	public void setTypeRegistry(final TypeRegistry typeRegistry) {
		this.typeRegistry = typeRegistry;
	}

	public void setTypeResolver(final TypeResolver typeResolver) {
		this.typeResolver = typeResolver;
	}

}
