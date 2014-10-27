package org.vb6.gpl.statements;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.vb6.ThrowingErrorListener;
import org.vb6.VisualBasic6Lexer;
import org.vb6.VisualBasic6Parser;

public class OptionPrivateTest {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/org/vb6/gpl/statements/OptionPrivate.cls");

		final InputStream inputStream = new FileInputStream(inputFile);
		final VisualBasic6Lexer lexer = new VisualBasic6Lexer(new ANTLRInputStream(inputStream));

		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final VisualBasic6Parser parser = new VisualBasic6Parser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);

		parser.startRule();
	}
}