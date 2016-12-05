package io.proleap.vb6.asg.statement.property.set;

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
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;

public class PropertySetTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/property/set/PropertySet.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("PropertySet");
		final PropertySet propertySet = (PropertySet) module.getStatements().get(0);

		assertNotNull(propertySet);
		assertEquals(StatementTypeEnum.PropertySet, propertySet.getStatementType());
		assertEquals(1, propertySet.getArgs().size());

		{
			final Arg arg = propertySet.getArgsList().get(0);
			assertEquals("I", arg.getName());
			assertEquals(VbBaseType.INTEGER, arg.getType());
		}
	}
}
