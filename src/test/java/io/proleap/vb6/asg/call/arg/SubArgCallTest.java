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
import io.proleap.vb6.asg.metamodel.call.SubCall;
import io.proleap.vb6.asg.metamodel.statement.callstmt.CallStmt;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class SubArgCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/arg/SubArgCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("SubArgCall");

		final Sub someSub = module.getSub("SomeSub");
		assertNotNull(someSub);

		{
			assertFalse(someSub.getArgsList().isEmpty());
			assertEquals(1, someSub.getArgsList().size());

			{
				final Arg argI = someSub.getArgs().get("I");
				// type has been inferred by value assignment
				assertEquals(VbBaseType.INTEGER, argI.getType());
				assertEquals(1, argI.getArgCalls().size());
			}

			{
				final Variable variableL = someSub.getVariable("L");
				// type has been inferred by value assignment
				assertEquals(VbBaseType.INTEGER, variableL.getType());
				assertEquals(1, variableL.getVariableCalls().size());
			}
		}

		assertEquals(2, module.getStatements().size());

		{
			final CallStmt callStmt = (CallStmt) module.getStatements().get(1);
			final Call call = callStmt.unwrap();
			final SubCall subCall = (SubCall) call;

			assertEquals(1, subCall.getArgValueAssignments().size());

			{
				final ArgValueAssignment argValueAssignment = subCall.getArgValueAssignments().get(0);
				final Arg arg = argValueAssignment.getArg();
				assertEquals("I", arg.getName());
			}
		}
	}
}
