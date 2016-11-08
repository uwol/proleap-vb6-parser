package io.proleap.vb6.gpl.parser.calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Function;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.Sub;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbBaseType;

public class ArgCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/calls/ArgCall.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ArgCall");
		final Function someFunction = module.getFunction("SomeFunction");

		assertNotNull(someFunction);
		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, someFunction.getType());

		{
			assertFalse(someFunction.getArgsList().isEmpty());
			assertEquals(2, someFunction.getArgsList().size());

			final Arg argI = someFunction.getArgs().get("I");
			// type has been inferred by value assignment
			assertEquals(VbBaseType.INTEGER, argI.getType());
			assertEquals(1, argI.getArgCalls().size());

			final Arg argJ = someFunction.getArgs().get("J");
			// type has been inferred by value assignment
			assertEquals(VbBaseType.INTEGER, argJ.getType());
			assertEquals(2, argJ.getArgCalls().size());

			final Variable variableK = someFunction.getVariable("K");
			// type has been inferred by value assignment
			assertEquals(VbBaseType.INTEGER, variableK.getType());
			assertEquals(1, variableK.getVariableCalls().size());
		}

		final Sub someSub = module.getSub("SomeSub");

		assertNotNull(someSub);

		{
			assertFalse(someSub.getArgsList().isEmpty());
			assertEquals(1, someSub.getArgsList().size());

			final Arg argI = someSub.getArgs().get("I");
			// type has been inferred by value assignment
			assertEquals(VbBaseType.INTEGER, argI.getType());
			assertEquals(1, argI.getArgCalls().size());

			final Variable variableL = someSub.getVariable("L");
			// type has been inferred by value assignment
			assertEquals(VbBaseType.INTEGER, variableL.getType());
			assertEquals(1, variableL.getVariableCalls().size());
		}
	}

}
