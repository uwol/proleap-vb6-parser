package io.proleap.vb6.gpl.parser.calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.type.ComplexType;

public class VariableCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/calls/VariableCall.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("VariableCall");
		final Variable variableI = module.getVariable("I");

		assertNotNull(variableI);
		assertFalse(variableI.getVariableCalls().isEmpty());
		assertEquals(1, variableI.getVariableCalls().size());
		// type has been defined by declaration
		assertEquals(VbBaseType.INTEGER, variableI.getType());

		final Variable variableJ = module.getVariable("J");

		assertNotNull(variableJ);
		assertFalse(variableJ.getVariableCalls().isEmpty());
		assertEquals(1, variableJ.getVariableCalls().size());
		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, variableJ.getType());

		final ComplexType someComplexType = VbParserContext.getInstance().getTypeRegistry()
				.getComplexType("SomeComplexType");

		final Variable typeElement = module.getVariable("typeElement");

		assertNotNull(someComplexType);
		assertEquals(someComplexType, typeElement.getType());
		assertFalse(typeElement.isCollection());

		final Variable typeArray = module.getVariable("typeArray");

		assertNotNull(someComplexType);
		assertEquals(someComplexType, typeArray.getType());
		assertTrue(typeArray.isCollection());
	}

}
