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

import cu.edu.cujae.graphy.core.Edge;
import cu.edu.cujae.graphy.core.WeightedGraph;
import cu.edu.cujae.graphy.core.iterators.GraphIterator;
import cu.edu.cujae.graphy.utils.MapTriArray;
import cu.edu.cujae.graphy.utils.Pair;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jose
 * @param <V>
 */
public class ExistSimpleRoad<V> extends AbstractAlgorithm<Pair<Integer,List<Integer>>>
{

    private final WeightedGraph<V>  graph;
    private final int k;
    private final GraphIterator<V> iteratorNI;
    private final GraphIterator<V> iteratorNF;
    private final int vertices;
    private final int pesoMayor;

    public ExistSimpleRoad(WeightedGraph<V> graph, GraphIterator<V> iteratorNI, GraphIterator<V> iteratorNF, int k, int pesoMayor)
    {
        super (new Pair(0,new LinkedList<Integer>()));
        this.graph = graph;
        this.iteratorNI = iteratorNI;
        this.iteratorNF = iteratorNF;
        this.k = k;
        this.vertices = graph.size();
        this.pesoMayor = pesoMayor + 1;
    }

    @Override
    public Algorithm<Pair<Integer,List<Integer>>> apply()
    {   
        /*Tabla a rellenar usando DP. El valor trimatrix.get(i,j,l), donde i y j indican el nodo destino y el final respectivamente y l
        y l almacenará el peso del camino mas corto de i a j con exactamente k aristas */
        MapTriArray<Integer,Integer,Integer,Integer> trimatrix = new MapTriArray();
        ArrayList<Integer> visitados = new ArrayList<>(graph.size());
        GraphIterator<V> iter = (GraphIterator<V>) graph.breadthFirstSearchIterator(false);
        
        //Guardando en una lista las etiquetas de cada nodo del grafo para acceder a ellas de forma secuencial
        while (iter.hasNext())
        {
            iter.next();
            visitados.add(iter.getLabel());
        }
       
        for (int l = 0; l<=k;l++)
        {
            for (int i = 0;i<vertices;i++)
            {
                for (int j = 0;j<vertices;j++)
                {
                    GraphIterator<V> iterat = (GraphIterator<V>) graph.iterator(visitados.get(i));
                    GraphIterator<V> iterat2 = (GraphIterator<V>) graph.iterator(visitados.get(j));
                    int label1 = iterat.getLabel();
                    int label2 = iterat2.getLabel();
                    
                    //Inicializar valor, que al finalizar el algoritmo la distancia se mantenga con pesoMayor indica que no existe un camino entre esos nodos
                    trimatrix.put(iterat.getLabel(), iterat2.getLabel(), l, pesoMayor);
                    
                    //Casos bases
                    if(l==0 && i==j)
                    {
                        trimatrix.put(iterat.getLabel(), iterat2.getLabel(), l, 0);
                    }
                   
                    //En el caso de que la distancia sea 1, los nodos deben ser adyacentes
                    if(l==1 && graph.isVertexAdjacent(label1, label2))
                    {
                        /*Al ser un grafo dirigido primero compruebo si es dirigido de i a j para obtener la arista entre ellos, sino es que es dirigido de j a i, 
                        pero la arista y el peso sigue siendo la misma se realiza la comprobacion para no obtener un valor nulo*/
                        Edge edge = (Edge) iterat.getAdjacentEdge(label2)!= null?(Edge) iterat.getAdjacentEdge(label2):(Edge) iterat2.getAdjacentEdge(label1);
                        trimatrix.put(iterat.getLabel(), iterat2.getLabel(), l, (Integer) edge.getWeight().getValue());
                    }
                    
                    //En el caso de que haya mas de una arista de distancia entre los nodos
                    if(l>1)
                    {
                        for(int a = 0; a < vertices;a++)
                        {
                            GraphIterator<V> iterat3 = (GraphIterator<V>) graph.iterator(visitados.get(a));
                            
                            /*Debe existir una arista de i a a teniendo en cuenta que los nodos a i y j sean diferentes
                            y debe existir una distancia entre a y j*/
                            if(iterat.isAdjacent(iterat3) && i!=a && j!=a && trimatrix.get(iterat3.getLabel(), iterat2.getLabel(), l-1)!= pesoMayor)
                            {   
                                /*Se selecciona como distancia entre los nodosi y j el menor valor entre la distancia que ya se encuentra actualmente en la trimatrix y 
                                el valor hasta la arista anterior sumado a la distancia entre a e i*/
                                trimatrix.put(iterat.getLabel(), iterat2.getLabel(), l, Math.min (trimatrix.get(iterat.getLabel(), iterat2.getLabel(),l), (((Edge) iterat.getAdjacentEdge(iterat3.getLabel())!= null) ? (Integer) iterat.getAdjacentEdge(iterat3.getLabel()).getWeight().getValue(): (Integer) iterat3.getAdjacentEdge(iterat.getLabel()).getWeight().getValue()) + trimatrix.get(iterat3.getLabel(), iterat2.getLabel(), l-1)));
                            }
                        }
                    }
                }
            }
        }
        setResult( new Pair(trimatrix.get(iteratorNI.getLabel(),iteratorNF.getLabel(), k),new LinkedList<Integer>()));
        return this;
    }
 
}
