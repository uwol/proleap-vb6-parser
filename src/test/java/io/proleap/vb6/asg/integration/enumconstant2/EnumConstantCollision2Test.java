package io.proleap.vb6.asg.integration.enumconstant2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class EnumConstantCollision2Test extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File clsSoundInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/enumconstant2/clsSound.cls");
		final File clsTestInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/enumconstant2/clsTest.cls");
		final File modGeneralInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/enumconstant2/modGeneral.bas");

		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(clsTestInputFile, modGeneralInputFile, clsSoundInputFile));

		{
			final ClazzModule clsSound = program.getClazzModule("clsSound");
			assertNotNull(clsSound);

			{
				final Function Sound_Play = clsSound.getFunction("Sound_Play");
				assertNotNull(Sound_Play);
				assertEquals(0, Sound_Play.getFunctionCalls().size());
			}

			{
				final Function Sound_Stop = clsSound.getFunction("Sound_Stop");
				assertNotNull(Sound_Stop);
				assertEquals(0, Sound_Stop.getFunctionCalls().size());
			}

			{
				final Variable cmdPlay = clsSound.getVariable("cmdPlay");
				assertNotNull(cmdPlay);
				assertEquals(0, cmdPlay.getVariableCalls().size());
			}
		}

		{
			final StandardModule modGeneral = program.getStandardModule("modGeneral");
			assertNotNull(modGeneral);

			{
				final Enumeration eSoundAction = modGeneral.getEnumeration("eSoundAction");
				assertNotNull(eSoundAction);

				{
					final EnumerationConstant Sound_Play = eSoundAction.getEnumerationConstant("Sound_Play");
					assertNotNull(Sound_Play);
					assertEquals(1, Sound_Play.getEnumerationConstantCalls().size());
				}

				{
					final EnumerationConstant Sound_Stop = eSoundAction.getEnumerationConstant("Sound_Stop");
					assertNotNull(Sound_Stop);
					assertEquals(1, Sound_Stop.getEnumerationConstantCalls().size());
				}

				{
					final EnumerationConstant cmdPlay = eSoundAction.getEnumerationConstant("cmdPlay");
					assertNotNull(cmdPlay);
					assertEquals(4, cmdPlay.getEnumerationConstantCalls().size());
				}
			}
		}
	}
}