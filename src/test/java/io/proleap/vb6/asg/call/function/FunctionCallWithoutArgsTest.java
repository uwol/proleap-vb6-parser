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

public class FunctionCallWithoutArgsTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/function/FunctionCallWithoutArgs.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("FunctionCallWithoutArgs");
		final Function function1 = module.getFunction("Function1");

		assertNotNull(function1);
		assertFalse(function1.getFunctionCalls().isEmpty());
		assertEquals(3, function1.getFunctionCalls().size());

		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, function1.getType());

		final Sub subTestFunctionCalls = module.getSub("TestFunctionCalls");
		final Variable variableI = subTestFunctionCalls.getVariable("I");

		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, variableI.getType());
	}

}
