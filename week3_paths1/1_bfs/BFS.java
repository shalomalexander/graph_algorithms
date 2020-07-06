import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    static int inf = 100001;

    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        // write your code here
        int[] dist = new int[adj.length];
        Arrays.fill(dist, inf);

        dist[s] = 0;
        int shortestPath = bfs(adj, s, t, dist);
        // for(int x : dist) 
        //     System.out.println(x);

        if(shortestPath == inf) return -1;

        return shortestPath;
    }

    private static int bfs(ArrayList<Integer>[] adj, int s, int t, int[] dist) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int i = 0; i < adj[temp].size(); i++) {
                if(dist[adj[temp].get(i)] == inf) {
                    q.add(adj[temp].get(i));
                    dist[adj[temp].get(i)] = dist[temp] + 1;
                }
            }
        }

        return dist[t];

    }

    public static void main(String[] args) throws FileNotFoundException {
        //String file = "input2.txt";
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

