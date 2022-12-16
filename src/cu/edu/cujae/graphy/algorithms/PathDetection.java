package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

/**
 * Given a graph, a starting vertex and an ending vertex, determinate if exist a path between this two vertex.
 * <p>
 * Time complexity for this algorithm is at most <code>O(n)</code>
 *
 * @param <T> The type of the graph
 */
public class PathDetection<T> extends AbstractAlgorithm<Boolean>
{

    private GraphIterator<T> iterator;
    private int end;

    /**
     *
     * @param graph The graph on which the algorithm is to be executed
     * @param start Label of the starting vertex
     * @param end   Label of the ending vertex
     *
     * @throws IllegalArgumentException If the selected graph is not directed
     */
    public PathDetection(Graph<T> graph, int start, int end) throws IllegalArgumentException
    {
        super(Boolean.FALSE);

        if (!graph.isDirected())
        {
            throw new IllegalArgumentException("The graph is not directed.");
        }

        //Build the iterator & initialize variables
        iterator = (GraphIterator<T>) graph.depthFirstSearchIterator(start, false);
        this.end = end;
    }

    @Override
    public Algorithm<Boolean> apply()
    {
        int label;

        while (iterator.hasNext())
        {
            iterator.next();
            label = iterator.getLabel();

            if (label == end)
            {
                setResult(true);
                break;
            }
        }
        return this;
    }
}
