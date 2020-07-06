import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Reachability {

    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        // write your code here
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for(int i = 0; i < adj.length; i++) {
            visited.put(i, false);
        }
        return dfs(adj, x, y, visited);
    }

    private static int dfs(ArrayList<Integer>[] adj, int x, int y, HashMap<Integer, Boolean> visited) {
        if(visited.get(x)) return 0;
        if(x == y) return 1;
        //If the vertex has not been visited then decalre it as true ie. visited
        if(!visited.get(x))
            visited.put(x, true);

        int res = 0;    
        for(int i = 0; i < adj[x].size(); i++ ) {
            res = dfs(adj, adj[x].get(i), y, visited);
            if(res == 1) break;
        }   
        return res;
        
    }
    public static void main(String[] args) throws FileNotFoundException {
        //String file = "input1.txt" ;
        //String file = "input2.txt" ;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer> adj[] = new ArrayList[n];
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
        System.out.println(reach(adj, x, y));
  
    }
}

