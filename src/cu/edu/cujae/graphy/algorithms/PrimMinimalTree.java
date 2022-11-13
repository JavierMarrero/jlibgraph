package cu.edu.cujae.graphy.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Tree;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

public class PrimMinimalTree<T> extends AbstractAlgorithm<Tree<T>>{

    private Graph<T> graph;
    private int start;

    public PrimMinimalTree(Graph<T> graph) {
        this(graph, 0);
    }

    @SuppressWarnings("unchecked")
    public PrimMinimalTree(Graph<T> graph, int rootLabel) {
        super((Tree<T>)GraphBuilders.makeSimpleWeightedGraph(true));

        if(!graph.isWeighted()){
            throw new IllegalArgumentException("The graph is not weighted.");
        }
        if(graph.isDirected()){
            throw new IllegalArgumentException("The graph is directed.");
        }

        // TODO Check Connected Graph

        this.graph = graph;
        this.start = rootLabel;
    }

    @Override
    public Algorithm<Tree<T>> apply() {
        GraphIterator<T> randomIter = graph.iterator(start);
        GraphIterator<T> iterator = (GraphIterator<T>)graph.depthFirstSearchIterator(false);

        PriorityQueue<Edge> queue =  new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                return Integer.compare((Integer)a.getWeight().getValue(), (Integer)b.getWeight().getValue());
            }
        });
        
        while(iterator.hasNext()){
            get().add(iterator.next());
        }

        for(int i = 0; i < graph.size(); i++){

        }

        return this;
    }    
}
