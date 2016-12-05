package io.proleap.vb6.gpl.parser.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.statement.function.Function;
import io.proleap.vb6.parser.metamodel.statement.sub.Sub;

public class FunctionCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/call/FunctionCall.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("FunctionCall");
		final Function function1 = module.getFunction("Function1");

		assertNotNull(function1);
		assertFalse(function1.getFunctionCalls().isEmpty());
		assertEquals(3, function1.getFunctionCalls().size());

		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, function1.getType());

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
