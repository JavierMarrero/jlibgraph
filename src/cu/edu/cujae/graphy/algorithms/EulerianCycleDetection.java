package cu.edu.cujae.graphy.algorithms;

/**
 * Esta clase permite ver si existe al menos un ciclo de Euler.
 *
 * @author César Férnandez García
 */
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

public class EulerianCycleDetection<V> extends AbstractAlgorithm<Boolean>
{

    private final Graph<V> graph;

    public EulerianCycleDetection(Graph<V> graph)
    {
        super(true);
        this.graph = graph;
    }

    @Override
    public Algorithm<Boolean> apply()
    {
        //esta funcion lo que hace es ver si deja de cumplirse la paridad de los grados de los nodos
        boolean isOdd = false;
        GraphIterator<V> iterator = (GraphIterator<V>) graph.depthFirstSearchIterator(false);
        while (!isOdd && iterator.hasNext())
        {
            iterator.next();
            int numberOfEdges = iterator.getAllAdjacentEdges().size();
            if ((numberOfEdges == 0) || ((numberOfEdges % 2) != 0))
            {
                isOdd = true;
            }

        }
        if (isOdd)
        {
            setResult(!isOdd);
        }

        return this;
    }

}
