package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import uk.ac.rothamsted.neo4j.utils.AbstractNeo4jTest;

/**
 * 
 * TODO: to be removed. I needed to rename it into *Old, due to conflicts with new classes in
 * src/test/
 *
 * <dl><dt>Date:</dt><dd>29 Jun 2021</dd></dl>
 *
 */
public class AttributeSummaryTestOld extends AbstractNeo4jTest {

	public static void main(String[] args) {
		try ( AttributeSummaryTestOld test = new AttributeSummaryTestOld() ) {
			test.run();
		}
	}
	
	@Override
	public void doTest() {
		try(
				Neo4jMetaGraph testMeta = new Neo4jMetaGraph ( AbstractNeo4jTest.TEST_URL, AbstractNeo4jTest.TEST_USER, AbstractNeo4jTest.TEST_PWD ); 
			) 
		{
			List<AttributeSummaryRow> rows = testMeta.nodeAttributesSummary("Customer");
			for (AttributeSummaryRow row:rows) {
				System.out.println(row.toString());
			}
			// TODO: println() and the rest...
		}

	}
}
