package hazardparking;

import java.util.List;
import java.util.ArrayList;

public class FindPath {
	
	
	
	public static String findPath() throws Exception{
		ExtractData.init();
		Entry[] pathpoints = ExtractData.getData();
		
		ArrayList<Note> ticketlist = new ArrayList<Note>();
		int counter = 0;
		
		// Initialze nodes from some entries in the data
		for (int i = 0; i < 90; i = i+3)
		{
			ticketlist.add(new Note(pathpoints[i].getStreet(),counter, pathpoints[i].getX(), pathpoints[i].getY()));
			counter++;
		}
		
		Graph ticketmap = new Graph(30,0);
		
		for (int j = 0; j < 30; j++)
		{
			ticketmap.addPlace(ticketlist.get(j));
		}
		
		
		// Adding edges based on graph
		ticketmap.addEdge(0, 1);
		ticketmap.addEdge(1, 2);
		ticketmap.addEdge(2, 4);
		ticketmap.addEdge(3, 6);
		ticketmap.addEdge(4, 5);
		ticketmap.addEdge(5, 7);
		ticketmap.addEdge(6, 12);
		ticketmap.addEdge(7, 9);
		ticketmap.addEdge(8, 9);
		ticketmap.addEdge(2, 9);
		ticketmap.addEdge(10, 11);
		ticketmap.addEdge(12, 21);
		ticketmap.addEdge(9, 15);
		ticketmap.addEdge(3, 13);
		ticketmap.addEdge(15, 16);
		ticketmap.addEdge(15, 17);
		ticketmap.addEdge(15, 18);
		ticketmap.addEdge(18, 21);
		ticketmap.addEdge(19, 20);
		ticketmap.addEdge(21, 23);
		ticketmap.addEdge(23, 24);
		ticketmap.addEdge(24, 28);
		ticketmap.addEdge(28, 29);
		
		
		
		System.out.println(ticketmap.E());
		
		
		DFS route = new DFS(ticketmap ,0);
		
		ArrayList<Integer> introute = route.pathTo(29);
		
		String stringroute = "";
		
		for (int i = 0; i < introute.size(); i++)
		{
			if (i == introute.size() - 1)
			{
				stringroute+=ticketlist.get(introute.get(i)).name();
			}
			else{
				stringroute+=ticketlist.get(introute.get(i)).name() + ", ";
			}
		}
		
		return stringroute;
	}
	
	public static void main(String[] args) throws Exception {
		findPath();
	}
	


}
