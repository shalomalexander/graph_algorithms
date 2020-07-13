import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.Scanner;


public class ConnectingPoints {
    static class Edge {
        int u , v;
        double cost;

        Edge(int u, int v, double cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

    }
    public static class Sortbycost implements Comparator<Edge> 
        { 
            // Used for sorting in ascending order of 
            // roll number 
            public int compare(Edge a, Edge b) 
            {   if(a.cost - b.cost > 0)
                    return 1;
                else if(a.cost - b.cost < 0)
                    return -1;
                else return 0;         
            }   
        } 

    private static double minimumDistance(int[] x, int[] y) {
         DecimalFormat df6 = new DecimalFormat("#.#######");
        double result = 0.;
        // write your code here
        double[][] costs = new double[x.length][x.length];
        ArrayList<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < y.length; j++) {
                double c = Math.sqrt(Math.pow((x[i] - x[j]), 2) + Math.pow((y[i] - y[j]), 2));
                //System.out.println(i+" "+j+" "+c);
                Edge edge = new Edge(i, j, c);
                edgeList.add(edge);
            }
        }
        //System.out.println("---------------------------");

        Collections.sort(edgeList, new Sortbycost());
        for(Edge e: edgeList) {
            System.out.println(e.u+" "+e.v+" "+e.cost);
        }
        int[] set = new int[x.length];
        for(int i = 0; i < x.length; i++) {
            set[i] = i;
        }
        int count = 0;
        for(Edge e : edgeList) {
            if(set[e.u]!=set[e.v]) {
                union(e.u,e.v,set);
                result+=e.cost;
                count++;
            }
            if(count == set.length) break;
        }
        result = Double.parseDouble(df6.format(result));
        

        return result;
    }

    public static void union(int u, int v, int[] set) {
        int temp = set[u];
        for(int i = 0; i < set.length; i++) {
            if(temp == set[i]) {
                set[i] = set[v];
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String file = "input2.txt";
         //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new FileReader(file));
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}
