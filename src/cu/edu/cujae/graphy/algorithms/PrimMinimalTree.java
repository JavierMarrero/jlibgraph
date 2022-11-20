package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a weighted & undirected graph, this algorithm will return a Minimal Expansion Tree
 * <p>
 * Time complexity for this algorithm is at most <code>O(n<sup>2</sup>)</code>
 *
 * @author Carlos Daniel Robaina Rivero
 * @param <T>
 */
public class PrimMinimalTree<T> extends AbstractAlgorithm<WeightedGraph<T>>
{

    private WeightedGraph<T> graph;

    public PrimMinimalTree(Graph<T> graph)
    {
        super(GraphBuilders.makeSimpleWeightedGraph(false));

        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException("The graph is not weighted.");
        }
        if (graph.isDirected())
        {
            throw new IllegalArgumentException("The graph is directed.");
        }

        // TODO Check Connected Graph
        this.graph = (WeightedGraph<T>) graph;
    }

    @SuppressWarnings ("unchecked")
    @Override
    public Algorithm<WeightedGraph<T>> apply()
    {
        GraphIterator<T> iterator = graph.iterator(0);

        Set<T> addedNodes = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>((Edge a, Edge b) -> Integer.compare((Integer) a.getWeight().
                getValue(), (Integer) b.getWeight().getValue()));

        for (int i = 0; i < graph.size() - 1; i++)
        {
            //System.out.println("Node : " + iterator.getLabel());
            Edge shortestEdge;
            Node<T> finalNode;
            Node<T> startNode;
            queue.addAll(iterator.getAllAdjacentEdges());

            do
            {
                shortestEdge = queue.poll();
            }
            while (addedNodes.contains(shortestEdge.getStartNode().get()) && addedNodes.contains(shortestEdge.
                    getFinalNode().get()));

            if (addedNodes.isEmpty())
            {
                addedNodes.add((T) shortestEdge.getStartNode().get());
                get().add(shortestEdge.getStartNode().getLabel(), (T) shortestEdge.getStartNode().get());
            }

            if (addedNodes.contains(shortestEdge.getStartNode().get()))
            {
                startNode = (Node<T>) shortestEdge.getStartNode();
                finalNode = (Node<T>) shortestEdge.getFinalNode();
            }
            else
            {
                startNode = (Node<T>) shortestEdge.getFinalNode();
                finalNode = (Node<T>) shortestEdge.getStartNode();
            }

            addedNodes.add(finalNode.get());
            get().add(finalNode.getLabel(), finalNode.get());
            get().connect(startNode.getLabel(), finalNode.getLabel(), shortestEdge.getWeight());

            //System.out.println(get());
            iterator.next(finalNode);
        }

        return this;
    }
}
