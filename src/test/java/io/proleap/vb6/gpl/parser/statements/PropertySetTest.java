package io.proleap.vb6.gpl.parser.statements;

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
import io.proleap.vb6.parser.metamodel.PropertySet;
import io.proleap.vb6.parser.metamodel.VbBaseType;

public class PropertySetTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/statements/PropertySet.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("PropertySet");
		final PropertySet propertySet = (PropertySet) module.getStatements().get(0);
		assertNotNull(propertySet);
		assertEquals(1, propertySet.getArgs().size());

		{
			final Arg arg = propertySet.getArgsList().get(0);
			assertEquals("I", arg.getName());
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}
	}
}
