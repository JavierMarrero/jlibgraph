/*
 * Copyright (C) 2022 Jose.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este algoritmo permite obtener todas las comunidades de nivel 1, es decir 
 * los componentes fuertemente conexos del grafos. Recibe como parámetro el grafo
 * y retorna una lista de colecciones donde cada lista contiene los label de cada vértice 
 * que conforma ese componente, no se cuentan los vértices aislados del grafo como 
 * componente fuertemente conexo.
 * 
 * @author Jose
 * @param <V>
 */
public class FirstLevelCommunities<V> extends AbstractAlgorithm<ArrayList<Collection<Integer>>>
{

    private final Graph<V> graph;

    public FirstLevelCommunities(Graph<V> graph)
    {
        //En caso de no tener comunidades retorna una lista vacía
        super(new ArrayList<Collection<Integer>>());
        this.graph = graph;

    }

    @Override
    public Algorithm<ArrayList<Collection<Integer>>> apply()
    {
        ArrayList<Collection<Integer>> comunities = new ArrayList<>();
        try
        {
            //Clonar el grafo para ir eliminando las comunidades de dicha copia
            Graph<V> graphCopy = graph.duplicate();
            boolean restart;
            int isolated = new IsolatedVertices<>(graphCopy).apply().get().size();
            //Mientras la cantidad de vértices del grafo sin tener en cuenta los vértices aislados 
            //sea mayor que 0 seguir buscando comunidades
            while ((graphCopy.size() - isolated) > 0)
            {
                GraphIterator<V> iter = (GraphIterator<V>) graphCopy.breadthFirstSearchIterator(true);
                restart = false;
                //Mientras tenga siguiente el iterador y no sea necesario reiniciar el iterador
                while (iter.hasNext() && !restart)
                {
                    iter.next();
                    LinkedList<Integer> adyacentes = new LinkedList<>(iter.getAllAdjacentVertices());
                    Iterator<Integer> iteradya = adyacentes.iterator();
                    while (iteradya.hasNext())
                    {
                        int i = iteradya.next();
                        GraphIterator<V> iterator = graphCopy.iterator(i);
                        iterator.next(i);
                        Collection<Integer> adya = iterator.getAllAdjacentVertices();
                        //bandera booleana para controlar si uno de los adyacentes no forma parte de la 
                        //posible comunidad
                        boolean stop = false;
                        /*
                        De cada vértice adyacente verificar si entre sus adyacentes se encuentran todos los adyacentes
                        de la posible comunidad
                        */
                        for (Iterator<Integer> it = adyacentes.iterator(); it.hasNext() && !stop;)
                        {
                            Integer v = it.next();
                            /*
                            Si no se encuentra entre los adyacentes y es diferente del vértice inicial
                            y es diferente de sí mismo parar la iteracion y eliminarlo de la lista de la 
                            posible comunidad
                            */
                            if (!adya.contains(v) && v != iter.getLabel() && i != v)
                            {
                                stop = true;
                            }
                        }
                        if (stop)
                        {
                            iteradya.remove();
                        }
                    }
                    if (!adyacentes.isEmpty())
                    {
                        ArrayList<Integer> result = new ArrayList<>();
                        result.addAll(adyacentes);
                        result.add(iter.getLabel());
                        /*
                        Se adiciona como comunidad todos los vértices que hayan permanecido en la lista de adyacentes
                        es decir, los que se tienen a ellos mismos como adyacentes y el vértice inicial
                        */
                        comunities.add(result);
                        for (Integer i : result)
                        {   
                            /*Se eliminan los vértices de la comunidad seleccionada del clon del grafo
                             y se activa la bandera booleana para reiniciar el iterador*/
                            graphCopy.removeAt(i);
                            restart = true;
                        }
                    }
                }
                //Se actualiza la lista de los vértices que pudieron quedar aislados al eliminar vértices del grafo
                isolated = new IsolatedVertices<>(graphCopy).apply().get().size();
            }
        }
        catch (CloneNotSupportedException ex)
        {
            Logger.getLogger(FirstLevelCommunities.class.getName()).log(Level.SEVERE, null, ex);
        }
        setResult(comunities);
        return this;
    }

}
