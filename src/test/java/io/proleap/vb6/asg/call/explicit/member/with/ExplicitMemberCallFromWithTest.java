package io.proleap.vb6.asg.call.explicit.member.with;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ExplicitMemberCallFromWithTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File classAInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/explicit/member/with/ClassA.cls");
		final File classBInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/explicit/member/with/ClassB.cls");
		final File moduleWithCommonInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/explicit/member/with/Module.bas");

		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classAInputFile, classBInputFile, moduleWithCommonInputFile));

		final ClazzModule classA = program.getClazzModule("ClassA");
		assertNotNull(classA);

		final ClazzModule classB = program.getClazzModule("ClassB");
		assertNotNull(classB);

		final Module module = program.getModule("Module");
		assertNotNull(module);

		{
			final Sub common = module.getSub("Common");
			assertNotNull(common);
			assertEquals(0, common.getSubCalls().size());
		}

		{
			final Sub common = classA.getSub("Common");
			assertNotNull(common);
			assertEquals(1, common.getSubCalls().size());
		}

		{
			final Sub common = classB.getSub("Common");
			assertNotNull(common);
			assertEquals(0, common.getSubCalls().size());
		}
	}
}