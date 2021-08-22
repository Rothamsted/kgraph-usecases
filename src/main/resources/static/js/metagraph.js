/**
 * Helper to draw a graph using Cytoscape.js
 * graphData must be a JSON in the format expeted by Cytoscape.
 * 
 * htmlElementId is the id of the HTML element where to render the graph visualisation
 * (it's reccomended to be an empty <div>, see the .html files).
 *
 * This helper auto-creates elements like the style or layout.
 */
function drawGraph ( graphData, htmlElementId = "cy" )
{
  let cy = cytoscape({
    container: document.getElementById( htmlElementId ),
    elements: graphData,
    style: [
      {
        selector: 'node',
        style: {
        	'label': 'data(label)'
        }
      },
      {
        selector: 'edge',
        style: {
        	'label': 'data(label)',
        	'curve-style': 'bezier',
        	'target-arrow-shape': 'triangle'
        }
      }],
			layout:
			{
        name: 'cose',
        idealEdgeLength: 100,
        nodeOverlap: 20,
        refresh: 20,
        fit: true,
        padding: 30,
        randomize: false,
        componentSpacing: 100,
        nodeRepulsion: 400000,
        edgeElasticity: 100,
        nestingFactor: 5,
        gravity: 80,
        numIter: 1000,
        initialTemp: 200,
        coolingFactor: 0.95,
        minTemp: 1.0
      }       
  });
}

/**
 * Small helper to call our API URLs. Returns back the API JSON, as is. 
 * Actually, returns a 'promise', ie, an asynch function that can be invoked later.
 */
function callApi ( path )
{
	let url = myUrlBase () + path;
	
	return fetch ( url )
  .then ( response => response.json() );
}

/* 
 * Renders A Neo4j metagraph using our /classSummary and relationSummary API calls.
 * 
 * The method converts and combine the JSON results from these calls into Cytoscape format and renders the graph
 * using drawGraph().
 */
function drawMetaGraph ( htmlElementId = "cy" )
{
	// dirty trick to filter this messy non-relevant nodes and relations, see below
	let nodeFilter = [ "Resource", "Concept", "DataSource", "EvidenceType", "Accession" ];
	let relationFilter = [ "relatedConcept" ];
	
	// Get the nodes
	callApi ( "/metagraph/classSummary" )
	.then ( js =>
	{
		// Take the result from our API and turn it into Cytoscape format 
		cyData = [];
		for (let i in js)
		{
			let entry = js [ i ];
			let nodeLabel = entry [ 'label' ], freq = entry [ 'freq' ];
			let label = nodeLabel + " (" + freq + ")";

			// Just ignore these node types.
			if ( nodeFilter.includes ( nodeLabel ) ) continue;
			
			// Cytoscape format
			cyData.push ( { data: { id: nodeLabel, label: label } } );
		}
		return cyData;
	})
	.then ( cyData =>
	{
		// After the nodes, work to the relations in a similar way
		// This is another deferred 'promise' that is chained to the rest automatically
		
		return callApi ( "/metagraph/relationSummary" )
		.then ( js => 
		{
			for (let i in js)
			{
				let entry = js [ i ];
				let type = entry [ 'relationType' ], freq = entry [ 'frequency' ], 
						from = entry [ "fromType" ], to = entry [ "toType" ];
				let id = from + ":" + type + ":" + to;
				let label = type + " (" + freq + ")";
				
				// As above, filter non-relevant types away
				if ( relationFilter.includes ( type ) ) continue;
				if ( nodeFilter.includes ( from ) || nodeFilter.includes ( to ) ) continue;

				// 'from'/'to' refer to the node fragments using their 'id' property. 
				// This way, Cytoscape understands which nodes have to be linked together.
				//
				// 'label' is how it displays it, which is different than the id
				cyData.push ( { data: { id: id, label: label, source: from, target: to } } );
			}
			return cyData;
		});
	})
	.then ( cyData => drawGraph ( cyData, htmlElementId ) );
	// Eventually, pass the Cytoscape JSON format to our drawing method.
	// These methods are all asynchronous, the code flow reaches this point here immediately and 
	// then a separated parallel flow starts calling the APIs (via HTTP) and it calls the other 
	// chained methods above as soon as the the API yields a result.
}

/* 
 * Simple helper to get our site base. 
 * Eg, if you call this with the '/' default and you're testing from localhost, it will 
 * return 'http://localhost:8080', the URL it sees in the .documentURI below. 
 */
function myUrlBase ( pathMarker = "/" )
{
	let docUrl = document.documentURI;
	let i = docUrl.lastIndexOf ( pathMarker ); // search the tail
	if ( i < 0 ) return docUrl; // found it?
	
	return docUrl.substring ( 0, i ); // if yes, return everything but that tail
}
