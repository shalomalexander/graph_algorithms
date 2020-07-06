import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    static int inf = 100001;
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        int[] dist = new int[adj.length];
        Arrays.fill(dist, inf);

        for(int i = 0; i < adj.length; i++) {
            if(dist[i] == inf) {
                dist[i] = 0;
                isBipartite(adj, dist, i);
            }
        } 

        // for(int x : dist) {
        //     System.out.println(x);
        // }

        for(int i = 0; i < adj.length; i++) {
            for(int j = 0; j < adj[i].size(); j++) {
                if(dist[i] == dist[adj[i].get(j)]) return 0;
            }
        } 
        
        return 1;
    }

    private static void isBipartite(ArrayList<Integer>[] adj, int[] dist, int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int i = 0; i < adj[temp].size(); i++) {
                if(dist[adj[temp].get(i)] == inf) {
                    q.add(adj[temp].get(i));
                    dist[adj[temp].get(i)] = (dist[temp] + 1) % 2;
                } 
            }
        }

        // for(int x : dist) {
        //     System.out.println(x);
        // }


    }

    public static void main(String[] args) throws FileNotFoundException {
        //String file = "input3.txt";
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

