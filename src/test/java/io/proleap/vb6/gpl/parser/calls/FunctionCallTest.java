package io.proleap.vb6.gpl.parser.calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Function;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;

public class FunctionCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/calls/FunctionCall.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("FunctionCall");
		final Function function1 = module.getFunction("Function1");

		assertNotNull(function1);
		assertFalse(function1.getFunctionCalls().isEmpty());
		assertEquals(3, function1.getFunctionCalls().size());

		final Function function2 = module.getFunction("Function2");

		assertNotNull(function2);
		assertFalse(function2.getFunctionCalls().isEmpty());
		assertEquals(2, function2.getFunctionCalls().size());
	}

}
