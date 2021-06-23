package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import uk.ac.rothamsted.neo4j.utils.AbstractNeo4jTest;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>21 Jun 2021</dd></dl>
 *
 */
public class Neop4jMetaGraphTest extends AbstractNeo4jTest
{

	/**
	 * @param args
	 */
	public static void main ( String[] args )
	{
		try ( Neop4jMetaGraphTest test = new Neop4jMetaGraphTest () ) {
			test.run ();
		}
	}

	@Override
	public void doTest ()
	{
		try (
			Neo4jMetaGraph testMeta = new Neo4jMetaGraph ( AbstractNeo4jTest.TEST_URL, AbstractNeo4jTest.TEST_USER, AbstractNeo4jTest.TEST_PWD ); 
		) 
		{
			
			List<ClassSummaryRow> rows = testMeta.classesSummary();
			for (ClassSummaryRow row:rows) {
				System.out.println(row.toString());
			}
			// TODO: println() and the rest...
		}
	}

	
}
