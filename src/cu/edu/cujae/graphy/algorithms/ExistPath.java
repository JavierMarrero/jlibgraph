package cu.edu.cujae.graphy.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

public class ExistPath<T> extends AbstractAlgorithm<List<Integer>> {

    private GraphIterator<T> iterator;
    private int last;

    public ExistPath(Graph<T> graph, int first, int last) throws IllegalArgumentException{
        super(new LinkedList<>());
        
        if(!graph.isDirected()){
            throw new IllegalArgumentException("The graph is not directed.");
        }

        //Build the iterator & initialize variables
        iterator = graph.iterator(first);
        this.last = last;
    }

    @Override
    @SuppressWarnings (
            {
                "unchecked",
            })
    public Algorithm<List<Integer>> apply() {
        Queue<Integer> queue = (Queue<Integer>)getResult();

        while(iterator.hasNext()){
            queue.add(iterator.getLabel());
            if(!(iterator.getLabel() == last)){
                iterator.next();
            }
            else {
                break;
            }
        }
        return this;
    }
}