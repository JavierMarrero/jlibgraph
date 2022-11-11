package cu.edu.cujae.graphy.algorithms;
/** 
 * @author César Férnandez García
 * Esta clase permite ver si existe al menos un Ciclo Euleriano
 * */
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
public class EulerianCycle<V> extends AbstractAlgorithm<List<Integer>> {

	private final Graph<V> graph;
	private final int V;
	public Graph<V> getGraph() {
		return graph;
	}


	public EulerianCycle(List<Integer> result, Graph<V> graph) {
		super(result);
		this.graph = graph;
		this.V = graph.size();
	}





	public EulerianCycle(List<Integer> result) {
		super(result);
		this.graph = null;
		this.V = graph.size();

		// TODO Auto-generated constructor stub
	}





	@Override
	public Algorithm<List<Integer>> apply() {
		boolean isOdd = false;
		GraphIterator<V> iterator = (GraphIterator<V>) graph.iterator();
		while(!isOdd && iterator.hasNext()) {
			if( ( ( ( (Node)iterator.next()).getConnectedEdges().size() ) % 2)==1) { 
				isOdd=true;
			}
		}
		return null;
	}


	public int getV() {
		return V;
	}




}
