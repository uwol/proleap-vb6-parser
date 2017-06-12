package io.proleap.vb6.asg.call.variable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.type.ComplexType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ScalarTypeVariableCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/variable/ScalarTypeVariableCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ScalarTypeVariableCall");
		final ComplexType someComplexType = program.getTypeRegistry().getComplexType("SomeComplexType");

		final Variable typeElement = module.getVariable("typeElement");

		assertNotNull(someComplexType);
		assertEquals(someComplexType, typeElement.getType());
		assertFalse(typeElement.isCollection());
	}
}
