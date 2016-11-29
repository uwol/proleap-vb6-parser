package io.proleap.vb6.gpl.parser.statements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.ForNext;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.VbBaseType;
import io.proleap.vb6.parser.metamodel.valuestmt.ValueStmt;

public class ForNextTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/statements/ForNext.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ForNext");
		final ForNext forNext = (ForNext) module.getStatements().get(0);
		assertNotNull(forNext);

		{
			final ValueStmt from = forNext.getFrom();
			assertEquals(VbBaseType.INTEGER, from.getType());
		}

		{
			final ValueStmt to = forNext.getTo();
			assertEquals(VbBaseType.INTEGER, to.getType());
		}
	}

}
