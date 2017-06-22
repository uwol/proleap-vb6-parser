package io.proleap.vb6.asg.integration.overrides;

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
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class TypeMemberCallByScalarTest extends VbTestBase {

	final File classInputFile = new File("src/test/resources/io/proleap/vb6/asg/integration/overrides/MyClass.cls");
	final File classScalarInputFile = new File(
			"src/test/resources/io/proleap/vb6/asg/integration/overrides/MyClassScalar.cls");
	final File moduleScalarInputFile = new File(
			"src/test/resources/io/proleap/vb6/asg/integration/overrides/MyModuleScalar.bas");

	@Test
	public void test1() throws Exception {
		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classInputFile, moduleScalarInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("MyClass");
			assertNotNull(myClass);

			{
				final Function myFunction = myClass.getFunction("MyFunction");
				assertNotNull(myFunction);
				// calls from MyModule should not hit this function, only
				// the 1 ME call in the class itself
				assertEquals(1, myFunction.getFunctionCalls().size());
			}

			{
				final PropertyGet myPropertyGet = myClass.getPropertyGet("MyPropertyGet");
				assertNotNull(myPropertyGet);
				// calls from MyModule should not hit this function, only
				// the 1 ME call in the class itself
				assertEquals(1, myPropertyGet.getPropertyGetCalls().size());
			}

			{
				final Sub mySub = myClass.getSub("MySub");
				assertNotNull(mySub);
				assertEquals(0, mySub.getSubCalls().size());
			}
		}

		{
			final StandardModule myModuleScalar = program.getStandardModule("MyModuleScalar");
			assertNotNull(myModuleScalar);

			{
				final io.proleap.vb6.asg.metamodel.Type myType = myModuleScalar.getType("MyType");
				assertNotNull(myType);
				assertEquals(myType, program.getTypeRegistry().getType("MyType"));

				{
					final TypeElement myFunction = myType.getTypeElement("MyFunction");
					assertNotNull(myFunction);
					assertEquals(VbBaseType.STRING, myFunction.getType());
					// calls in MyModule should hit this type element
					assertEquals(3, myFunction.getTypeElementCalls().size());
				}

				{
					final TypeElement mySub = myType.getTypeElement("MySub");
					assertNotNull(mySub);
					assertEquals(VbBaseType.STRING, mySub.getType());
					// calls in MyModule should hit this type element
					assertEquals(2, mySub.getTypeElementCalls().size());
				}
			}
		}
	}

	@Test
	public void test2() throws Exception {
		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classInputFile, classScalarInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("myClass");
			assertNotNull(myClass);

			{
				final Function myFunction = myClass.getFunction("MyFunction");
				assertNotNull(myFunction);
				assertEquals(3, myFunction.getFunctionCalls().size());
			}

			{
				final Sub pause = myClass.getSub("Pause");
				assertNotNull(pause);
				assertEquals(6, pause.getSubCalls().size());
			}

			{
				final PropertyGet loadedGet = myClass.getPropertyGet("Loaded");
				assertNotNull(loadedGet);
				assertEquals(2, loadedGet.getPropertyGetCalls().size());
			}

			{
				final PropertyLet loadedLet = myClass.getPropertyLet("Loaded");
				assertNotNull(loadedLet);
				assertEquals(4, loadedLet.getPropertyLetCalls().size());
			}

			{
				final Function initialize = myClass.getFunction("Initialize");
				assertNotNull(initialize);
				assertEquals(2, initialize.getFunctionCalls().size());
			}

		}
	}

	@Test
	public void test3() throws Exception {
		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classScalarInputFile, classInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("myClass");
			assertNotNull(myClass);

			{
				final Function myFunction = myClass.getFunction("MyFunction");
				assertNotNull(myFunction);
				assertEquals(3, myFunction.getFunctionCalls().size());
			}

			{
				final Sub pause = myClass.getSub("Pause");
				assertNotNull(pause);
				assertEquals(6, pause.getSubCalls().size());
			}

			{
				final PropertyGet loadedGet = myClass.getPropertyGet("Loaded");
				assertNotNull(loadedGet);
				assertEquals(2, loadedGet.getPropertyGetCalls().size());
			}

			{
				final PropertyLet loadedLet = myClass.getPropertyLet("Loaded");
				assertNotNull(loadedLet);
				assertEquals(4, loadedLet.getPropertyLetCalls().size());
			}

			{
				final Function initialize = myClass.getFunction("Initialize");
				assertNotNull(initialize);
				assertEquals(2, initialize.getFunctionCalls().size());
			}

		}
	}

	@Test
	public void test4() throws Exception {
		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classInputFile, classScalarInputFile, moduleScalarInputFile));

		{
			final ClazzModule myClass = program.getClazzModule("myClass");
			assertNotNull(myClass);

			{
				final Function Play_Sound = myClass.getFunction("Play_Sound");
				assertNotNull(Play_Sound);
				// calls to enum member Play_Sound should not hit this
				// function
				assertEquals(1, Play_Sound.getFunctionCalls().size());
			}

			{
				final Variable Close_Window = myClass.getVariable("Close_Window");
				assertNotNull(Close_Window);
				// calls to enum member Close_Window should not hit this
				// variable
				assertEquals(0, Close_Window.getVariableCalls().size());
			}
		}
	}
}