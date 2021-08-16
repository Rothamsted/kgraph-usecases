package uk.ac.rothamsted.neo4j.metagraph;

/**
 * TODO: comment me!
 */
public class ClassSummaryRow
{
	private String label;
	private long frequency;

	/**
	 * 
	 */
	public ClassSummaryRow (String label, long frequency)
	{		
		super ();
		this.label = label;
		this.frequency = frequency;
		
	}

	public String getLabel() {
		return label;
	}
	
	public long getFreq() {
		return frequency;
	}
	
	public String toString() {
		return "label: " + getLabel() + ", Frequency: " + getFreq() ;
	}
	
}