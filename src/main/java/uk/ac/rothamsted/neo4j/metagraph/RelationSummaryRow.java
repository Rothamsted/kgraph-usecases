package uk.ac.rothamsted.neo4j.metagraph;

/**
 * TODO: comment me!
 *
 */
public class RelationSummaryRow
{
	private String fromType, toType, relationType, frequency;

	public RelationSummaryRow ( String fromType, String toType, String relationType, String frequency )
	{
		super ();
		this.fromType = fromType;
		this.toType = toType;
		this.relationType = relationType;
		this.frequency = frequency;
	}

	public String getFromType ()
	{
		return fromType;
	}

	public String getToType ()
	{
		return toType;
	}

	public String getRelationType ()
	{
		return relationType;
	}

	public String getFrequency ()
	{
		return frequency;
	} 
	
	
}
