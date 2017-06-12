package io.proleap.vb6.asg.call.type.member.me;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class MemberMeCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File classInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/type/member/me/MemberMeCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(classInputFile);

		{
			final ClazzModule myClass = program.getClazzModule("MemberMeCall");
			assertNotNull(myClass);

			{
				final Function myFunction = myClass.getFunction("MyFunction");
				assertNotNull(myFunction);
				assertEquals(1, myFunction.getFunctionCalls().size());
			}

			{
				final PropertyGet myPropertyGet = myClass.getPropertyGet("MyPropertyGet");
				assertNotNull(myPropertyGet);
				assertEquals(1, myPropertyGet.getPropertyGetCalls().size());
			}

			{
				final Sub mySub = myClass.getSub("MySub");
				assertNotNull(mySub);
				assertEquals(0, mySub.getSubCalls().size());
			}
		}
	}
}
