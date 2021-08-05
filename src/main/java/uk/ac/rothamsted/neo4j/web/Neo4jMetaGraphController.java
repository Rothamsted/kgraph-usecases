package uk.ac.rothamsted.neo4j.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import uk.ac.rothamsted.neo4j.metagraph.AttributeSummaryRow;
import uk.ac.rothamsted.neo4j.metagraph.ClassSummaryRow;
import uk.ac.rothamsted.neo4j.metagraph.Neo4jMetaGraph;
import uk.ac.rothamsted.neo4j.metagraph.RelationSummaryRow;
import uk.ac.rothamsted.neo4j.utils.AbstractNeo4jTest;

@RestController
@RequestMapping(value = "/metagraph", method = RequestMethod.GET)
public class Neo4jMetaGraphController extends AbstractNeo4jTest{ // search for the correct prefix to have /metagraph here instead of all

	private Neo4jMetaGraph newMetaGraph() {
		return new Neo4jMetaGraph(TEST_URL, TEST_USER, TEST_PWD);
		// Use connection params from either the environment or test constants, see the
		// method implementation
		return new Neo4jMetaGraph(); 
	}

	@GetMapping ("/classSummary")
	public List<ClassSummaryRow> classesSummary(){
		try (var meta = newMetaGraph()){
			return meta.classesSummary();
		}
	}
	
	@GetMapping ("/attributeSummary") //for testing '?label=Customer'
	public List<AttributeSummaryRow> attributeSummary(String nodeLabel){
		try (var meta = newMetaGraph()){
			return meta.nodeAttributesSummary(nodeLabel);
		}
	}
	
	@GetMapping ("/relationSummary")
	public List<RelationSummaryRow> relationSummary(){
		try (var meta = newMetaGraph()){
			return meta.relationsSummary();
		}
	}
	
	@GetMapping ("/relationAttributeSummary")
	public List<AttributeSummaryRow> relationAttributesSummary(String relationType){
		try (var meta = newMetaGraph()){
			return meta.relationAttributesSummary(relationType);
		}
	}

	@Override
	public void doTest() {
		// TODO Auto-generated method stub
		
	}
	
	//test manually first as shown in attributeSummary then:
	//complete so that you can actually run it correctly, then create a JUnit test to make sure that the 
	//code above is working correctly too. JUnit testing can be found in the testing of the Greeting example/online
	
}
