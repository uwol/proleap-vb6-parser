package io.proleap.vb6.asg.call.type.member.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class TypeMemberCallFileOrderTest extends VbTestBase {

	final File classInputFile = new File("src/test/resources/io/proleap/vb6/asg/call/type/member/order/MyClass.cls");
	final File classScalarInputFile = new File(
			"src/test/resources/io/proleap/vb6/asg/call/type/member/order/MemberScalarTypeCallFileOrder.bas");

	protected void assertFunctionCalls(final ClazzModule myClass) {
		final Function myFunction = myClass.getFunction("MyFunction");
		assertNotNull(myFunction);
		assertEquals(2, myFunction.getFunctionCalls().size());
	}

	@Test
	public void test() throws Exception {
		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classInputFile, classScalarInputFile));
		final ClazzModule myClass = program.getClazzModule("MyClass");

		assertNotNull(myClass);
		assertFunctionCalls(myClass);
	}

	@Test
	public void testReverseFileOrder() throws Exception {
		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(classScalarInputFile, classInputFile));
		final ClazzModule myClass = program.getClazzModule("MyClass");

		assertNotNull(myClass);
		assertFunctionCalls(myClass);
	}
}