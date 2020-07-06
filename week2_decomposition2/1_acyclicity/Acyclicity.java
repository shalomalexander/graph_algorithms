import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Acyclicity {

    private static int acyclic(ArrayList<Integer>[] adj) {
        // write your code here
       boolean[] visited = new boolean[adj.length];
       boolean[] recStack = new boolean[adj.length];
       
       for(int i = 0; i < adj.length; i++) {
           if(dfs(adj, visited, recStack, i))
               return 1;
        }
        
       return 0;
    }

    private static boolean dfs(ArrayList<Integer>[] adj, boolean[] visited, boolean[] recStack, int curr) {
        
        if(recStack[curr]) return true; 
        if(visited[curr]) return false;
        
        visited[curr] = true;
        recStack[curr] = true;

        for(int i = 0; i < adj[curr].size(); i++) {
            if(dfs(adj, visited, recStack, adj[curr].get(i)))
                return true;
        }
        recStack[curr] = false;

        return false;
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
        }
        System.out.println(acyclic(adj));
    }
}

