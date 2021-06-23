package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import uk.ac.rothamsted.neo4j.utils.AbstractNeo4jTest;

public class RelationSummaryTest extends AbstractNeo4jTest {

	public static void main(String[] args) {
		try ( RelationSummaryTest test = new RelationSummaryTest() ) {
			test.run();
		}

	}	
	
	@Override
	public void doTest() {
		// TODO Auto-generated method stub
		try(
				Neo4jMetaGraph testMeta = new Neo4jMetaGraph ( AbstractNeo4jTest.TEST_URL, AbstractNeo4jTest.TEST_USER, AbstractNeo4jTest.TEST_PWD ); 
			) 
		{
			List<RelationSummaryRow> rows = testMeta.relationsSummary();
			for (RelationSummaryRow row:rows) {
				System.out.println(row.toString());
			}
			// TODO: println() and the rest...
		}

	}

}
