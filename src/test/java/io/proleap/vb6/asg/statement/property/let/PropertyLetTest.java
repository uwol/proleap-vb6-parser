package io.proleap.vb6.asg.statement.property.let;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class PropertyLetTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/property/let/PropertyLet.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("PropertyLet");
		final Variable variable = module.getVariable("CurrentI");
		assertNotNull(variable);

		final PropertyLet propertyLet = (PropertyLet) module.getStatements().get(0);
		assertNotNull(propertyLet);
		assertEquals(StatementTypeEnum.PROPERTY_LET, propertyLet.getStatementType());
		assertEquals(1, propertyLet.getArgs().size());
		assertEquals(1, propertyLet.getStatements().size());

		{
			final Arg arg = propertyLet.getArgsList().get(0);
			assertEquals("I", arg.getName());
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}

		{
			final Statement statement = propertyLet.getStatements().get(0);
			assertEquals(StatementTypeEnum.LET, statement.getStatementType());

			final Let let = (Let) statement;

			{
				final Call leftHandCall = let.getLeftHandCall();
				assertEquals(CallType.VARIABLE_CALL, leftHandCall.getCallType());

				final VariableCall variableCall = (VariableCall) leftHandCall.unwrap();
				assertEquals(variable, variableCall.getVariable());
			}
		}
	}
}
