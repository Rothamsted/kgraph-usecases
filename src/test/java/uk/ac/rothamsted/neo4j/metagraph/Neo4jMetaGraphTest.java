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
		
		Assert.assertTrue ( expectedRowFound );	
		
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
		
		/* See notes below on a possible replacement of this
		 
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
		*/
		
		// The loop above works, but what about replacing it with this?
		for ( RelationSummaryRow row: rows )
		{
			// This is a shortcut of assignment + evaluation, figure out why it works as expected here
			// Moreover, why using regular expressions (ie, matches()) in this case? Copy-pasted code shouldn't be used
			// too casually, always check what it actually means
			//
			if ( expectedRowFound = expectedRow.equals ( row.toString () ) ) 
				break; // Why does this make it faster?
		}
		
		// What if expectedRow isn't found? What will happen to expectedRowFound?
		
		// Not using a failure message is bad practice, we want to have an idea of why the test didn't pass, what it was 
		// attempting to verify and the like. The message usually doesn't need to mention the test class or methdod, since
		// the existing test frameworks (eg, Maven, Eclipse) report that automatically.
		//
		// Please, check the variant assertTrue( String, Boolean ) and even experiment with this version by 
		// intentionally making the test to fail, e.g., trying !expectedRowFound for a moment, just to see that it prints
		// the message in the reports.
		//
		Assert.assertTrue ( "Expected probe relation not found!", expectedRowFound );	
		
		// Further notes:
		//
		// An alternative to evaluating toString() could be:
		// "Customer".equals ( row.getFromType () ) 
		//   && "ADDED_TO_WISH_LIST".equals ( row.getRelationType () )
		//   && "Product".equals ( row.getToType () )
		//   && 6 == row.getFrequency ()
	  //
		// In general, toString() might change format and cause tests like this to fail, despite their substance remaining
		// the same.
		// 
		// Another clean approach is to implement RelationSummaryRow.equals() and RelationSummaryRow.hashCode()
	  // and then do:
		
	  // expectedRow = new RelationSummaryRow ( "Customer", "Product", "ADDED_TO_WISH_LIST", 6 )
		// for (...)
	  //   if ( ... expectedRow.equals ( row ) )
		// 
	  // equals() + hashCode() are crucial when using types with collections, more here:
	  // https://howtodoinjava.com/java/basics/java-hashcode-equals-methods/
		// (the quickest method is usually Eclipse)
		//		
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
