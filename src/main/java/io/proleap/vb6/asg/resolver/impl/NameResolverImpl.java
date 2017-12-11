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
import io.proleap.vb6.VisualBasic6Parser.AmbiguousIdentifierContext;
import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgDefaultValueContext;
import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.CertainIdentifierContext;
import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeclareStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DictionaryCallStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_ProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_B_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_B_ProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_MemberCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_MembersCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LineLabelContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleConfigElementContext;
import io.proleap.vb6.VisualBasic6Parser.OnErrorStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ResumeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VsICSContext;
import io.proleap.vb6.asg.resolver.NameResolver;

public class NameResolverImpl implements NameResolver {

	public String determineName(final AmbiguousIdentifierContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ArgCallContext ctx) {
		final String result = determineName(ctx.valueStmt());
		return result;
	}

	public String determineName(final ArgDefaultValueContext ctx) {
		final String result = determineName(ctx.valueStmt());
		return result;
	}

	public String determineName(final AttributeStmtContext ctx) {
		final String result = determineName(ctx.implicitCallStmt_InStmt());
		return result;
	}

	public String determineName(final CertainIdentifierContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ConstSubStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final DictionaryCallStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final ECS_MemberProcedureCallContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final ECS_ProcedureCallContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final ForEachStmtContext ctx) {
		final String elementVariableName = determineName(ctx.ambiguousIdentifier(0));
		return elementVariableName;
	}

	public String determineName(final ICS_B_MemberProcedureCallContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final ICS_B_ProcedureCallContext ctx) {
		final String result = determineName(ctx.certainIdentifier());
		return result;
	}

	public String determineName(final ICS_S_MemberCallContext ctx) {
		final String result;

		if (ctx.iCS_S_VariableOrProcedureCall() != null) {
			result = determineName(ctx.iCS_S_VariableOrProcedureCall());
		} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
			result = determineName(ctx.iCS_S_ProcedureOrArrayCall());
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final ICS_S_MembersCallContext ctx) {
		final ICS_S_MemberCallContext lastMemberCall = ctx.iCS_S_MemberCall().get(ctx.iCS_S_MemberCall().size() - 1);
		final String result = determineName(lastMemberCall);
		return result;
	}

	public String determineName(final ICS_S_ProcedureOrArrayCallContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final ICS_S_VariableOrProcedureCallContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final ImplicitCallStmt_InStmtContext ctx) {
		final String result;

		if (ctx.iCS_S_VariableOrProcedureCall() != null) {
			result = determineName(ctx.iCS_S_VariableOrProcedureCall());
		} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
			result = determineName(ctx.iCS_S_ProcedureOrArrayCall());
		} else if (ctx.iCS_S_MembersCall() != null) {
			result = determineName(ctx.iCS_S_MembersCall());
		} else if (ctx.iCS_S_DictionaryCall() != null) {
			result = determineName(ctx.iCS_S_DictionaryCall());
		} else {
			result = null;
		}

		return result;
	}

	@Override
	public String determineName(final ParseTree ctx) {
		final String result;

		if (ctx instanceof AmbiguousIdentifierContext) {
			result = determineName((AmbiguousIdentifierContext) ctx);
		} else if (ctx instanceof ArgContext) {
			result = determineName((ArgContext) ctx);
		} else if (ctx instanceof ArgCallContext) {
			result = determineName((ArgCallContext) ctx);
		} else if (ctx instanceof ArgDefaultValueContext) {
			result = determineName((ArgDefaultValueContext) ctx);
		} else if (ctx instanceof AttributeStmtContext) {
			result = determineName((AttributeStmtContext) ctx);
		} else if (ctx instanceof CertainIdentifierContext) {
			result = determineName((CertainIdentifierContext) ctx);
		} else if (ctx instanceof ConstSubStmtContext) {
			result = determineName((ConstSubStmtContext) ctx);
		} else if (ctx instanceof DeclareStmtContext) {
			result = determineName((DeclareStmtContext) ctx);
		} else if (ctx instanceof DictionaryCallStmtContext) {
			result = determineName((DictionaryCallStmtContext) ctx);
		} else if (ctx instanceof EnumerationStmt_ConstantContext) {
			result = determineName((EnumerationStmt_ConstantContext) ctx);
		} else if (ctx instanceof EnumerationStmtContext) {
			result = determineName((EnumerationStmtContext) ctx);
		} else if (ctx instanceof ECS_MemberProcedureCallContext) {
			result = determineName((ECS_MemberProcedureCallContext) ctx);
		} else if (ctx instanceof ECS_ProcedureCallContext) {
			result = determineName((ECS_ProcedureCallContext) ctx);
		} else if (ctx instanceof ForEachStmtContext) {
			result = determineName((ForEachStmtContext) ctx);
		} else if (ctx instanceof FunctionStmtContext) {
			result = determineName((FunctionStmtContext) ctx);
		} else if (ctx instanceof ICS_B_ProcedureCallContext) {
			result = determineName((ICS_B_ProcedureCallContext) ctx);
		} else if (ctx instanceof ICS_B_MemberProcedureCallContext) {
			result = determineName((ICS_B_MemberProcedureCallContext) ctx);
		} else if (ctx instanceof ICS_S_ProcedureOrArrayCallContext) {
			result = determineName((ICS_S_ProcedureOrArrayCallContext) ctx);
		} else if (ctx instanceof ICS_S_MembersCallContext) {
			result = determineName((ICS_S_MembersCallContext) ctx);
		} else if (ctx instanceof ICS_S_VariableOrProcedureCallContext) {
			result = determineName((ICS_S_VariableOrProcedureCallContext) ctx);
		} else if (ctx instanceof ImplicitCallStmt_InStmtContext) {
			result = determineName((ImplicitCallStmt_InStmtContext) ctx);
		} else if (ctx instanceof LineLabelContext) {
			result = determineName((LineLabelContext) ctx);
		} else if (ctx instanceof ModuleConfigElementContext) {
			result = determineName((ModuleConfigElementContext) ctx);
		} else if (ctx instanceof OnErrorStmtContext) {
			result = determineName((OnErrorStmtContext) ctx);
		} else if (ctx instanceof PropertyGetStmtContext) {
			result = determineName((PropertyGetStmtContext) ctx);
		} else if (ctx instanceof PropertyLetStmtContext) {
			result = determineName((PropertyLetStmtContext) ctx);
		} else if (ctx instanceof PropertySetStmtContext) {
			result = determineName((PropertySetStmtContext) ctx);
		} else if (ctx instanceof RedimSubStmtContext) {
			result = determineName((RedimSubStmtContext) ctx);
		} else if (ctx instanceof ResumeStmtContext) {
			result = determineName((ResumeStmtContext) ctx);
		} else if (ctx instanceof SubStmtContext) {
			result = determineName((SubStmtContext) ctx);
		} else if (ctx instanceof TypeStmtContext) {
			result = determineName((TypeStmtContext) ctx);
		} else if (ctx instanceof TypeStmt_ElementContext) {
			result = determineName((TypeStmt_ElementContext) ctx);
		} else if (ctx instanceof ValueStmtContext) {
			result = determineName((ValueStmtContext) ctx);
		} else if (ctx instanceof VariableSubStmtContext) {
			result = determineName((VariableSubStmtContext) ctx);
		} else if (ctx instanceof VsICSContext) {
			result = determineName((VsICSContext) ctx);
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final RedimSubStmtContext ctx) {
		final String result = determineName(ctx.implicitCallStmt_InStmt());
		return result;
	}

	public String determineName(final ValueStmtContext ctx) {
		final String result;

		if (ctx == null || ctx.getChildCount() == 0) {
			result = null;
		} else if (ctx instanceof VsICSContext) {
			result = determineName((VsICSContext) ctx);
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final VariableSubStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.ArgContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.DeclareStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.EnumerationStmt_ConstantContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.EnumerationStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.FunctionStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.LineLabelContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.ModuleConfigElementContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.OnErrorStmtContext ctx) {
		final String result = determineName(ctx.valueStmt());
		return result;
	}

	public String determineName(final VisualBasic6Parser.PropertyGetStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.PropertyLetStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.PropertySetStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.ResumeStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.SubStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.TypeStmt_ElementContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VisualBasic6Parser.TypeStmtContext ctx) {
		final String result = determineName(ctx.ambiguousIdentifier());
		return result;
	}

	public String determineName(final VsICSContext ctx) {
		final String result = determineName(ctx.implicitCallStmt_InStmt());
		return result;
	}

}
