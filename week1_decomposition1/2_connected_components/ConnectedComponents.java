import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        // write your code here
        if(adj.length == 0) return result;

        HashMap<Integer, Boolean> visited = new HashMap<>();

        for(int i = 0; i < adj.length; i++) {
            visited.put(i, false);
        }
        
        for(int i = 0; i < adj.length; i++) {
            if(!visited.get(i)) {
                dfs(adj, i, visited);
                result++;
            }

        }
        
        return result;
    }

    public static void dfs(ArrayList<Integer>[] adj, int curr, HashMap<Integer, Boolean> visited) {
        if(visited.get(curr)) return;
         
        visited.put(curr, true);

        for(int i = 0; i < adj[curr].size(); i++) {
            dfs(adj, adj[curr].get(i), visited);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //String file = "input1.txt";
        //String file = "input2.txt";
        //String file = "input3.txt";
        //String file = "input4.txt";
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
        System.out.println(numberOfComponents(adj));
    }
}

