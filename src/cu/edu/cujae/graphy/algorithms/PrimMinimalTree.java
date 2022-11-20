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
 * El <b>algoritmo de Prim</b> pertenece a la <i>teoría de grafos</i> y se encarga de encontrar un 
 * árbol recubridor mínimo en un <b>grafo conexo, no dirigido y ponderado</b>. Es decir, 
 * encuentra un subconjunto de aristas que forman un árbol con todos los vértices,
 * donde el peso total de todas las aristas en el árbol es el mínimo posible. Si
 * el grafo no es conexo, entonces encontrará el árbol de expansión mínima para uno
 * de los componentes conexos que forman el grafo original.
 * <p>
 * El algoritmo fue diseñado originalmente por el matemático <i>Vojtech Jarnik</i> en 1930,
 * y luego de manera independiente por el científico computacional <i>Robert C. Prim</i>
 * en 1957. Por último, fue redescubierto por <i>Dijkstra</i> en 1959.
 * <p>
 * Es conocido también como <b>algoritmo de DJP</b> o <b>algoritmo de Jarnik</b>. 
 * Posee una complejidad de <code>O(n<sup>2</sup>)</code>.
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
