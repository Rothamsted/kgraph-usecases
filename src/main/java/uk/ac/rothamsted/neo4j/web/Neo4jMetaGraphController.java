package uk.ac.rothamsted.neo4j.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.rothamsted.neo4j.metagraph.AttributeSummaryRow;
import uk.ac.rothamsted.neo4j.metagraph.ClassSummaryRow;
import uk.ac.rothamsted.neo4j.metagraph.Neo4jMetaGraph;
import uk.ac.rothamsted.neo4j.metagraph.RelationSummaryRow;

@RestController
public class Neo4jMetaGraphController { // search for the correct prefix to have /metagraph here instead of all

	private Neo4jMetaGraph newMetaGraph()
	{
		return new Neo4jMetaGraph(<configured URI>, <configured user>, <configured pwd>); //same as within the abstractneo4jtest
	}

	@GetMapping ("/metagraph/classSummary")
	public List<ClassSummaryRow> classesSummary(){
		try (var meta = newMetaGraph()){
			return meta.classesSummary();
		}
	}
	
	@GetMapping ("/metagraph/attributeSummary") //for testing '?label=customer'
	public List<AttributeSummaryRow> attributeSummary(String label){
		try (var meta = newMetaGraph()){
			return meta.nodeAttributesSummary(label);
		}
	}
	
	@GetMapping ("/metagraph/relationSummary")
	public List<RelationSummaryRow> relationSummary(){
		try (var meta = newMetaGraph()){
			return meta.relationsSummary();
		}
	}
	
	@GetMapping ("/metagraph/relationAttributeSummary")
	public List<AttributeSummaryRow> relationAttributesSummary(String relationType){
		try (var meta = newMetaGraph()){
			return meta.relationAttributesSummary(relationType);
		}
	}
	
	//complete so that you can actually run it correctly, then create a JUnit test to make sure that the 
	//code above is working correctly too.
	
}
