package io.proleap.vb6.asg;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class HelloWorldTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/HelloWorld.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);
		final ClazzModule clazzModule = program.getClazzModule("HelloWorld");
		assertNotNull(clazzModule);
		assertNotNull(clazzModule.getVariable("I"));
		assertNotNull(clazzModule.getVariable("J"));
	}
}