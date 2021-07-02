package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import org.junit.Assert;
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
	protected Neo4jMetaGraph testMeta = new Neo4jMetaGraph (this.driver);
	
	@Test
	public void testClassesSummary ()
	{
		createData();
		
		// Note we don't need the try() idiom to close the connection, since @After already does that
		List<ClassSummaryRow> rows = testMeta.classesSummary();
		
		boolean expectedRowFound = false;
		String expectedRow = "label: Category, Frequency: 3";
		resetDB();
		
		// TODO: Write a loop that searches for some expected row, when found, set expectedRowFound to
		// true and stop (break) the loop
		
		int rowIndex = 0;
		
		while (expectedRowFound != true) {
			String row1 = rows.get(rowIndex).toString();
			System.out.println(row1);
			if (row1.matches(expectedRow) == true) {
				expectedRowFound = true;
				System.out.println("expected row found"); //just to help me while testing
			} else {
				rowIndex += 1;
				System.out.println("expected row not found, next row searching... Row " + rowIndex); //just to help me while testing
			}
			
		}
		
		// TODO: Use Assert.assertTrue() to verify that expectedRowFound has actually found what expected
		
		Assert.assertTrue(expectedRowFound);	
		
	}
	
	/**
	 * TODO: write a similar test for the relationsSummary() method and turn it into a test with the
	 * @Test annotation 
	 */
	
	@Test
	public void testRelationsSummary ()
	{
		createData();
		
		List<RelationSummaryRow> rows = testMeta.relationsSummary();
		
		boolean expectedRowFound = false;
		String expectedRow = "Customer-ADDED_TO_WISH_LIST->Product Frequency: 6";
		resetDB();
		

		int rowIndex = 0;
		
		while (expectedRowFound != true) {
			String row1 = rows.get(rowIndex).toString();
			System.out.println(row1);
			if (row1.matches(expectedRow) == true) {
				expectedRowFound = true;
				System.out.println("expected row found"); //just to help me while testing
			} else {
				rowIndex += 1;
				System.out.println("expected row not found, next row searching... Row " + rowIndex); //just to help me while testing
			}
			
		}
		
		Assert.assertTrue(expectedRowFound);	
				
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
