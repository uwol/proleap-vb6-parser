package io.proleap.vb6.gpl.parser.calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.oop.api.ApiProcedure;

public class ApiProcedureCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/calls/ApiProcedureCall.cls");
		VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final ApiProcedure midApiProcedure = VbParserContext.getInstance().getApiProcedureRegistry()
				.getApiProcedure("Mid");

		assertNotNull(midApiProcedure);
		assertFalse(midApiProcedure.getApiProcedureCalls().isEmpty());
		assertEquals(2, midApiProcedure.getApiProcedureCalls().size());
	}

}
