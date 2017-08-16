package io.proleap.vb6.asg.call.arg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.FunctionCall;
import io.proleap.vb6.asg.metamodel.statement.callstmt.CallStmt;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class FunctionArgCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/arg/FunctionArgCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("FunctionArgCall");
		final Function someFunction = module.getFunction("SomeFunction");

		assertNotNull(someFunction);
		// type has been inferred by value assignment
		assertEquals(VbBaseType.INTEGER, someFunction.getType());

		{
			assertFalse(someFunction.getArgsList().isEmpty());
			assertEquals(2, someFunction.getArgsList().size());

			{
				final Arg argI = someFunction.getArgs().get("I");
				// type has been inferred by value assignment
				assertEquals(VbBaseType.INTEGER, argI.getType());
				assertEquals(1, argI.getArgCalls().size());
			}

			{
				final Arg argJ = someFunction.getArgs().get("J");
				// type has been inferred by value assignment
				assertEquals(VbBaseType.INTEGER, argJ.getType());
				assertEquals(2, argJ.getArgCalls().size());
			}

			{
				final Variable variableK = someFunction.getVariable("K");
				// type has been inferred by value assignment
				assertEquals(VbBaseType.INTEGER, variableK.getType());
				assertEquals(1, variableK.getVariableCalls().size());
			}
		}

		assertEquals(2, module.getStatements().size());

		{
			final CallStmt callStmt = (CallStmt) module.getStatements().get(1);
			final Call call = callStmt.unwrap();
			final FunctionCall functionCall = (FunctionCall) call;

			assertEquals(2, functionCall.getArgValueAssignments().size());

			{
				final ArgValueAssignment argValueAssignment = functionCall.getArgValueAssignments().get(0);
				final Arg arg = argValueAssignment.getArg();
				assertEquals("I", arg.getName());
			}

			{
				final ArgValueAssignment argValueAssignment = functionCall.getArgValueAssignments().get(1);
				final Arg arg = argValueAssignment.getArg();
				assertEquals("J", arg.getName());
			}
		}
	}
}
