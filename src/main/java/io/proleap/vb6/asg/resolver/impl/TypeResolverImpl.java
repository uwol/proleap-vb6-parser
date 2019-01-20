/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.resolver.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgDefaultValueContext;
import io.proleap.vb6.VisualBasic6Parser.AsTypeClauseContext;
import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.VisualBasic6Parser.TypeHintContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VsEqvContext;
import io.proleap.vb6.VisualBasic6Parser.VsGeqContext;
import io.proleap.vb6.VisualBasic6Parser.VsGtContext;
import io.proleap.vb6.VisualBasic6Parser.VsICSContext;
import io.proleap.vb6.VisualBasic6Parser.VsIsContext;
import io.proleap.vb6.VisualBasic6Parser.VsLeqContext;
import io.proleap.vb6.VisualBasic6Parser.VsLikeContext;
import io.proleap.vb6.VisualBasic6Parser.VsLiteralContext;
import io.proleap.vb6.VisualBasic6Parser.VsLtContext;
import io.proleap.vb6.VisualBasic6Parser.VsNeqContext;
import io.proleap.vb6.VisualBasic6Parser.VsNewContext;
import io.proleap.vb6.VisualBasic6Parser.VsNotContext;
import io.proleap.vb6.VisualBasic6Parser.VsOrContext;
import io.proleap.vb6.VisualBasic6Parser.VsXorContext;
import io.proleap.vb6.asg.exception.VbParserException;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.resolver.TypeResolver;
import io.proleap.vb6.asg.util.AsgStringUtils;

/**
 * Resolves and returns types of AST elements on a syntactic declarative level
 * (e. g. AS Integer). In contrast, semantic type analysis by value assignments
 * including transitive type relation analysis is conducted on the ASG.
 */
public class TypeResolverImpl implements TypeResolver {

	public Type determineType(final ImplicitCallStmt_InStmtContext ctx, final Program program) {
		final Type result;

		if (ctx.iCS_S_VariableOrProcedureCall() != null) {
			result = determineType(ctx.iCS_S_VariableOrProcedureCall(), program);
		} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
			result = determineType(ctx.iCS_S_ProcedureOrArrayCall(), program);
		} else {
			result = null;
		}

		return result;
	}

	public Type determineType(final LiteralContext literal, final Program program) {
		final Type result;

		if (literal == null) {
			result = null;
		} else if (literal.COLORLITERAL() != null) {
			result = VbBaseType.COLOR;
		} else if (literal.DATELITERAL() != null) {
			result = VbBaseType.DATE;
		} else if (literal.DOUBLELITERAL() != null) {
			result = VbBaseType.DOUBLE;
		} else if (literal.FILENUMBER() != null) {
			result = VbBaseType.FILENUMBER;
		} else if (literal.INTEGERLITERAL() != null) {
			result = VbBaseType.INTEGER;
		} else if (literal.STRINGLITERAL() != null) {
			result = VbBaseType.STRING;
		} else if (literal.TRUE() != null) {
			result = VbBaseType.BOOLEAN;
		} else if (literal.FALSE() != null) {
			result = VbBaseType.BOOLEAN;
		} else if (literal.NULL() != null) {
			result = null;
		} else if (literal.NOTHING() != null) {
			result = null;
		} else {
			throw new VbParserException("unknown literal " + literal);
		}

		return result;
	}

	@Override
	public Type determineType(final ParseTree ctx, final Program program) {
		final Type result;

		if (ctx instanceof ArgContext) {
			result = determineType((ArgContext) ctx, program);
		} else if (ctx instanceof ArgCallContext) {
			result = determineType((ArgCallContext) ctx, program);
		} else if (ctx instanceof ArgDefaultValueContext) {
			result = determineType((ArgDefaultValueContext) ctx, program);
		} else if (ctx instanceof AsTypeClauseContext) {
			result = determineType((AsTypeClauseContext) ctx, program);
		} else if (ctx instanceof AttributeStmtContext) {
			result = determineType((AttributeStmtContext) ctx, program);
		} else if (ctx instanceof ConstSubStmtContext) {
			result = determineType((ConstSubStmtContext) ctx, program);
		} else if (ctx instanceof ImplicitCallStmt_InStmtContext) {
			result = determineType((ImplicitCallStmt_InStmtContext) ctx, program);
		} else if (ctx instanceof LiteralContext) {
			result = determineType((LiteralContext) ctx, program);
		} else if (ctx instanceof TypeHintContext) {
			result = determineType((TypeHintContext) ctx, program);
		} else if (ctx instanceof TypeStmt_ElementContext) {
			result = determineType((TypeStmt_ElementContext) ctx, program);
		} else if (ctx instanceof VariableSubStmtContext) {
			result = determineType((VariableSubStmtContext) ctx, program);
		}
		// specific value statements
		else if (ctx instanceof ValueStmtContext) {
			result = determineType((ValueStmtContext) ctx, program);
		} else if (ctx instanceof VsICSContext) {
			result = determineType((VsICSContext) ctx, program);
		} else if (ctx instanceof VsLiteralContext) {
			result = determineType((VsLiteralContext) ctx, program);
		} else if (ctx instanceof VsLtContext) {
			result = determineType((VsLtContext) ctx, program);
		} else if (ctx instanceof VsLeqContext) {
			result = determineType((VsLeqContext) ctx, program);
		} else if (ctx instanceof VsGtContext) {
			result = determineType((VsGtContext) ctx, program);
		} else if (ctx instanceof VsGeqContext) {
			result = determineType((VsGeqContext) ctx, program);
		} else if (ctx instanceof VsNeqContext) {
			result = determineType((VsNeqContext) ctx, program);
		} else if (ctx instanceof VsNewContext) {
			result = determineType((VsNewContext) ctx, program);
		} else if (ctx instanceof VsEqvContext) {
			result = determineType((VsEqvContext) ctx, program);
		} else if (ctx instanceof VsIsContext) {
			result = determineType((VsIsContext) ctx, program);
		} else if (ctx instanceof VsLikeContext) {
			result = determineType((VsLikeContext) ctx, program);
		} else if (ctx instanceof VsNotContext) {
			result = determineType((VsNotContext) ctx, program);
		} else if (ctx instanceof VsOrContext) {
			result = determineType((VsOrContext) ctx, program);
		} else if (ctx instanceof VsXorContext) {
			result = determineType((VsXorContext) ctx, program);
		} else {
			result = null;
		}

		return result;
	}

	public Type determineType(final TypeHintContext ctx, final Program program) {
		final Type result;

		if ("&".equals(ctx.getText())) {
			result = VbBaseType.LONG;
		} else if ("%".equals(ctx.getText())) {
			result = VbBaseType.INTEGER;
		} else if ("#".equals(ctx.getText())) {
			result = VbBaseType.DOUBLE;
		} else if ("!".equals(ctx.getText())) {
			result = VbBaseType.SINGLE;
		} else if ("$".equals(ctx.getText())) {
			result = VbBaseType.STRING;
		} else if ("@".equals(ctx.getText())) {
			result = null; // VbBaseType.DECIMAL
		} else {
			result = null;
		}

		return result;
	}

	public Type determineType(final ValueStmtContext valueStmt, final Program program) {
		final Type result;

		if (valueStmt == null) {
			result = null;
		} else if (valueStmt instanceof VsICSContext) {
			result = determineType((VsICSContext) valueStmt, program);
		} else if (valueStmt instanceof VsLiteralContext) {
			result = determineType((VsLiteralContext) valueStmt, program);
		} else if (valueStmt instanceof VsLtContext) {
			result = determineType((VsLtContext) valueStmt, program);
		} else if (valueStmt instanceof VsLeqContext) {
			result = determineType((VsLeqContext) valueStmt, program);
		} else if (valueStmt instanceof VsGtContext) {
			result = determineType((VsGtContext) valueStmt, program);
		} else if (valueStmt instanceof VsGeqContext) {
			result = determineType((VsGeqContext) valueStmt, program);
		} else if (valueStmt instanceof VsNeqContext) {
			result = determineType((VsNeqContext) valueStmt, program);
		} else if (valueStmt instanceof VsEqvContext) {
			result = determineType((VsEqvContext) valueStmt, program);
		} else if (valueStmt instanceof VsIsContext) {
			result = determineType((VsIsContext) valueStmt, program);
		} else if (valueStmt instanceof VsLikeContext) {
			result = determineType((VsLikeContext) valueStmt, program);
		} else if (valueStmt instanceof VsNotContext) {
			result = determineType((VsNotContext) valueStmt, program);
		} else if (valueStmt instanceof VsOrContext) {
			result = determineType((VsOrContext) valueStmt, program);
		} else if (valueStmt instanceof VsXorContext) {
			result = determineType((VsXorContext) valueStmt, program);
		} else if (valueStmt instanceof VsNewContext) {
			result = determineType((VsNewContext) valueStmt, program);
		} else {
			result = determineTypeFromText(valueStmt.getText());
		}

		return result;
	}

	public Type determineType(final VisualBasic6Parser.ArgCallContext ctx, final Program program) {
		return determineType(ctx.valueStmt(), program);
	}

	public Type determineType(final VisualBasic6Parser.ArgContext ctx, final Program program) {
		return determineType(ctx.asTypeClause(), program);
	}

	public Type determineType(final VisualBasic6Parser.ArgDefaultValueContext ctx, final Program program) {
		Type result;

		if (ctx.valueStmt() != null) {
			result = determineType(ctx.valueStmt(), program);
		} else {
			result = null;
		}

		return result;
	}

	public Type determineType(final VisualBasic6Parser.AsTypeClauseContext ctx, final Program program) {
		final Type result;

		if (ctx == null) {
			result = null;
		} else if (ctx.type() == null) {
			result = null;
		} else if (ctx.type().baseType() != null) {
			final String typeName = ctx.type().baseType().getText();
			result = program.getTypeRegistry().getType(typeName);
		} else if (ctx.type().complexType() != null) {
			final String typeName = ctx.type().complexType().getText();
			result = program.getTypeRegistry().getType(typeName);
		} else {
			result = null;
		}

		return result;
	}

	public Type determineType(final VisualBasic6Parser.AttributeStmtContext ctx, final Program program) {
		final Type result = determineType(ctx.literal(0), program);
		return result;
	}

	public Type determineType(final VisualBasic6Parser.ConstSubStmtContext ctx, final Program program) {
		final Type asTypeType = determineType(ctx.asTypeClause(), program);
		final Type valueType = determineType(ctx.valueStmt(), program);

		final Type result;

		if (asTypeType != null) {
			result = asTypeType;
		} else {
			result = valueType;
		}

		return result;
	}

	public Type determineType(final VisualBasic6Parser.TypeStmt_ElementContext ctx, final Program program) {
		final Type result = determineType(ctx.asTypeClause(), program);
		return result;
	}

	public Type determineType(final VisualBasic6Parser.VariableSubStmtContext ctx, final Program program) {

		final Type result;

		if (ctx.asTypeClause() != null) {
			result = determineType(ctx.asTypeClause(), program);
		} else if (ctx.typeHint() != null) {
			result = determineType(ctx.typeHint(), program);
		} else {
			result = null;
		}

		return result;
	}

	public Type determineType(final VisualBasic6Parser.VsEqvContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsGeqContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsGtContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsIsContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsLeqContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsLikeContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsLiteralContext ctx, final Program program) {
		return determineType(ctx.literal(), program);
	}

	public Type determineType(final VisualBasic6Parser.VsLtContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsNeqContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsNewContext ctx, final Program program) {
		final Type type;

		if (ctx != null) {
			final String typeName = ctx.valueStmt().getText();
			type = program.getTypeRegistry().getType(typeName);
		} else {
			type = null;
		}

		return type;
	}

	public Type determineType(final VisualBasic6Parser.VsNotContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsOrContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VisualBasic6Parser.VsXorContext ctx, final Program program) {
		return VbBaseType.BOOLEAN;
	}

	public Type determineType(final VsICSContext ctx, final Program program) {
		final ImplicitCallStmt_InStmtContext implicitCallStmt_InStmt = ctx.implicitCallStmt_InStmt();
		return determineType(implicitCallStmt_InStmt, program);
	}

	protected Type determineTypeFromText(final String value) {
		final Type result;

		if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
			result = VbBaseType.BOOLEAN;
		} else if (AsgStringUtils.isInteger(value)) {
			result = VbBaseType.LONG;
		} else if (AsgStringUtils.isDouble(value)) {
			result = VbBaseType.DOUBLE;
		} else if (value.length() > 1 && value.startsWith("\"") && value.endsWith("\"")) {
			result = VbBaseType.STRING;
		} else {
			result = null;
		}

		return result;
	}

}
