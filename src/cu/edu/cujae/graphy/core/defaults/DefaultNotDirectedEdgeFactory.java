package cu.edu.cujae.graphy.core.defaults;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.EdgeFactory;
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.abstractions.AbstractEdge;

public class DefaultNotDirectedEdgeFactory extends DefaultEdgeFactory implements EdgeFactory
{

    /**
     * {@inheritDoc}}
     */
    @Override
    public Edge build(Object label, Node<?> u, Node<?> v, Weight<?> w)
    {
        return new AbstractEdge(label, u, v, w, false)
        {

        };
    }

}
