package io.proleap.vb6.asg.call.enumeration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class EnumerationCallTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/call/enumeration/EnumerationCall.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("EnumerationCall");
		final Enumeration days = module.getEnumeration("Days");

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
