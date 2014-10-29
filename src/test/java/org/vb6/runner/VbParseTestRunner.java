package org.vb6.runner;

import java.io.File;
import java.io.IOException;

public interface VbParseTestRunner {

	public void parseDirectory(final File inputDirectory) throws IOException;

	public void parseFile(final File inputFile) throws IOException;
}
