package io.proleap.vb6.asg.statement.property.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.ReturnValueCall;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class PropertyGetTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/property/get/PropertyGet.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("PropertyGet");
		final PropertyGet propertyGet = (PropertyGet) module.getStatements().get(0);

		assertNotNull(propertyGet);
		assertEquals(StatementTypeEnum.PROPERTY_GET, propertyGet.getStatementType());
		assertEquals(VbBaseType.INTEGER, propertyGet.getType());
		assertEquals(0, propertyGet.getArgs().size());
		assertEquals(1, propertyGet.getStatements().size());

		{
			final Statement statement = propertyGet.getStatements().get(0);
			assertEquals(StatementTypeEnum.LET, statement.getStatementType());

			final Let let = (Let) statement;

			{
				final Call leftHandCall = let.getLeftHandCall();
				assertEquals(CallType.RETURN_VALUE_CALL, leftHandCall.getCallType());

				final ReturnValueCall returnValueCall = (ReturnValueCall) leftHandCall.unwrap();
				assertEquals(propertyGet, returnValueCall.getProcedure());
			}
		}
	}
}
