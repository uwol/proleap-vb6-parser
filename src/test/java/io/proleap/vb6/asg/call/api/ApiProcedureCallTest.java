package io.proleap.vb6.asg.call.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.api.ApiProcedure;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ApiProcedureCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/api/ApiProcedureCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final ApiProcedure midApiProcedure = program.getApiProcedureRegistry().getApiProcedure("Mid");

		assertNotNull(midApiProcedure);
		assertFalse(midApiProcedure.getApiProcedureCalls().isEmpty());
		assertEquals(2, midApiProcedure.getApiProcedureCalls().size());
	}

}
