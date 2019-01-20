/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CommonTokenStream;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeclareStmtContext;
import io.proleap.vb6.VisualBasic6Parser.DeftypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LetterrangeContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleConfigElementContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleHeaderContext;
import io.proleap.vb6.VisualBasic6Parser.OptionBaseStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OptionCompareStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OptionExplicitStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OptionPrivateModuleStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.asg.exception.VbParserException;
import io.proleap.vb6.asg.metamodel.Attribute;
import io.proleap.vb6.asg.metamodel.DefType;
import io.proleap.vb6.asg.metamodel.Literal;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.ModuleConfigElement;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.ProcedureDeclaration;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.ScopedElement;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.enumeration.impl.EnumerationImpl;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.function.impl.FunctionImpl;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.get.impl.PropertyGetImpl;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.let.impl.PropertyLetImpl;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.asg.metamodel.statement.property.set.impl.PropertySetImpl;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.statement.sub.impl.SubImpl;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.util.AsgStringUtils;

public abstract class ModuleImpl extends ScopeImpl implements Module {

	public final static String NEW_ENUM = "NewEnum";

	protected Map<String, Attribute> attributes = new HashMap<String, Attribute>();

	protected ModuleContext ctx;

	protected List<DefType> defTypes = new ArrayList<DefType>();

	protected Map<String, Enumeration> enumerations = new HashMap<String, Enumeration>();

	protected Map<String, Function> functions = new HashMap<String, Function>();

	protected boolean isCollection;

	protected List<String> lines;

	protected List<ModuleConfigElement> moduleConfigElements = new ArrayList<ModuleConfigElement>();

	protected final String name;

	protected Integer optionBase;

	protected OptionCompare optionCompare;

	protected Boolean optionExplicit;

	protected Boolean optionPrivateModule;

	protected List<Procedure> procedures = new ArrayList<Procedure>();

	protected final Program program;

	protected Map<String, PropertyGet> propertyGets = new HashMap<String, PropertyGet>();

	protected Map<String, PropertyLet> propertyLets = new HashMap<String, PropertyLet>();

	protected Map<String, PropertySet> propertySets = new HashMap<String, PropertySet>();

	protected Map<String, Sub> subs = new HashMap<String, Sub>();

	protected CommonTokenStream tokens;

	protected final Map<String, io.proleap.vb6.asg.metamodel.Type> types = new HashMap<String, io.proleap.vb6.asg.metamodel.Type>();

	protected Double version;

	public ModuleImpl(final String name, final Program program, final CommonTokenStream tokens,
			final ModuleContext ctx) {
		super(program, null, program, ctx);

		this.name = name;
		this.program = program;
		this.ctx = ctx;
		this.tokens = tokens;
		module = this;

		registerASGElement(this);
	}

	@Override
	public Attribute addAttribute(final AttributeStmtContext ctx) {
		Attribute result = (Attribute) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new AttributeImpl(name, type, this, this, ctx);

			registerScopedElement(result);
			attributes.put(getSymbol(name), result);

			final Literal literal = addLiteral(ctx.literal(0));
			result.setLiteral(literal);
		}

		return result;
	}

	@Override
	public ProcedureDeclaration addDeclaration(final DeclareStmtContext ctx) {
		ProcedureDeclaration result = (ProcedureDeclaration) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ProcedureDeclarationImpl(name, this, this, ctx);

			registerScopedElement(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}
		}

		return result;
	}

	@Override
	public DefType addDefType(final DeftypeStmtContext ctx) {
		DefType result = (DefType) getASGElement(ctx);

		if (result == null) {
			final VbBaseType vbType;

			if (ctx.DEFBOOL() != null) {
				vbType = VbBaseType.BOOLEAN;
			} else if (ctx.DEFBYTE() != null) {
				vbType = VbBaseType.BYTE;
			} else if (ctx.DEFCUR() != null) {
				vbType = VbBaseType.CURRENCY;
			} else if (ctx.DEFDATE() != null) {
				vbType = VbBaseType.DATE;
			} else if (ctx.DEFDBL() != null) {
				vbType = VbBaseType.DOUBLE;
			} else if (ctx.DEFDEC() != null) {
				vbType = VbBaseType.DOUBLE;
			} else if (ctx.DEFINT() != null) {
				vbType = VbBaseType.INTEGER;
			} else if (ctx.DEFLNG() != null) {
				vbType = VbBaseType.LONG;
			} else if (ctx.DEFOBJ() != null) {
				vbType = VbBaseType.VARIANT;
			} else if (ctx.DEFSNG() != null) {
				vbType = VbBaseType.SINGLE;
			} else if (ctx.DEFSTR() != null) {
				vbType = VbBaseType.STRING;
			} else if (ctx.DEFVAR() != null) {
				vbType = VbBaseType.VARIANT;
			} else {
				throw new VbParserException("unknown deftype " + ctx);
			}

			result = new DefTypeImpl(vbType);

			for (final LetterrangeContext letterRange : ctx.letterrange()) {
				final String lower = letterRange.certainIdentifier(0).getText();

				final String upper;

				if (letterRange.certainIdentifier().size() > 1) {
					upper = letterRange.certainIdentifier(1).getText();
				} else {
					upper = null;
				}

				result.addLetterRange(lower, upper);
			}

			defTypes.add(result);
		}

		return result;
	}

	@Override
	public Enumeration addEnumeration(final EnumerationStmtContext ctx) {
		Enumeration result = (Enumeration) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final VisibilityEnum visibility = determineVisibility(ctx.publicPrivateVisibility());
			result = new EnumerationImpl(name, visibility, this, ctx);

			registerStatement(result);
			enumerations.put(getSymbol(name), result);

			getProgram().getTypeRegistry().registerType(result);
			getProgram().getEnumerationRegistry().registerEnumeration(result);
		}

		return result;
	}

	@Override
	public Function addFunction(final FunctionStmtContext ctx) {
		Function result = (Function) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx.asTypeClause());
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());

			result = new FunctionImpl(name, visibility, type, this, ctx);

			registerStatement(result);
			functions.put(getSymbol(name), result);
			procedures.add(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}

			/*
			 * check, whether the return type is an array
			 */
			if (ctx.asTypeClause() != null && ctx.asTypeClause().type() != null) {
				final boolean isArray = ctx.asTypeClause().type().LPAREN() != null
						&& ctx.asTypeClause().type().RPAREN() != null;

				result.setDeclaredAsArray(isArray);
			}

			hasNewEnum(name);
		}

		return result;
	}

	@Override
	public ModuleConfigElement addModuleConfigElement(final ModuleConfigElementContext ctx) {
		ModuleConfigElement result = (ModuleConfigElement) getASGElement(ctx);

		if (result == null) {

			final String name = determineName(ctx);

			result = new ModuleConfigElementImpl(name, this, this, ctx);
			moduleConfigElements.add(result);

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public void addModuleHeader(final ModuleHeaderContext ctx) {
		final String versionString = ctx.DOUBLELITERAL().getText();
		version = AsgStringUtils.parseDouble(versionString);
	}

	@Override
	public void addOptionBase(final OptionBaseStmtContext ctx) {
		final String optionBaseString = ctx.INTEGERLITERAL().getText();
		optionBase = AsgStringUtils.parseInteger(optionBaseString);
	}

	@Override
	public void addOptionCompare(final OptionCompareStmtContext ctx) {
		final OptionCompare result;

		if (ctx.BINARY() != null) {
			result = OptionCompare.BINARY;
		} else if (ctx.TEXT() != null) {
			result = OptionCompare.TEXT;
		} else {
			result = null;
		}

		optionCompare = result;
	}

	@Override
	public void addOptionExplicit(final OptionExplicitStmtContext ctx) {
		optionExplicit = ctx.OPTION_EXPLICIT() != null;
	}

	@Override
	public void addOptionPrivateModule(final OptionPrivateModuleStmtContext ctx) {
		optionPrivateModule = ctx.OPTION_PRIVATE_MODULE() != null;
	}

	@Override
	public PropertyGet addPropertyGet(final PropertyGetStmtContext ctx) {
		PropertyGet result = (PropertyGet) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx.asTypeClause());
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());

			result = new PropertyGetImpl(name, visibility, type, this, ctx);

			registerStatement(result);
			propertyGets.put(getSymbol(name), result);
			procedures.add(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}

			/*
			 * check, whether the return type is an array
			 */
			if (ctx.asTypeClause() != null && ctx.asTypeClause().type() != null) {
				final boolean isArray = ctx.asTypeClause().type().LPAREN() != null
						&& ctx.asTypeClause().type().RPAREN() != null;

				result.setDeclaredAsArray(isArray);
			}

			hasNewEnum(name);
		}

		return result;
	}

	@Override
	public PropertyLet addPropertyLet(final PropertyLetStmtContext ctx) {
		PropertyLet result = (PropertyLet) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());

			result = new PropertyLetImpl(name, visibility, this, ctx);

			registerStatement(result);
			propertyLets.put(getSymbol(name), result);
			procedures.add(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}
		}

		return result;
	}

	@Override
	public PropertySet addPropertySet(final PropertySetStmtContext ctx) {
		PropertySet result = (PropertySet) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());

			result = new PropertySetImpl(name, visibility, this, ctx);

			registerStatement(result);
			propertySets.put(getSymbol(name), result);
			procedures.add(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}
		}

		return result;
	}

	@Override
	public Sub addSub(final SubStmtContext ctx) {
		Sub result = (Sub) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());

			result = new SubImpl(name, visibility, this, ctx);

			registerStatement(result);
			subs.put(getSymbol(name), result);
			procedures.add(result);

			if (ctx.argList() != null) {
				for (final ArgContext argCtx : ctx.argList().arg()) {
					result.addArg(argCtx);
				}
			}
		}

		return result;
	}

	@Override
	public io.proleap.vb6.asg.metamodel.Type addType(final TypeStmtContext ctx) {
		io.proleap.vb6.asg.metamodel.Type result = (io.proleap.vb6.asg.metamodel.Type) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final VisibilityEnum visibility = determineVisibility(ctx.visibility());

			result = new TypeImpl(name, visibility, this, ctx);

			for (final TypeStmt_ElementContext typeElementCtx : ctx.typeStmt_Element()) {
				final TypeElement typeElement = addTypeElement(typeElementCtx);
				result.addTypeElement(typeElement);
			}

			registerScopedElement(result);
			types.put(getSymbol(name), result);
			program.getTypeRegistry().registerType(result);
		}

		return result;
	}

	@Override
	public TypeElement addTypeElement(final TypeStmt_ElementContext ctx) {
		TypeElement result = (TypeElement) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Type type = determineType(ctx);

			result = new TypeElementImpl(name, type, module, ctx);

			final boolean isArray = ctx.LPAREN() != null && ctx.RPAREN() != null;
			final boolean isStaticArray = isArray && ctx.subscripts() != null;

			result.setDeclaredAsArray(isArray);
			result.setDeclaredAsStaticArray(isStaticArray);

			registerScopedElement(result);
		}

		return result;
	}

	@Override
	public ModuleContext getCtx() {
		return ctx;
	}

	@Override
	public List<DefType> getDefTypes() {
		return defTypes;
	}

	@Override
	public Enumeration getEnumeration(final String name) {
		return enumerations.get(getSymbol(name));
	}

	@Override
	public EnumerationConstant getEnumerationConstant(final String name) {
		for (final Enumeration enumeration : enumerations.values()) {
			if (enumeration.getEnumerationConstant(name) != null) {
				return enumeration.getEnumerationConstant(name);
			}
		}

		return null;
	}

	@Override
	public Map<String, Enumeration> getEnumerations() {
		return enumerations;
	}

	@Override
	public Function getFunction(final String name) {
		return functions.get(getSymbol(name));
	}

	@Override
	public List<Function> getFunctions() {
		return new ArrayList<>(functions.values());
	}

	@Override
	public List<String> getLines() {
		return lines;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Procedure> getProcedures() {
		return procedures;
	}

	@Override
	public Program getProgram() {
		return program;
	}

	@Override
	public PropertyGet getPropertyGet(final String name) {
		return propertyGets.get(getSymbol(name));
	}

	@Override
	public List<PropertyGet> getPropertyGets() {
		return new ArrayList<>(propertyGets.values());
	}

	@Override
	public PropertyLet getPropertyLet(final String name) {
		return propertyLets.get(getSymbol(name));
	}

	@Override
	public List<PropertyLet> getPropertyLets() {
		return new ArrayList<>(propertyLets.values());
	}

	@Override
	public PropertySet getPropertySet(final String name) {
		return propertySets.get(getSymbol(name));
	}

	@Override
	public List<PropertySet> getPropertySets() {
		return new ArrayList<>(propertySets.values());
	}

	@Override
	public List<ScopedElement> getScopedElementsInScope(final String name) {
		final List<ScopedElement> result;

		final EnumerationConstant enumerationConstant = getEnumerationConstant(name);

		if (enumerationConstant != null) {
			result = new ArrayList<>();
			result.add(enumerationConstant);
		} else {
			result = super.getScopedElementsInScope(name);
		}

		return result;
	}

	@Override
	public Sub getSub(final String name) {
		return subs.get(getSymbol(name));
	}

	@Override
	public List<Sub> getSubs() {
		return new ArrayList<Sub>(subs.values());
	}

	@Override
	public CommonTokenStream getTokens() {
		return tokens;
	}

	@Override
	public io.proleap.vb6.asg.metamodel.Type getType(final String name) {
		return types.get(getSymbol(name));
	}

	@Override
	public Double getVersion() {
		return version;
	}

	private void hasNewEnum(final String name) {
		if (NEW_ENUM.equals(name)) {
			isCollection = true;
		}
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}

	@Override
	public boolean isModuleWithMetaData() {
		return version != null || optionExplicit != null || optionPrivateModule != null || optionBase != null
				|| optionCompare != null;
	}

	@Override
	public void setLines(final List<String> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return name;
	}

}
