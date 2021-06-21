package uk.ac.rothamsted.neo4j.metagraph;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: comment me!
 */
public class ClassSummaryRow
{
	public String label;
	public long frequency;

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
	
}