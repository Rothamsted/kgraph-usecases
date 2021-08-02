package uk.ac.rothamsted.neo4j.wordcloud;

import org.neo4j.driver.Driver;

import uk.ac.rothamsted.neo4j.utils.Neo4jConnection;

/**
 * TODO: to be developed later
 *
 *
 */
public class Neo4jWordCloud extends Neo4jConnection
{
	public Neo4jWordCloud ( String uri, String user, String password )
	{
		super ( uri, user, password );
	}

	public Neo4jWordCloud ( Driver driver )
	{
		super ( driver );
	}
	
	public Neo4jWordCloud ()
	{
		super ();
	}	
}
