package io.proleap.vb6.asg.call.type.nonmember.array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class NonMemberArrayTypeCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File classInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/type/nonmember/array/MyClass.cls");
		final File moduleInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/type/nonmember/array/NonMemberArrayTypeCall.bas");

		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(classInputFile, moduleInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("MyClass");
			assertNotNull(myClass);

			{
				final Sub cls = myClass.getSub("Cls");
				assertNotNull(cls);
				// calls in MyModule should not hit this sub
				assertEquals(0, cls.getSubCalls().size());
			}

			{
				final Function width = myClass.getFunction("Width");
				assertNotNull(width);
				// calls in MyModule should not hit this function
				assertEquals(0, width.getFunctionCalls().size());
			}
		}
	}
}