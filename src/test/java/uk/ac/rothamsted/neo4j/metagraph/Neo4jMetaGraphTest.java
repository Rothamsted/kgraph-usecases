package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import org.junit.Test;

import uk.ac.rothamsted.neo4j.utils.Neo4jTestBase;

/**
 * TODO: comment me!
 *
 * <dl><dt>Date:</dt><dd>29 Jun 2021</dd></dl>
 *
 */
public class Neo4jMetaGraphTest extends Neo4jTestBase
{
	/**
	 * This is used for the tests in this class
	 */
	protected Neo4jMetaGraph testMeta = new Neo4jMetaGraph ( this.driver );
	
	@Test
	public void testClassesSummary ()
	{
		// Note we don't need the try() idiom to close the connection, since @After already does that
		List<ClassSummaryRow> rows = testMeta.classesSummary();
		
		boolean expectedRowFound = false;
		// TODO: Write a loop that searches for some expected row, when found, set expectedRowFound to
		// true and stop (break) the loop

		
		// TODO: Use Assert.assertTrue() to verify that expectedRowFound has actually found what expected
	}
	
	/**
	 * TODO: write a similar test for the relationsSummary() method and turn it into a test with the
	 * @Test annotation 
	 */
	public void testRelationsSummary ()
	{
		
	}
	
	/**
	 * TODO: write a similar test for nodeAttributesSummary()
	 */
	public void testNodeAttributesSummary ()
	{
		
	}

	/**
	 * TODO: write a similar test for relationAttributesSummary()
	 */
	public void testRelationAttributesSummary ()
	{
		
	}
	
}
