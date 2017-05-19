package io.proleap.vb6.asg.call.type.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class TypeMemberCallByArrayTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File classInputFile = new File("src/test/resources/io/proleap/vb6/asg/call/type/member/MyClass.cls");
		final File moduleInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/call/type/member/MyModuleArray.bas");

		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(classInputFile, moduleInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("MyClass");
			assertNotNull(myClass);

			{
				final Function myFunction = myClass.getFunction("MyFunction");
				assertNotNull(myFunction);
				// calls in MyModule should not hit this function
				assertEquals(0, myFunction.getFunctionCalls().size());
			}

			{
				final Sub mySub = myClass.getSub("MySub");
				assertNotNull(mySub);
				// calls in MyModule should not hit this sub
				assertEquals(0, mySub.getSubCalls().size());
			}
		}

		{
			final StandardModule myModuleArray = program.getStandardModule("MyModuleArray");
			assertNotNull(myModuleArray);

			{
				final io.proleap.vb6.asg.metamodel.Type myType = myModuleArray.getType("MyType");
				assertNotNull(myType);
				assertEquals(myType, program.getTypeRegistry().getType("MyType"));

				{
					final TypeElement myFunction = myType.getTypeElement("MyFunction");
					assertNotNull(myFunction);
					assertEquals(VbBaseType.STRING, myFunction.getType());
					// calls in MyModule should hit this type element
					assertEquals(4, myFunction.getTypeElementCalls().size());
				}

				{
					final TypeElement mySub = myType.getTypeElement("MySub");
					assertNotNull(mySub);
					assertEquals(VbBaseType.STRING, mySub.getType());
					assertEquals(0, mySub.getTypeElementCalls().size());
				}
			}
		}
	}
}
