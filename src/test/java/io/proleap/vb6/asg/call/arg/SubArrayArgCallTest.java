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
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class SubArrayArgCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/arg/SubArrayArgCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("SubArrayArgCall");
		final Sub someOtherSub = module.getSub("SomeOtherSub");
		assertNotNull(someOtherSub);

		{
			assertFalse(someOtherSub.getArgsList().isEmpty());
			assertEquals(1, someOtherSub.getArgsList().size());

			{
				final Arg argArrData = someOtherSub.getArgs().get("arrData");
				assertEquals(VbBaseType.BYTE, argArrData.getType());
				assertEquals(2, argArrData.getArgCalls().size());
			}
		}
	}
}
