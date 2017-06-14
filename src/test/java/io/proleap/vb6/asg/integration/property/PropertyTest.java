package io.proleap.vb6.asg.integration.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import io.proleap.vb6.VbTestBase;
import io.proleap.vb6.asg.metamodel.ClazzModule;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

public class PropertyTest extends VbTestBase {

	@Test
	public void test() throws Exception {
		final File clsListInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/property/clsList.cls");
		final File clsListNodeInputFile = new File(
				"src/test/resources/io/proleap/vb6/asg/integration/property/clsListNode.cls");

		final Program program = new VbParserRunnerImpl()
				.analyzeFiles(Arrays.asList(clsListInputFile, clsListNodeInputFile));

		{
			final ClazzModule clsListNode = program.getClazzModule("clsListNode");
			assertNotNull(clsListNode);

			{
				final PropertyLet Data = clsListNode.getPropertyLet("Data");
				assertNotNull(Data);
				assertEquals(1, Data.getPropertyLetCalls().size());
			}

			{
				final PropertyLet Prev = clsListNode.getPropertyLet("Prev");
				assertNotNull(Prev);
				assertEquals(1, Prev.getPropertyLetCalls().size());
			}

			{
				final PropertyGet Prev = clsListNode.getPropertyGet("Prev");
				assertNotNull(Prev);
				assertEquals(3, Prev.getPropertyGetCalls().size());
			}

			{
				final PropertyLet Next = clsListNode.getPropertyLet("Next");
				assertNotNull(Next);
				assertEquals(1, Next.getPropertyLetCalls().size());
			}

			{
				final PropertyGet Next = clsListNode.getPropertyGet("Next");
				assertNotNull(Next);
				assertEquals(1, Next.getPropertyGetCalls().size());
			}
		}
	}
}