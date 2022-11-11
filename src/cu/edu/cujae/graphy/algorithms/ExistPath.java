package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

public class ExistPath<T> extends AbstractAlgorithm<Boolean> {

    private GraphIterator<T> iterator;
    private int end;

    public ExistPath(Graph<T> graph, int start, int end) throws IllegalArgumentException{
        super(Boolean.FALSE);
        
        if(!graph.isDirected()){
            throw new IllegalArgumentException("The graph is not directed.");
        }

        //Build the iterator & initialize variables
        iterator = (GraphIterator<T>)graph.depthFirstSearchIterator(start, false);
        this.end = end;
    }

    @Override
    public Algorithm<Boolean> apply() {
        int label;

        while(iterator.hasNext()){
            iterator.next();
            label = iterator.getLabel();

            if(label == end){
                setResult(true);
                break;
            }
        }
        return this;
    }
}