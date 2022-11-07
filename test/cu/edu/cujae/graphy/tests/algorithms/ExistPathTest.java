package cu.edu.cujae.graphy.tests.algorithms;

import java.util.List;

import cu.edu.cujae.graphy.algorithms.ExistPath;
import cu.edu.cujae.graphy.core.Graph;
import cu.edu.cujae.graphy.core.defaults.DefaultGraphBuilder;

public class ExistPathTest {
    public static void main(String[] args) {
        Graph<Integer> graph = new DefaultGraphBuilder<Integer>().buildGraph().directed(false).get();
        System.out.println(graph.isDirected());

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);
        graph.add(5);

        graph.connect(0, 1);
        graph.connect(1, 4);
        graph.connect(1, 2);
        graph.connect(2, 5);
        graph.connect(5, 4);
        graph.connect(4, 3);
        graph.connect(3, 0);

        ExistPath<Integer> algorithms = new ExistPath<>(graph, 1, 2);
        //List<Integer> list = algorithms.apply().get();
        
        //for(Integer element : list){
            //System.out.print(element + " => ");
        //}
    }
}
