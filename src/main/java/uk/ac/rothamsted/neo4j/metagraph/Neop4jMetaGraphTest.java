package uk.ac.rothamsted.neo4j.metagraph;

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
			
			testMeta.classesSummary();
			// TODO: println() and the rest...
		}
	}

	
}
