package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import uk.ac.rothamsted.neo4j.utils.AbstractNeo4jTest;

public class AttributeSummaryTest extends AbstractNeo4jTest {

	public static void main(String[] args) {
		try ( AttributeSummaryTest test = new AttributeSummaryTest() ) {
			test.run();
		}
	}
	
	@Override
	public void doTest() {
		try(
				Neo4jMetaGraph testMeta = new Neo4jMetaGraph ( AbstractNeo4jTest.TEST_URL, AbstractNeo4jTest.TEST_USER, AbstractNeo4jTest.TEST_PWD ); 
			) 
		{
			List<AttributeSummaryRow> rows = testMeta.nodeAttributeSummary("Customer");
			for (AttributeSummaryRow row:rows) {
				System.out.println(row.toString());
			}
			// TODO: println() and the rest...
		}

	}
}
