package cu.edu.cujae.graphy.tests;

import cu.edu.cujae.graphy.core.DefaultGraphBuilder;
import cu.edu.cujae.graphy.core.Graph;

public class LocalTests {
    public static void main(String[] args) {
        Graph<Integer> graph = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();

        System.out.println(graph.isDirected());

        int testSize = 10;

        for(int i = 0; i < testSize; i++){
            graph.add(i);
        }

        for(int i = 0, j = 1; i < testSize; i++, j++){
            if(j >= testSize){
                j = 0;
            }
            graph.connect(i, j);
        }

        System.out.println(graph.toString());
    }
}
