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
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ArrayVariableCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/variable/ArrayVariableCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ArrayVariableCall");
		final Variable myArray = module.getVariable("MyArray");

		assertNotNull(myArray);
		assertTrue(myArray.isCollection());
		assertFalse(myArray.getVariableCalls().isEmpty());
		assertEquals(3, myArray.getVariableCalls().size());

		{
			final VariableCall variableCall = myArray.getVariableCalls().get(0);
			assertEquals(CallType.VARIABLE_CALL, variableCall.getCallType());
		}

		{
			final VariableCall variableCall = myArray.getVariableCalls().get(1);
			assertEquals(CallType.ARRAY_ELEMENT_CALL, variableCall.getCallType());
		}

		{
			final VariableCall variableCall = myArray.getVariableCalls().get(2);
			assertEquals(CallType.ARRAY_ELEMENT_CALL, variableCall.getCallType());
		}
	}
}