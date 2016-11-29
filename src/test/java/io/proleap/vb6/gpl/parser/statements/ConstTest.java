package io.proleap.vb6.gpl.parser.statements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Constant;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.VbBaseType;

public class ConstTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/statements/Const.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("Const");
		final Constant constant = (Constant) module.getStatements().get(0);
		assertNotNull(constant);
		assertEquals(VbBaseType.STRING, constant.getType());
	}

}
