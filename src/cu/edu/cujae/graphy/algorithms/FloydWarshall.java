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

import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.exceptions.InvalidOperationException;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.MapBiArray;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.ArrayList;


/**
 * El algoritmo de Floyd-Warshall es uno de los que se han creado para encontrar el camino más corto entre nodos, se trabaja
 * con programación dinámica, lo que garantiza que la solución entregada sea óptima.
 * Entrada: Grafo dirigido con pesos asociados a las aristas
 * Salida: Matriz distancia que entrega el menor camino para ir de un nodo a otro del grafo
 *         Matriz ruta que entrega el nodo intermedio para llegar desde un nodo a otro del grafo
 * 
 * @author Jose
 * @param <V>
 */
public class FloydWarshall<V> extends AbstractAlgorithm<Pair<MapBiArray<Integer,Integer,Integer>,MapBiArray<Integer,Integer,Integer>>>{
    private final WeightedGraph<V> graph;
    private static final int INF = Integer.MAX_VALUE;

    public FloydWarshall (WeightedGraph<V> graph){
        super(new Pair(null,null));
        this.graph = graph;
}

    @Override
    public Algorithm apply()
    {
        int cantVertices = graph.size();
        
        /*Se crean dos MapBiArray que funcionan como arreglos bidimensionales cada uno
        similar a distancia [i][j]=valor, donde valor es el tercer parámetro.*/
        MapBiArray<Integer,Integer,Integer> distancia = new MapBiArray();
        MapBiArray<Integer,Integer,Integer> ruta = new MapBiArray();
        
        ArrayList<Integer> vertices = new ArrayList<Integer>(graph.size());
        GraphIterator<V> iter = (GraphIterator<V>) graph.breadthFirstSearchIterator(false);
 
        // Guardando en una lista las etiquetas de cada nodo del grafo para acceder a ellas de forma secuencial
        while (iter.hasNext())
        {
            iter.next();
            vertices.add(iter.getLabel());
        }
       
        
        //Inicializar
        for (int i =0;i < cantVertices;i++)
        {
            for (int j=0;j< cantVertices;j++)
            {    
                GraphIterator<V> iterat1 = graph.iterator(vertices.get(i));
                GraphIterator<V> iterat2 = graph.iterator(vertices.get(j));
                
                int label1 = iterat1.getLabel();
                int label2 = iterat2.getLabel();
                // Si existe una arista adyacente se le asigna a distancia [i][j] el peso de dicha arista sino, seria INF
                try
                {
                    distancia.put(label1, label2, (Integer) iterat1.getAdjacentEdge(iterat2.getLabel()).getWeight().getValue());
                }
                catch (InvalidOperationException ex)
                {
                    //TODO: Añadir logging (slf4j como dependencia)
                    distancia.put(label1, label2, INF);
                }
                //La ruta más corta desde i hasta j pasa por el vertice j
                ruta.put(label1, label2, label2);
            }
        }
        
        //Determinar la ruta más corta
        for (int k = 0; k< cantVertices;k++)
        {
            for (int i=0; i<cantVertices;i++)
            {
                for (int j=0; j<cantVertices;j++)
                {
                    if(i==j)
                    {
                        distancia.put(i, j, Integer.MAX_VALUE);
                    }
                    else{
                        /*Si la ruta a través del vértice del subíndice k es más corta que la ruta entre los puntos originales, 
                        actualizar distancia [i][j] y ruta [i][j]*/ 
                    int tmp = (distancia.get(i,k) == INF || distancia.get(k,j) == INF) ? INF : (distancia.get(i, k) + distancia.get(k, j));
                        if (distancia.get(i, j) > tmp)
                        {
                            //El valor correspondiente a la ruta más corta de i a j se establece en el más pequeño después de k  
                            distancia.put(i, j, tmp);
                            //La ruta correspondiente a la ruta más corta de i a j pasa por k
                            ruta.put(i, j, ruta.get(i, k));
                        }
                    }
                }
            }     
        }
        
        for (int i =0;i<cantVertices;i++)
        {
            for(int j =0;j<cantVertices;j++)
            {
                if(distancia.get(i, j)== Integer.MAX_VALUE)
                {
                    ruta.put(i, j, Integer.MAX_VALUE);
                }
            }
        }
        setResult(new Pair<MapBiArray<Integer,Integer,Integer>,MapBiArray<Integer,Integer,Integer>>(distancia,ruta));
        return this;
        
    }
    
}
