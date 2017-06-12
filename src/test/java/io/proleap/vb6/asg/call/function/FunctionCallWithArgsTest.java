package io.proleap.vb6.asg.call.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class FunctionCallWithArgsTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/function/FunctionCallWithArgs.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("FunctionCallWithArgs");
		final Function function2 = module.getFunction("Function2");

		assertNotNull(function2);
		assertFalse(function2.getFunctionCalls().isEmpty());
		assertEquals(2, function2.getFunctionCalls().size());

		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, function2.getType());

		final Sub subTestFunctionCalls = module.getSub("TestFunctionCalls");
		final Variable variableI = subTestFunctionCalls.getVariable("I");

		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, variableI.getType());
	}

}
