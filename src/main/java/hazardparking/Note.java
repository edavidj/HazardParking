package hazardparking;

import java.util.LinkedList;
import java.util.List;
/**
 * Class Note which stands for places in the city
 * @author Kunyuan Cao
 *
 */
public class Note {
	private String name;
	private int index;
	private double x,y;
	private List<Note> note= new LinkedList<Note>();
	/**
	 * constructor
	 * @param name name of places
	 * @param index index of places which is used to relief the effort of searching
	 * @param x latitude
	 * @param y lotitude
	 */
	public Note(String name, int index,double x, double y)
	{
		this.x=x;
		this.y=y;
		this.name=name;
		this.index=index;
	}
	/**
	 * 
	 * @return name of place
	 */
	public String name()
	{
		return name;
	}
	/**
	 * 
	 * @return index of places
	 */
	public int index()
	{
		return index;
	}
	/**
	 * 
	 * @return x
	 */
	public double x()
	{
		return x;
	}
	/**
	 * 
	 * @return y
	 */
	public double y()
	{
		return y;
	}
	/**
	 * add adj places
	 * @param n the note adj to this one
	 */
	public void addadj(Note n)
	{
		note.add(n);
	}
	public List<Note> adj(){
		return note;
	}

}
