import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        // write your code here
        boolean[] visited = new boolean[adj.length];
        Arrays.fill(visited, false);
        //System.out.println(visited[0]+" "+visited[1]+" "+visited[2]+" "+visited[3]);
        Stack<Integer> stack = new Stack<>();
        //Filling the stack with DFS by pushing the sinks into the stack
        for(int i = 0; i < adj.length; i++) {
            if(!visited[i]) {  
                fillStack(stack, adj, visited, i);
            }
        }

        //filling the visited array with false        
        Arrays.fill(visited, false);
        
        //created the reversGraph or transpose of the Graph
        ArrayList<Integer>[] adjRev = new ArrayList[adj.length];
        createReverseGraph(adj, adjRev); //O(V + E)

        int scc = 0;

        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(!visited[node]){
                dfs(adjRev, visited, node);
                scc++;
            } 
        }
                
        return scc;
    }

    private static void dfs(ArrayList<Integer>[] adjRev, boolean[] visited, int node) {
        visited[node] =  true;

        for(int i = 0; i < adjRev[node].size(); i++) {
            if(!visited[adjRev[node].get(i)]) {
                dfs(adjRev, visited, adjRev[node].get(i));
            }
        }
    }

    private static void fillStack(Stack<Integer> stack, ArrayList<Integer>[] adj, boolean[] visited, int curr) {
        visited[curr] = true;
        for(int i = 0; i < adj[curr].size(); i++ ) {
            if(!visited[adj[curr].get(i)]) {
                fillStack(stack, adj, visited, adj[curr].get(i));
            }
        }
        stack.push(curr);
    }

    public static void createReverseGraph(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjRev) {
        for(int i = 0; i < adj.length; i++ ) {
            adjRev[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < adj.length; i++) {
            for(int j = 0; j < adj[i].size(); j++) {
                adjRev[adj[i].get(j)].add(i);
            }
        }
    } 

    public static void main(String[] args) throws FileNotFoundException {
        //String file  = "input2.txt";
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

