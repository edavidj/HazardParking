package hazardparking;

import java.util.ArrayList;
import java.util.Stack;

//Based on implementation by algs4 Princeton website

public class DFS {
	
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public DFS(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v)
	{
		marked[v] = true;
		for (int w : G.adj(v))
		{
			if (!marked[w])
			{
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	public ArrayList<Integer> pathTo(int v) {
        Stack<Integer> path = new Stack<Integer>();
        ArrayList<Integer> intpath = new ArrayList<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
        	path.push(x);
        }
        path.push(s);
        while (!path.isEmpty()) {
        	intpath.add(path.pop());
        }
        return intpath;
    }

}
