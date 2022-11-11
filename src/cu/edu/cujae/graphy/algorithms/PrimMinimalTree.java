package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Tree;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.trees.DefaultGeneralTree;

public class PrimMinimalTree<T> extends AbstractAlgorithm<Tree<T>>{

    GraphIterator<T> iterator;

    public PrimMinimalTree(Graph<T> graph) {
        this(graph, 0);
    }

    public PrimMinimalTree(Graph<T> graph, int rootLabel) {
        super(new DefaultGeneralTree<T>());

        if(!graph.isWeighted()){
            throw new IllegalArgumentException("The graph is not weighted.");
        }
        if(graph.isDirected()){
            throw new IllegalArgumentException("The graph is directed.");
        }

        iterator.getAllAdjacentEdges();

        iterator = graph.iterator(rootLabel);
        getResult().add(iterator.getLabel(), iterator.next());
    }

    @Override
    public Algorithm<Tree<T>> apply() {


        // TODO Auto-generated method stub
        return null;
    }    
}
