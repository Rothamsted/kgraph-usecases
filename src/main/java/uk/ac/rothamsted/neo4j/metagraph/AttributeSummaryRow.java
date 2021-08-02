package uk.ac.rothamsted.neo4j.metagraph;

/**
 * TODO: comment me!
 *
 *
 */
public class AttributeSummaryRow {
	private String property;
	private long frequency;
	
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
	
	public String toString() {
		return "Property: " + getProperty() + " Frequency: " + getFreq() ;
	}
	
	
}
