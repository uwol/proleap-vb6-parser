package io.proleap.vb6.asg.call.constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ConstantCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/constant/ConstantCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

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
