package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
/**@author CesarFernandez*/
public class NoConexDetection<V> extends AbstractAlgorithm<Boolean> {
	private final Graph<V> graph;
	private final int NumVertex;
	public NoConexDetection(Graph<V> graph) {
		super(false);
		this.graph=graph;
		this.NumVertex=graph.getLabels().size();
	}

	@Override
	public Algorithm<Boolean> apply() {
		
		GraphIterator<V> iterator =(GraphIterator<V>) graph.depthFirstSearchIterator(false);
		int contNodes=0;
		while(iterator.hasNext()) {
			iterator.next();
			contNodes++;
		}
		if(contNodes!=NumVertex){
			setResult(true);
		}
		return this;
	}

}
