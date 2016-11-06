package io.proleap.vb6;

import org.junit.Before;

import io.proleap.vb6.parser.applicationcontext.VbParserContextFactory;

public class VbTestSupport {

	@Before
	public void setUp() throws Exception {
		VbParserContextFactory.configureDefaultApplicationContext();
	}
}
