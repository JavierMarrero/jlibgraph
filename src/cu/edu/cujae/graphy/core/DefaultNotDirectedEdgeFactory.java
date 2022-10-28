package cu.edu.cujae.graphy.core;

public class DefaultNotDirectedEdgeFactory extends DefaultEdgeFactory implements EdgeFactory {

    /**
     * {@inheritDoc}}
     */
    @Override
    public Edge build(Object label, Node<?> u, Node<?> v, Weight<?> w) {
        return new AbstractEdge(label, u, v, w, false) {
            
        };
    }

}
