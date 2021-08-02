package uk.ac.rothamsted.neo4j.utils;

import java.util.Optional;

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
	/**
	 * The connection coordinates for the test database.
	 * 
	 * If you use the Neo4j Maven plug-in, ensure these match {@code <configuration>}.
	 * 
	 * These were moved here, so that they can be used by non-testing component too 
	 * (eg, Spring).
	 * 
	 */
	public static final String 
		TEST_URL = "bolt://localhost:7687",
		TEST_USER = "neo4j",
		TEST_PWD = "test123";

	protected final Driver driver;
	
	
	/**
	 * Gets the connection parameters from the environment variables
	 * NEO_URL, NEO_USER, NEO_PWD.
	 * 
	 * If these are not set, uses the constants defined above.
	 * To set such variables, <a href = "https://tinyurl.com/y78qpmn8">Windows</a>,
	 * <a href = "https://tinyurl.com/yeueoyts">Unix/Linux</a>.
	 */
	public Neo4jConnection ()
	{
		this ( 
			Optional.ofNullable ( System.getenv ( "NEO_URL" ) ).orElse ( TEST_URL ), 
			Optional.ofNullable ( System.getenv ( "NEO_USER" ) ).orElse ( TEST_USER ), 			
			Optional.ofNullable ( System.getenv ( "NEO_PWD" ) ).orElse ( TEST_PWD ) 			
		);
	}
	
	
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
