package uk.ac.rothamsted.neo4j.utils;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

/**
 *
 * An helper class to be used as the parent of subclasses that need to work with a 
 * {@link Driver Neo4j driver}. 
 * 
 * A new driver is created by the constructor and the {@link #close()}
 * method is a wrapper to it.
 * 
 * Being {@link AutoCloseable}, you can init subclasses of this in a try() block.
 * 
 */
public abstract class Neo4jConnection implements AutoCloseable
{
	protected final Driver driver;

	public Neo4jConnection ( String uri,String user,String password ) 
	{
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user,password));
  }
	
  @Override
  public void close() throws Exception{
      driver.close();
  }

}
