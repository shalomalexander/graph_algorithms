import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Dijkstra {

    static int inf = Integer.MAX_VALUE;

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        Integer[] prev = new Integer[adj.length];

        Arrays.fill(dist, inf);
        dist[s] = 0;
        Arrays.fill(prev, null);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x , y) -> dist[x] - dist[y]);
        pq.add(s);
       
        while(!pq.isEmpty()) {
            int min = pq.poll();
            for(int i = 0; i < adj[min].size(); i++) {
                if(dist[adj[min].get(i)] > dist[min] + cost[min].get(i)) {
                    dist[adj[min].get(i)] = dist[min] + cost[min].get(i);
                    prev[adj[min].get(i)] = min;
                    pq.add(adj[min].get(i));
                    changePriority(pq, dist);
                }
            }
        }

        if(dist[t] == inf) return -1;
      
        return dist[t];
    }

    private static void changePriority(PriorityQueue<Integer> pq, int[] dist) {
        List<Integer> pqlist = new ArrayList<>();
        while(!pq.isEmpty()) {
            int n = pq.poll();
            pqlist.add(n);
        }

        for(int x : pqlist) {
            pq.add(x);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //String file = "input2.txt";
        Scanner scanner = new Scanner(System.in);
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
        int s = scanner.nextInt() - 1;
        int t = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, s, t));
    }
}

