package hazardparking;

import java.util.List;
/**
 * create a list of strings of all path from start point to end point
 * @author Kunyuan Cao
 *
 */
public class DFS {
	/**
	 * 
	 * @param g graph
	 * @param path empty list to store string of paths
	 * @param start startpoint
	 * @param fin endpoint
	 */
	public static void path(Graph g,List<String> path,Note start,Note fin)
	{
		String p="";
		boolean[] mark= new boolean[g.V()];
		for(int i=0;i<g.V();i++)
		{mark[i]=false;}
		dfs(g,path,p,mark,start,fin);
	}
	/**
	 * Recrusion to search all possible paths 
	 * @param g graph
	 * @param path list to store string of paths
	 * @param p the string of paths
	 * @param mark the array of boolean to store if a place is visited
	 * @param start current start point
	 * @param fin  end point
	 */
	private static void dfs(Graph g,List<String> path,String p,boolean[] mark,Note start,Note fin)
	{
		mark[start.index()]=true;
		p=p+start.name()+" ";
		if(start.name().equals(fin.name()))
		{
			path.add(p);
		}
		else
		{
			for(int i=0;i<start.adj().size();i++)
			{
				if(!mark[start.adj().get(i).index()])
				{
					String s1=p;
					dfs(g,path,s1,mark,start.adj().get(i),fin);
				}
			}
		}
		mark[start.index()]=false;
		
	}

}
