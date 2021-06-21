package uk.ac.rothamsted.neo4j.metagraph;

/**
 * TODO: comment me!
 *
 *
 */
public class AttributeSummaryRow {
	private String property;
	private long frequency;
	
	// TODO: generate the constructor and the getters as the other *Row classes.
	// ==> This can be done quickly in Eclipse via Source->Generate getters and setters and
	// Source->Generate constructor using fields
	// We need getters only, because these classes are unmutable objects, their state (ie, fields)
	// is initialised at creation and remain read-only during the object lifetime.	
	
	public AttributeSummaryRow(String property, long frequency) {
		
		super ();
		this.property = property;
		this.frequency = frequency;
		
		
	}
	
	public String getProperty() {
		return property;
	}
	
	public long getFreq() {
		return frequency;
	}
	
}
