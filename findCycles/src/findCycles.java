/**
 * Created by yuqishi on 2/9/17.
 */

import java.util.*;
import java.io.*;
import java.lang.*;


public class findCycles {

    public static void findCycles(Map<Integer, Integer> graph, Map<Integer, ArrayList> inDegree){

        while(! graph.isEmpty()){
            LinkedList<Integer> cycle = new LinkedList<Integer>();
            ArrayList<Integer> realCycle = new ArrayList<Integer>();
            int cur = 0;
            for (Integer q: graph.values()){
                if (graph.containsKey(q)){
                    cur = q;
                    break;
                }
            }

            while (! cycle.contains(cur)){
                cycle.add(cur);
                //System.out.println(cycle);
                //System.out.println(cur);
                cur = graph.get(cur);
            }
            for (int j = cycle.indexOf(cur); j < cycle.size(); j++){
                realCycle.add(cycle.get(j));
                //System.out.println(realCycle);
            }

            int weight = 0;
            while (! cycle.isEmpty()){
                int node = cycle.pop();
                //System.out.println(cycle);
                //System.out.format("node: %d", node);
                //System.out.format("cycleSize: %d", cycle.size());
                weight += 1;

                if (graph.containsKey(node)) {
                    graph.remove(node);
                }
                    ArrayList<Integer> arr = inDegree.get(node);
                    //System.out.println(graph);
                    for (Integer x: arr){
                        if (graph.containsKey(x) && (! cycle.contains(x))){
                            //System.out.println(inDegree.get(node));
                            cycle.add(x);
                        }
                    }
                    inDegree.remove(node);
            }
            System.out.println(realCycle);
            System.out.format("Weight: %d     ", weight);
            //System.out.println("\n");
            System.out.format("Length: %d", realCycle.size());
            System.out.println("\n");

        }
    }


    public static void main(String[] args) {

        try {

            String pathname = "/Users/yuqishi/Desktop/Small.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            int size = Integer.parseInt(line);
            Map<Integer, Integer> graph_t = new HashMap<>();


            for (int i =0; i<size; i++){
                line = br.readLine();
                String[] parts = line.split(" ");
                String start = parts[0];
                String end = parts[1];
                graph_t.put(Integer.parseInt(start),Integer.parseInt(end));
            }
            Map<Integer, ArrayList > inDegree_t = new HashMap<>();

            for (int i=1; i<=size; i++){
                inDegree_t.put(i,new ArrayList<Integer>());
            }
            for (int i=1; i<=size; i++){
                int index = graph_t.get(i);
                inDegree_t.get(index).add(i);
            }

            findCycles(graph_t,inDegree_t);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



