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

	public Neo4jConnection ( String uri, String user, String password ) 
	{
		this ( GraphDatabase.driver ( uri, AuthTokens.basic ( user, password ) ) );
  }

	/**
	 * Starts a connection from an existing driver.
	 */
	public Neo4jConnection ( Driver driver ) 
	{
		this.driver = driver;
  }
	
	
	/**
	 * This is idempotent (can be called more than once, without any effect after the first call).
	 */
  @Override
  public void close()
  {
  	driver.close();
  }

  /**
   * Allows for reusing the driver of a given connection.
   */
	public Driver getDriver ()
	{
		return driver;
	}
  
  
}
