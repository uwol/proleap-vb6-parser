package io.proleap.vb6.asg.call.arg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.call.ArgCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.SubCall;
import io.proleap.vb6.asg.metamodel.statement.explicitcallstmt.ExplicitCallStmt;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class SubNamedArgCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/arg/SubNamedArgCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("SubNamedArgCall");

		final Sub someSub = module.getSub("studentInfo");
		assertNotNull(someSub);

		{
			assertFalse(someSub.getArgsList().isEmpty());
			assertEquals(3, someSub.getArgsList().size());

			{
				final Arg argName = someSub.getArgs().get("name");
				assertEquals(VbBaseType.STRING, argName.getType());
				assertFalse(argName.isOptional());
				assertEquals(1, argName.getArgCalls().size());

				{
					final ArgCall argCall = argName.getArgCalls().get(0);
					assertEquals("name", argCall.getArg().getName());
				}
			}

			{
				final Arg argAge = someSub.getArgs().get("age");
				assertEquals(VbBaseType.INTEGER, argAge.getType());
				assertTrue(argAge.isOptional());
				assertEquals(1, argAge.getArgCalls().size());

				{
					final ArgCall argCall = argAge.getArgCalls().get(0);
					assertEquals("age", argCall.getArg().getName());
				}
			}

			{
				final Arg argBirth = someSub.getArgs().get("birth");
				assertEquals(VbBaseType.DATE, argBirth.getType());
				assertTrue(argBirth.isOptional());
				assertEquals(1, argBirth.getArgCalls().size());

				{
					final ArgCall argCall = argBirth.getArgCalls().get(0);
					assertEquals("birth", argCall.getArg().getName());
				}
			}
		}

		assertEquals(2, module.getStatements().size());

		{
			final ExplicitCallStmt explicitCallStmt = (ExplicitCallStmt) module.getStatements().get(1);
			final Call explicitCall = explicitCallStmt.unwrap();
			final SubCall subCall = (SubCall) explicitCall;

			assertEquals(3, subCall.getArgValueAssignments().size());

			{
				final ArgValueAssignment argValueAssignment = subCall.getArgValueAssignments().get(0);
				final Arg arg = argValueAssignment.getArg();
				assertEquals("age", arg.getName());
			}

			{
				final ArgValueAssignment argValueAssignment = subCall.getArgValueAssignments().get(1);
				final Arg arg = argValueAssignment.getArg();
				assertEquals("birth", arg.getName());
			}

			{
				final ArgValueAssignment argValueAssignment = subCall.getArgValueAssignments().get(2);
				final Arg arg = argValueAssignment.getArg();
				assertEquals("name", arg.getName());
			}
		}
	}
}
