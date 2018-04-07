package hazardparking;

/**
 * Simple Graph with Limited functions
 * The Graph can not be modified my the user
 * @author Kunyuan Cao
 *
 */

public class Graph {
	/**
	 * The notes is represent by a class Note which contains name and index
	 * place is a array of notes which index is the index of object note
	 * V,E is vertice and edge number
	 */
	private Note[] place;
	private final int V,E;
	/**
	 * constructor
	 * @param v number of notes
	 * @param e number of edges
	 */
	public Graph(int v,int e)
	{
		V=v;
		E=e;
		place= new Note[V];
	}
	/**
	 * addnote
	 * @param n new notes added
	 */
	public void addPlace(Note n)
	{
		place[n.index()]=n;
		
	}
	/**
	 * add an edge
	 * @param i connected note index
	 * @param j connected note index
	 */
	public void addEdge(int i,int j)
	{
		place[i].addadj(place[j]);
		place[j].addadj(place[i]);
	}
	/**
	 * 
	 * @return number of notes
	 */
	public int V()
	{
		return V;
	}
	/**
	 * 
	 * @return number of edges
	 */
	public int E()
	{
		return E;
	}

}
