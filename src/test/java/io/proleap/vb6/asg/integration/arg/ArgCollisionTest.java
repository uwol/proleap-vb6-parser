package io.proleap.vb6.asg.integration.arg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ArgCollisionTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File clsSoundInputFile = new File("src/test/resources/io/proleap/vb6/asg/integration/arg/clsSound.cls");

		final File modGeneralInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/arg/modGeneral.bas");

		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(clsSoundInputFile, modGeneralInputFile));

		{
			final ClazzModule clsSound = program.getClazzModule("clsSound");
			assertNotNull(clsSound);

			final Sub List_ItemClick = clsSound.getSub("List_ItemClick");
			assertNotNull(List_ItemClick);

			{
				final Arg cmdPlay = List_ItemClick.getArgs().get("cmdPlay");
				assertNotNull(cmdPlay);
				assertEquals(2, cmdPlay.getArgCalls().size());
			}
		}

		{
			final StandardModule modGeneral = program.getStandardModule("modGeneral");
			assertNotNull(modGeneral);

			{
				final Enumeration eSoundAction = modGeneral.getEnumeration("eSoundAction");
				assertNotNull(eSoundAction);

				{
					final EnumerationConstant cmdPlay = eSoundAction.getEnumerationConstant("cmdPlay");
					assertNotNull(cmdPlay);
					assertEquals(0, cmdPlay.getEnumerationConstantCalls().size());
				}
			}
		}
	}
}