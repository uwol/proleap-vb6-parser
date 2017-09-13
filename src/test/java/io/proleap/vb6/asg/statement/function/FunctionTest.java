package io.proleap.vb6.asg.statement.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.ReturnValueCall;
import io.proleap.vb6.asg.metamodel.statement.Statement;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class FunctionTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/function/Function.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("Function");
		final Function function = (Function) module.getStatements().get(0);

		assertNotNull(function);
		assertEquals(StatementTypeEnum.FUNCTION, function.getStatementType());
		assertEquals(VbBaseType.INTEGER, function.getType());
		assertEquals("Mult", function.getName());

		assertEquals(2, function.getArgs().size());
		assertEquals(2, function.getArgsList().size());
		assertEquals(1, function.getStatements().size());

		{
			final Arg arg = function.getArgsList().get(0);
			assertEquals("Factor1", arg.getName());
			assertEquals(arg, function.getArgs().get("Factor1"));
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}

		{

			final Arg arg = function.getArgsList().get(1);
			assertEquals("Factor2", arg.getName());
			assertEquals(arg, function.getArgs().get("Factor2"));
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}

		{
			final Statement statement = function.getStatements().get(0);
			assertEquals(StatementTypeEnum.LET, statement.getStatementType());

			final Let let = (Let) statement;

			{
				final Call leftHandCall = let.getLeftHandCall();
				assertEquals(CallType.RETURN_VALUE_CALL, leftHandCall.getCallType());

				final ReturnValueCall returnValueCall = (ReturnValueCall) leftHandCall.unwrap();
				assertEquals(function, returnValueCall.getProcedure());
			}
		}
	}
}
