package io.proleap.vb6.asg.statement.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.function.Function;

public class FunctionTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/function/Function.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("Function");
		final Function function = (Function) module.getStatements().get(0);

		assertNotNull(function);
		assertEquals(StatementTypeEnum.Function, function.getStatementType());
		assertEquals("Mult", function.getName());

		assertEquals(2, function.getArgs().size());
		assertEquals(2, function.getArgsList().size());

		{

			final Arg arg = function.getArgsList().get(0);
			assertEquals("Factor1", arg.getName());
			assertEquals(arg, function.getArgs().get("Factor1"));
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}

		{

			final Arg arg = function.getArgsList().get(1);
			assertEquals("Factor2", arg.getName());
			assertEquals(arg, function.getArgs().get("Factor2"));
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}

		assertEquals(VbBaseType.INTEGER, function.getType());
	}

}
