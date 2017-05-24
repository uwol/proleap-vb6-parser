package io.proleap.vb6.asg.statement.with;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.statement.with.With;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class WithTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/with/With.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("With");

		{
			final io.proleap.vb6.asg.metamodel.Type myType = module.getType("MyType");
			assertNotNull(myType);
			assertEquals(myType, program.getTypeRegistry().getType("MyType"));

			{
				final TypeElement myProperty = myType.getTypeElement("MyProperty");
				assertNotNull(myProperty);
				assertEquals(VbBaseType.STRING, myProperty.getType());
				assertEquals(1, myProperty.getTypeElementCalls().size());
			}
		}

		{
			final Variable variable = module.getVariable("SomeObject");
			assertNotNull(variable);
			assertEquals(module.getType("MyType"), variable.getType());
			assertEquals(1, variable.getVariableCalls().size());
		}

		{
			final With with = (With) module.getStatements().get(0);
			assertNotNull(with);
			assertEquals(StatementTypeEnum.WITH, with.getStatementType());

			{
				final Statement statement = with.getStatements().get(0);
				assertEquals(StatementTypeEnum.LET, statement.getStatementType());

				final Let let = (Let) statement;
				final Call leftHandCall = let.getLeftHandCall();
				assertEquals(CallType.TYPE_ELEMENT_CALL, leftHandCall.getCallType());
			}
		}
	}
}
