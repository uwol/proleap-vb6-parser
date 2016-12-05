package io.proleap.vb6.gpl.parser.statement.sub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Arg;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.parser.metamodel.statement.sub.Sub;

public class SubTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/statement/sub/Sub.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("Sub");
		final Sub sub = (Sub) module.getStatements().get(0);

		assertNotNull(sub);
		assertEquals(StatementTypeEnum.Sub, sub.getStatementType());
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
