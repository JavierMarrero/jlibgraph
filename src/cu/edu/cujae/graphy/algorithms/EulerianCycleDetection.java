/*
 * Copyright (C) 2022 CUJAE.
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

import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;

/**
 * En la <i>teoría de grafos</i>, un camino euleriano es un camino que pasa
 * por cada arista una y solo una vez. Un <b>ciclo o circuito euleriano</b> es un camino
 * cerrado que recorre cada arista exactamente una vez.
 * <p>
 * El problema de encontrar dichos caminos fue discutido por primera vez por <i>Leonhard
 * Euler</i> en 1736, en el famoso problema de los siete puentes de la ciudad de Königsberg; 
 * dando origen a la <i>teoría de grafos</i>.
 * A partir de este descubrimiento surgen las siguientes propiedades:
 * <ul>
 * <li>Un grafo conexo y no dirigido se dice que es euleriano si cada vértice 
 * tiene un grado par.</li>
 * <li>Un grafo no dirigido es euleriano si es conexo y si se puede descomponer 
 * en uno con los vértices disjuntos.</li>
 * <li>Si un grafo no dirigido G es euleriano entonces su gráfo-línea L(G) se 
 * dice que es también euleriano.</li>
 * <li>Un grafo dirigido es euleriano si es conexo y cada vértice tiene grados 
 * internos iguales a los externos.</li>
 * <li>Un grafo no dirigido se dice que es susceptible de ser recorrido si es 
 * conexo y dos vértices en el grafo tienen grado impar.</li>
 * </ul>
 * <p>
 * Este algoritmo permite conocer si existe al menos un <b>ciclo de Euler</b>. 
 * Su complejidad es de <code>O(V + E)</code>. 
 *
 * @author César Férnandez García
 */

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
