package io.proleap.vb6.asg.integration.variable;

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
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class VariableCollisionTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File clsSoundInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/variable/clsSound.cls");
		final File modGeneralInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/variable/modGeneral.bas");
		final File clsDuplicateInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/variable/clsDuplicate.cls");
		final File clsTestInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/variable/clsTest.cls");
		final File modTestInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/variable/modTest.bas");

		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(clsTestInputFile,
				modGeneralInputFile, clsSoundInputFile, clsDuplicateInputFile, modTestInputFile));

		{
			final ClazzModule clsDuplicate = program.getClazzModule("clsDuplicate");
			assertNotNull(clsDuplicate);

			{
				final Variable sound = clsDuplicate.getVariable("Sound");
				assertNotNull(sound);
				assertEquals(0, sound.getVariableCalls().size());
			}
		}

		{
			final StandardModule modGeneral = program.getStandardModule("modGeneral");
			assertNotNull(modGeneral);

			{
				final Variable sound = modGeneral.getVariable("Sound");
				assertNotNull(sound);
				assertEquals(2, sound.getVariableCalls().size());
			}
		}

		{
			final ClazzModule clsSound = program.getClazzModule("clsSound");
			assertNotNull(clsSound);

			{
				final Function sound_Play = clsSound.getFunction("Sound_Play");
				assertNotNull(sound_Play);
				assertEquals(2, sound_Play.getFunctionCalls().size());
			}
		}
	}
}
