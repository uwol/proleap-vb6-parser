package io.proleap.vb6.gpl.parser.calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Constant;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.Variable;
import io.proleap.vb6.parser.metamodel.VbBaseType;

public class ConstantCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/calls/ConstantCall.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ConstantCall");
		final Constant constantI = module.getConstant("I");

		assertNotNull(constantI);
		assertFalse(constantI.getConstantCalls().isEmpty());
		assertEquals(1, constantI.getConstantCalls().size());
		// type has been defined by declaration
		assertEquals(VbBaseType.INTEGER, constantI.getType());

		final Variable variableJ = module.getVariable("J");

		assertNotNull(variableJ);
		assertFalse(variableJ.getVariableCalls().isEmpty());
		assertEquals(1, variableJ.getVariableCalls().size());
		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, variableJ.getType());
	}

}
