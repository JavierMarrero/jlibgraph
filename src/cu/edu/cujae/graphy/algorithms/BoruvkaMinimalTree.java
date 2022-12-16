/*
 * Copyright (C) 2022 Ananda.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package cu.edu.cujae.graphy.algorithms;

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.Weight;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;
import java.util.ArrayList;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Un árbol de expansión significa que todos los vértices deben estar conectados.Por lo tanto, los dos subconjuntos
 * disjuntos de vértices deben estar conectados
 * con el borde de peso mínimo para convertirlo en un árbol de expansión mínimo (MST)
 * .<p>
 * El <b>algoritmo de Boruvka</b> es considerado voraz y utilizado para encontrar
 * el <b>árbol recubridor mínimo</b> en un grafo ponderado, en el que todos sus
 * arcos poseen distinto peso. Fue publicado por primera vez en 1926, por
 * <i>Otakar Boruvka</i> como método eficiente para construir la red eléctrica
 * de Moravia. También es conocido como <i>algoritmo de Sollin</i>.
 * <p>
 * Su complejidad temporal es <code>O(E log(V))</code>, donde E es el número de
 * arcos y V el número de vértices del grafo.
 * <p>
 * Existen algoritmos similares para la obtención de árboles de expansión mínimo,
 * como es el caso del <i>algoritmo de Kruskal</i> y el <i>algoritmo de Prim</i>.
 *
 * @see PrimMinimalTree
 * @see KruskalMinimumSpanningTree
 *
 * @author Ananda
 */
public class BoruvkaMinimalTree extends AbstractAlgorithm<WeightedGraph<?>>
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BoruvkaMinimalTree.class);

    /**
     * Represents a disjoint set of vertices.
     */
    @SuppressWarnings ("serial")
    private final static class Component extends TreeSet<Integer>
    {
        
        public static Component makeSingleton(WeightedGraph<?> graph, int u)
        {
            Component singleton = new Component(graph);
            singleton.add(u);
            return singleton;
        }
        
        private final WeightedGraph<?> graph;
        
        public Component(WeightedGraph<?> graph)
        {
            this.graph = graph;
        }
        
        public boolean union(Component component)
        {
            return addAll(component);
        }
        
        public Edge getLesserWeightEdge()
        {
            Edge result = null;
            float lesser = Float.MAX_VALUE;
            
            GraphIterator<?> i = graph.randomIterator();
            for (int v : this)
            {
                i.next(v);
                
                for (Edge e : i.getEdgesDepartingSelf())
                {
                    if (isInternal(e) == false)
                    {
                        @SuppressWarnings ("unchecked")
                        Weight<Number> weight = (Weight<Number>) e.getWeight();
                        
                        if (weight.getValue().floatValue() < lesser)
                        {
                            result = e;
                            lesser = weight.getValue().floatValue();
                        }
                    }
                }
            }
            
            return result;
        }
        
        private boolean isInternal(Edge edge)
        {
            return contains(edge.getStartNode().getLabel()) && contains(edge.getFinalNode().getLabel());
        }
    }
    
    private final WeightedGraph<?> graph;
    
    public BoruvkaMinimalTree(WeightedGraph<?> graph)
    {
        super(GraphBuilders.makeSimpleWeightedGraph(false));
        if (!graph.isWeighted())
        {
            throw new IllegalArgumentException("Attempted to apply Boruvka algorithm to an unweighted graph.");
        }
        if (graph.isDirected())
        {
            throw new IllegalArgumentException("Attempted to apply Boruvka algorithm to a directed graph.");
        }
        
        this.graph = graph;
    }
    
    @Override
    public Algorithm<WeightedGraph<?>> apply()
    {
        //asumiendo que el grafo es conexo.
        WeightedGraph<?> mst = getResult();

        // List of components
        ArrayList<Component> components = new ArrayList<>(graph.size() * (2 / 3));

        // Initialize all the components to a singleton
        for (int v : graph.getLabels())
        {
            components.add(Component.makeSingleton(graph, v));
        }
        
        LOGGER.debug("{}", components);
        
        while (components.size() > 1)
        {
            LOGGER.info("{}", components);
            
            for (int i = 0; i < components.size(); ++i)
            {
                Component connectableComponent = null;
                Component currentComponent = components.get(i);
                Edge cheapest = currentComponent.getLesserWeightEdge();

                // Find to what component does the edge belongs to
                for (Component c : components)
                {
                    int connectionPoint = cheapest.getFinalNode().getLabel();
                    if (c.equals(currentComponent) == false && c.contains(connectionPoint))
                    {
                        connectableComponent = c;
                        break;
                    }
                }
                
                if (connectableComponent == null)
                {
                    throw new IllegalStateException("Unable to find connection for " + cheapest);
                }

                // Merge the components & remove the second from the list
                currentComponent.union(connectableComponent);
                components.remove(connectableComponent);
                
            }
        }

        //Paso 3: devolver MST.
        return this;
    }
    
}
