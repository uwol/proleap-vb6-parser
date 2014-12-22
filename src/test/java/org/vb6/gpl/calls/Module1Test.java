package org.vb6.gpl.calls;

import java.io.File;

import org.junit.Test;
import org.vb6.runner.VbParseTestRunner;
import org.vb6.runner.impl.VbParseTestRunnerImpl;

public class Module1Test {

	@Test
	public void test() throws Exception {
		final File inputDirectory = new File(
				"src/test/resources/org/vb6/gpl/calls/Module1.cls");
		final VbParseTestRunner runner = new VbParseTestRunnerImpl();
		runner.parseFile(inputDirectory);
	}
}