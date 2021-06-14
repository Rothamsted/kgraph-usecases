package uk.ac.rothamsted.neo4j.metagraph;

import java.util.List;

import uk.ac.rothamsted.neo4j.utils.Neo4jConnection;

/**
 * 
 *	Utilities to build meta-graph summaries like list of classes or relations.
 *
 */
public class Neo4jMetaGraph extends Neo4jConnection
{
	
	public Neo4jMetaGraph ( String uri, String user, String password )
	{
		super ( uri, user, password );
	}
	
  public List<ClassSummaryRow> classesSummary()
  {
  	// TODO: take the existing code and adapt it to this
  	// It should create a ClassSummaryRow for every record coming from Neo4j and
  	// add it to a result list
  	// 
  	// List results in descending order (by frequency)
  	return null;
  }
  
  public List<RelationSummaryRow> relationsSummary() {
  	// TODO: As above
  	return null;
  }
  
  public List<AttributeSummaryRow> nodeAttributeSummary ( String nodeLabel )
  {
  	// TODO: as above
  	return null;
  }
  
  public List<AttributeSummaryRow> relationAttributeSummary ( String relationType )
  {
  	// TODO: leave this for later. It shouldn't be difficult to write by using an approach similar
  	// to nodeAttributeSummary:
  	
  	var query = "MATCH p=()-[r:" + relationType + "]->() WITH KEYS(r) AS props, r\n" +
  		"UNWIND props as prop" +
  		"...";
  	
  	return null;
  }

}
