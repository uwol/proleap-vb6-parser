package io.proleap.vb6.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.api.ApiProperty;

public class ApiPropertyCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/ApiPropertyCall.cls");
		VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final ApiProperty apiProperty = VbParserContext.getInstance().getApiPropertyRegistry()
				.getApiProperty("AppMajor");

		assertNotNull(apiProperty);
		assertFalse(apiProperty.getApiPropertyCalls().isEmpty());
		assertEquals(2, apiProperty.getApiPropertyCalls().size());
	}

}
