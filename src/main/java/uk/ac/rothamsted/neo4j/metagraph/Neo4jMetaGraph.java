package uk.ac.rothamsted.neo4j.metagraph;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

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
	
  public Neo4jMetaGraph ( Driver driver )
	{
		super ( driver );
	}

	public Neo4jMetaGraph ()
	{
		super ();
	}

	public List<ClassSummaryRow> classesSummary()
  {
    try (Session session = driver.session()) {
      	List<ClassSummaryRow> result1 = new ArrayList<>();
      	Result result = session.run("MATCH (n) WITH LABELS(n) AS labels, COUNT(n) AS freq"
      			+ " UNWIND labels AS label RETURN DISTINCT (label) AS label, freq ORDER BY freq DESC");
        while (result.hasNext()) {
            Record record = result.next();
            result1.add(new ClassSummaryRow(record.get("label").asString(),record.get("freq").asLong()));
        }
        return result1;
    }  
  }
  
  public List<RelationSummaryRow> relationsSummary() {
      try (Session session = driver.session()) {
    	  List<RelationSummaryRow> result1 = new ArrayList<>();
      	  Result result = session.run("MATCH (x)-[r]->(y) WITH LABELS(x) AS fromTypes, LABELS(y) AS toTypes, COUNT(r) AS freq, TYPE(r) AS relType"
      			+ " UNWIND fromTypes AS fromType UNWIND toTypes AS toType UNWIND relType AS relationType RETURN fromType, toType, relationType, freq ORDER BY freq DESC");
          while (result.hasNext()) {
              Record record = result.next();
              result1.add(new RelationSummaryRow(record.get("fromType").asString(),record.get("toType").asString(),record.get("relationType").asString(),record.get("freq").asLong()));
          }
          return result1;
      }
  }
  
  public List<AttributeSummaryRow> nodeAttributesSummary ( String nodeLabel )
  {
      try (Session session = driver.session()) { 
    	List<AttributeSummaryRow> result1 = new ArrayList<>();
      	Result result = session.run("MATCH (n:"+ nodeLabel +") WITH KEYS(n) AS props, n"
      			+ " UNWIND props AS property RETURN DISTINCT property, COUNT(n) as freq");
          while (result.hasNext()) { 
              Record record = result.next();
              result1.add(new AttributeSummaryRow(record.get("property").asString(),record.get("freq").asLong()));
          }
          return result1;
      }   
  }
  
  public List<AttributeSummaryRow> relationAttributesSummary ( String relationType )
  {
	  try (Session session = driver.session()) {
		  List<AttributeSummaryRow> result1 = new ArrayList<>();
		  Result result = session.run("MATCH p=()-[r:" + relationType + "]->() WITH KEYS(r) AS props, r\n" +
				  " UNWIND props as property" +
				  " RETURN DISTINCT property, COUNT(r) as freq");
		  while (result.hasNext()) { 
              Record record = result.next();
              result1.add(new AttributeSummaryRow(record.get("property").asString(),record.get("freq").asLong()));
          }
		  return result1;
	  }
  }
}
