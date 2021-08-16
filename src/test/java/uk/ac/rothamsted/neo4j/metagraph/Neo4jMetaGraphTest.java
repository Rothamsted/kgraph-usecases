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
	protected Neo4jMetaGraph testMeta = new Neo4jMetaGraph ( driver );
	
	@Test
	public void testClassesSummary ()
	{
		List<ClassSummaryRow> rows = testMeta.classesSummary();
		
		String expectedRow = "label: Category, Frequency: 3";

//		boolean expectedRowFound = false;
//		int rowIndex = 0;
//		
//		while (expectedRowFound != true) {
//			String row1 = rows.get(rowIndex).toString();
//			System.out.println(row1);
//			if (row1.matches(expectedRow) == true) {
//				expectedRowFound = true;
//				System.out.println("expected row found"); //just to help me while testing
//			} else {
//				rowIndex += 1;
//				System.out.println("expected row not found, next row searching... Row " + rowIndex); //just to help me while testing
//			}
//			
//		}
		
		// Compare this approach with yours above. Delete the commented code afterwards.
		//
		for ( ClassSummaryRow row: rows ) {
			System.out.println ( "ROW: " + row );
			// TODO: why were you using .match()?
			if ( expectedRow.equals ( row.toString () ) ) return;
		}
		
		// This is executed without checking anything every time it's reached.
		// Can you see why it works if expectedRow is found?
		Assert.fail ( "Probe row not found!" );	
		
	}
	
	@Test
	public void testRelationsSummary ()
	{
		
		List<RelationSummaryRow> rows = testMeta.relationsSummary();
		
		boolean expectedRowFound = false;
		String expectedRow = "Customer-ADDED_TO_WISH_LIST->Product Frequency: 6";
		
		for (RelationSummaryRow row: rows)
		{
			if (expectedRowFound = expectedRow.equals(row.toString())) 
				break;
		}		
		Assert.assertTrue("Expected probe relation not found!", expectedRowFound);	
	}
	
	@Test
	public void testNodeAttributesSummary ()
	{		
		List<AttributeSummaryRow> rows = testMeta.nodeAttributesSummary("Customer");

		boolean expectedRowFound = false;
		String expectedRow = "Property: name Frequency: 4";
		
		for (AttributeSummaryRow row: rows)
		{
			if (expectedRowFound = expectedRow.equals(row.toString())) 
				break;
		}
		
		Assert.assertTrue("Expected probe relation not found!", expectedRowFound);
	}

	@Test
	public void testRelationAttributesSummary ()
	{
		List<AttributeSummaryRow> rows = testMeta.relationAttributesSummary("VIEWED");

		boolean expectedRowFound = false;
		String expectedRow = "Property: views_count Frequency: 4";
		
		for (AttributeSummaryRow row: rows)
		{
			if (expectedRowFound = expectedRow.equals(row.toString())) 
				break;
		}
		
		Assert.assertTrue("Expected probe relation not found!", expectedRowFound);
	}
	
}
