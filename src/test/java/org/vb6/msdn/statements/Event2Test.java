package org.vb6.msdn.statements;

import java.io.File;

import org.junit.Test;
import org.vb6.runner.VbParseTestRunner;
import org.vb6.runner.impl.VbParseTestRunnerImpl;

public class Event2Test {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/org/vb6/msdn/statements/Event2.cls");
		final VbParseTestRunner runner = new VbParseTestRunnerImpl();
		runner.parseFile(inputFile);
	}
}