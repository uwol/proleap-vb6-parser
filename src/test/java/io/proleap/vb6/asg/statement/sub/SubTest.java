package io.proleap.vb6.asg.statement.sub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class SubTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/sub/Sub.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("Sub");
		final Sub sub = (Sub) module.getStatements().get(0);

		assertNotNull(sub);
		assertEquals(StatementTypeEnum.SUB, sub.getStatementType());
		assertEquals("Mult", sub.getName());

		assertEquals(2, sub.getArgs().size());
		assertEquals(2, sub.getArgsList().size());

		{
			final Arg arg = sub.getArgsList().get(0);
			assertEquals("Factor1", arg.getName());
			assertEquals(arg, sub.getArgs().get("Factor1"));
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}

		{

			final Arg arg = sub.getArgsList().get(1);
			assertEquals("Factor2", arg.getName());
			assertEquals(arg, sub.getArgs().get("Factor2"));
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}
	}
}
