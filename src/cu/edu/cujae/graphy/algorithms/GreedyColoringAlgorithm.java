/*
 * Copyright (C) 2022 Asus.
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
import cu.edu.cujae.graphy.core.Node;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * La coloración de grafos es un caso especial de etiquetas de grafos; es una
 * asignación de etiquetas llamadas colores a los elementos del grafo. De manera
 * simple, una coloración de los vértices de un grafo tal que ningún vértice
 * adyacente comparta el mismo color es llamado vértice coloración, y es el
 * punto de inicio de la coloración.
 * <p>
 * No existe ningún algoritmo eficiente disponible para colorear un grafo con
 * un número mínimo de colores, ya que e un problema NP-Completo conocido. Sin
 * embargo, existen algoritmos aproximados para resolver el problema.
 * <p>
 * A continuación se muestra el algoritmo Greedy básico para asignar colores.
 * No garantiza el uso de colores mínimos, pero garantiza un límite superior en
 * la cantidad de colores. Este algoritmo básico nunca usa más de d+1 colores
 * donde d es el grado máximo de un vértice en el gráfico dado.
 *
 * @author Amanda Méndez
 * @param<T>
 */
public class GreedyColoringAlgorithm<T> extends AbstractAlgorithm<Graph<T>>
{

    // Campos de la clase
    public final Graph<T> graph;
    
    public GreedyColoringAlgorithm(Graph<T> graph)
    {
        super(graph);

        // Inicializar campos de la clase
        this.graph = graph;
    }
    
    @Override
    public Algorithm<Graph<T>> apply()
    {
        
        Map<Integer, Integer> result = new HashMap<>(graph.size());

        // Inicializar todos los vértices a no asignados
        for (int u : graph.getLabels())
        {
            result.put(u, -1);
        }

        // Asignar el primer color al primer vértice
        Collection<Integer> labels = graph.getLabels();
        Iterator<Integer> it = labels.iterator();
        int firstVertex = it.next();
        
        result.put(firstVertex, 0);

        // Almacenar el color de los vértices adyacentes de "u"
        Set<Integer> available = new TreeSet<>();
        available.addAll(labels);

        // Asignar colores 
        while (it.hasNext())
        {
            // Lista de vértices adyacentes a "u"
            int u = it.next();
            GraphIterator<T> ite = graph.iterator(u);
            
            for (int i : ite.getAllAdjacentVertices())
            {
                if (result.get(i) != -1)
                {
                    available.remove(result.get(i));
                }
            }

            // Encontrar el primer color disponible
            int cr = 0;
            for (int c : labels)
            {
                if (available.contains(cr++))
                {
                    cr = c;
                    break;
                }
            }
            
            result.put(u, cr);
            
            available.addAll(labels);
        }
        
        Set<Integer> keySet = result.keySet();
        
        for (int k : keySet)
        {
            GraphIterator<T> ite = graph.iterator(k);
            ite.setAttribute(Node.COLOR, result.get(k));
        }
        
        return this;
    }
    
}
