package io.proleap.vb6.asg.statement.property.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;

public class PropertyGetTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/property/get/PropertyGet.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("PropertyGet");
		final PropertyGet propertyGet = (PropertyGet) module.getStatements().get(0);

		assertNotNull(propertyGet);
		assertEquals(StatementTypeEnum.PropertyGet, propertyGet.getStatementType());
		assertEquals(VbBaseType.INTEGER, propertyGet.getType());
		assertEquals(0, propertyGet.getArgs().size());
	}

}
