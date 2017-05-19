package io.proleap.vb6.asg.call.variable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class VariableCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/variable/VariableCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

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

		final ComplexType someComplexType = program.getTypeRegistry().getComplexType("SomeComplexType");

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
