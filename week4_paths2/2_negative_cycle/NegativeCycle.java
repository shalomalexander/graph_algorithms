import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class NegativeCycle {
    static int inf = Integer.MAX_VALUE;
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        Integer[] dist = new Integer[adj.length];
        Integer[] prev = new Integer[adj.length];

        Arrays.fill(dist, inf);
        //dist[0] = 0;
        Arrays.fill(prev, null);

        // int lastRelaxed = -1;

        for(int p = 0; p < adj.length; p++) {
            for(int i = 0; i < adj.length; i++) {
                if(prev[i] == null) dist[i] = 0;
                for(int j = 0;  j  < adj[i].size(); j++) {
                    if(dist[adj[i].get(j)] > dist[i] + cost[i].get(j) && dist[i] != inf) {
                        dist[adj[i].get(j)] = dist[i] + cost[i].get(j);
                        prev[adj[i].get(j)] = i;
                        if(p == adj.length - 1) return 1;
                    }
                }
                // System.out.println("iteration: "+p+" ");
                // for(int x = 0; x < dist.length; x++)
                //   System.out.print(dist[x]+" ");
                // System.out.println();  
            }
        }
        
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //String file = "input1.txt";
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new FileReader(file));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

