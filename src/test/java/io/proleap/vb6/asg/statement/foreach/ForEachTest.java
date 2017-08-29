package io.proleap.vb6.asg.statement.foreach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ForEachTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/foreach/ForEach.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ForEach");

		{
			final Variable variable = module.getVariable("elements");
			assertEquals(1, variable.getVariableCalls().size());
		}

		{
			final ForEach forEach = (ForEach) module.getStatements().get(0);

			assertNotNull(forEach);
			assertEquals(StatementTypeEnum.FOR_EACH, forEach.getStatementType());

			{
				final ElementVariable elementVariable = forEach.getElementVariable();
				assertEquals(1, elementVariable.getElementVariableCalls().size());
			}
		}
	}
}
