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
package cu.edu.cujae.graphy.tests.algorithms;

import cu.edu.cujae.graphy.algorithms.EulerianCycleDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

/**
 * Eulerian cycle test.
 *
 * @author César Fernández
 */
public class EulerianCycleDetectionTest
{

	public static void main(String[] args)
	{
		Graph<Integer> graphNoConex = GraphBuilders.makeSimpleGraph(false);


		for (int j = 0; j < 5; j++)
		{
			graphNoConex.add(j);
		}
		/*conecto los nodos 4-1-3-2-4 los otros nodos no se conectan y debe dar false*/
		graphNoConex.connect(4, 1);
		graphNoConex.connect(1, 3);
		graphNoConex.connect(3, 2);
		graphNoConex.connect(2, 4);

		System.out.println("El grafo no conexo es un ciclo Euleriano ?: " + (new EulerianCycleDetection<>(graphNoConex).apply().get()));


		Graph<Integer> graphConex = GraphBuilders.makeSimpleGraph(false);


		for (int i = 0; i < 4; i++)
		{
			graphConex.add(i);
		}
		//se cumplen todas las condiciones necesarias conecto 0-1-3-2-0
		graphConex.connect(0, 1);
		graphConex.connect(1, 3);
		graphConex.connect(3, 2);
		graphConex.connect(2, 0);



		System.out.println("El grafo conexo es un ciclo Euleriano con todos los nodos de grado par?: " + (new EulerianCycleDetection<>(graphConex).apply().get()));
		/////////////////////////////////////////////////////////////////////////
		Graph<Integer> graphConexOdd = GraphBuilders.makeSimpleGraph(false);


		for (int i = 0; i < 4; i++)
		{
			graphConexOdd.add(i);
		}
		// conecto 0-1-3-2 con 0 con grado impar
		graphConexOdd.connect(0, 1);
		graphConexOdd.connect(1, 3);
		graphConexOdd.connect(3, 2);




		System.out.println("El grafo conexo es un ciclo Euleriano ?: " + (new EulerianCycleDetection<>(graphConexOdd).apply().get()));
	}
}
