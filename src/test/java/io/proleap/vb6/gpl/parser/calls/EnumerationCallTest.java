package io.proleap.vb6.gpl.parser.calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.vb6.VbTestSupport;
import io.proleap.vb6.parser.applicationcontext.VbParserContext;
import io.proleap.vb6.parser.metamodel.Enumeration;
import io.proleap.vb6.parser.metamodel.EnumerationConstant;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.Program;
import io.proleap.vb6.parser.metamodel.Variable;

public class EnumerationCallTest extends VbTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/gpl/parser/calls/EnumerationCall.cls");
		final Program program = VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

		final Module module = program.getClazzModule("EnumerationCall");
		final Enumeration days = module.getEnumerations().get("Days");

		assertNotNull(days);
		assertEquals(3, days.getEnumerationCalls().size());

		final EnumerationConstant monday = days.getEnumerationConstant("Monday");
		final EnumerationConstant tuesday = days.getEnumerationConstant("Tuesday");
		final EnumerationConstant friday = days.getEnumerationConstant("Friday");

		assertNotNull(monday);
		assertNotNull(tuesday);
		assertNotNull(friday);

		assertEquals(2, monday.getEnumerationConstantCalls().size());
		assertEquals(0, tuesday.getEnumerationConstantCalls().size());
		assertEquals(1, friday.getEnumerationConstantCalls().size());

		final Variable day1 = module.getVariable("Day1");

		assertNotNull(day1);
		// type has been inferred by value assignment
		assertEquals(days, day1.getType());

		final Variable day2 = module.getVariable("Day2");

		assertNotNull(day2);
		// type has been inferred by value assignment
		assertEquals(days, day2.getType());

		final Variable day3 = module.getVariable("Day3");

		assertNotNull(day3);
		// type has been inferred by value assignment
		assertEquals(days, day3.getType());
	}

}
