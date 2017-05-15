package io.proleap.vb6.asg.statement.fornext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.VbBaseType;
import io.proleap.vb6.asg.metamodel.statement.StatementTypeEnum;
import io.proleap.vb6.asg.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class ForNextTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/asg/statement/fornext/ForNext.cls");
		final Program program = new VbParserRunnerImpl().analyzeFile(inputFile);

		final Module module = program.getClazzModule("ForNext");
		final ForNext forNext = (ForNext) module.getStatements().get(0);

		assertNotNull(forNext);
		assertEquals(StatementTypeEnum.FOR_NEXT, forNext.getStatementType());

		{
			final ValueStmt from = forNext.getFrom();
			assertEquals(VbBaseType.INTEGER, from.getType());
		}

		{
			final ValueStmt to = forNext.getTo();
			assertEquals(VbBaseType.INTEGER, to.getType());
		}
	}

}
