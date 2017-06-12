package io.proleap.vb6.asg.call.variable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ArrayTypeVariableCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/variable/ArrayTypeVariableCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ArrayTypeVariableCall");
		final ComplexType someComplexType = program.getTypeRegistry().getComplexType("SomeComplexType");

		final Variable typeArray = module.getVariable("typeArray");

		assertNotNull(someComplexType);
		assertEquals(someComplexType, typeArray.getType());
		assertTrue(typeArray.isCollection());
	}
}
