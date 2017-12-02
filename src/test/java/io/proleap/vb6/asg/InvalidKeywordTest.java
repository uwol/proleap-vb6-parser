package io.proleap.vb6.asg;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class InvalidKeywordTest extends VbTestBase {

	@Test(expected = RuntimeException.class)
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/InvalidKeyword.cls");
		new VbParserRunnerImpl().analyzeFile(inputFile);
	}
}