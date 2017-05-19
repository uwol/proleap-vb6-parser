package io.proleap.vb6.asg.call.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.api.ApiProperty;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ApiPropertyCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/api/ApiPropertyCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final ApiProperty apiProperty = program.getApiPropertyRegistry().getApiProperty("AppMajor");

		assertNotNull(apiProperty);
		assertFalse(apiProperty.getApiPropertyCalls().isEmpty());
		assertEquals(2, apiProperty.getApiPropertyCalls().size());
	}

}
