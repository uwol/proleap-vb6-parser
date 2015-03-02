/*
Copyright (C) 2014 u.wol@wwu.de

This file is part of vb6grammar.

vb6grammar is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

vb6grammar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with vb6grammar. If not, see <http://www.gnu.org/licenses/>.
 */

package org.vb6.runner;

import java.io.File;
import java.io.IOException;

public interface VbParseTestRunner {

	public void parseDirectory(final File inputDirectory) throws IOException;

	public void parseFile(final File inputFile) throws IOException;
}
