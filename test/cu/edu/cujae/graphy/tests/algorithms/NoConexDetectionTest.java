package cu.edu.cujae.graphy.tests.algorithms;


import cu.edu.cujae.graphy.algorithms.NoConexDetection;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.utility.GraphBuilders;

public class NoConexDetectionTest {

	public static void main(String[] args) {
		Graph<Integer> graphNoConex = GraphBuilders.makeSimpleGraph(false);

        for (int i = 0; i < 5; i++)
        {
            graphNoConex.add(i);
        }
        /*conecto los nodos 4-1-3-2-4 los otros nodos no se conectan y debe dar true*/
        graphNoConex.connect(4, 1);
        graphNoConex.connect(1, 3);
        graphNoConex.connect(3, 2);
        graphNoConex.connect(2, 4);
        
        
        System.out.println("El grafo es no conexo? : " + (new NoConexDetection<>(graphNoConex).apply().get() ));
        
        
        Graph<Integer> graphConex = GraphBuilders.makeSimpleGraph(false);
        for (int j = 0; j < 4; j++)
        {
        	graphConex.add(j);
        }
        /*conecto todos los nodos 4-1-3-2-4 debe dar false*/
        graphConex.connect(0, 1);
        graphConex.connect(1, 2);
        graphConex.connect(2, 3);
       System.out.println("El grafo es no conexo? : " + (new NoConexDetection<>(graphConex).apply().get() ));
        

	}

}
