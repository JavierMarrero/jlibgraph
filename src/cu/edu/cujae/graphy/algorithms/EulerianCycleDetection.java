package cu.edu.cujae.graphy.algorithms;
/** 
 * @author César Férnandez García
 * Esta clase permite ver si existe al menos un Ciclo Euleriano
 * */
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.List;

public class EulerianCycleDetection<V> extends AbstractAlgorithm<Boolean> {

	


	private final Graph<V> graph;
	private final int V;
	public Graph<V> getGraph() {
		return graph;
	}


	public EulerianCycleDetection(Boolean result, Graph<V> graph) {
		super(result);
		this.graph = graph;
		this.V = graph.size();
	}





	public EulerianCycleDetection(Boolean result) {
		super(result);
		this.graph = null;
		this.V = graph.size();
	}





	@Override
	public Algorithm<Boolean> apply() {
		boolean isOdd = false;
		GraphIterator<V> iterator = (GraphIterator<V>) graph.iterator();
		while(!isOdd && iterator.hasNext()) {
			if( ( ( ( (Node)iterator.next()).getConnectedEdges().size() ) % 2)==1) { 
				isOdd=true;
			}
		}
		setResult(!isOdd);
		return this;
	}


	public int getV() {
		return V;
	}




}
