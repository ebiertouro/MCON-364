package graphing;

public class Relationship implements Comparable<Relationship>
//this is a modified Flight class for relationships between two people
{
	protected String fromVertex;
	protected String toVertex;
	protected int howClose;

	public Relationship(String fromVertex, String toVertex, int howClose)
{
	this.fromVertex = fromVertex;
 	this.toVertex = toVertex;
 	this.howClose = howClose;
}

	public String getFromVertex()
{
		return fromVertex;
}

	public String getToVertex()
{
		return toVertex;
}

	public int getHowClose()
{
		return howClose;
}

	public void setFromVertex(String vertex)
{
		fromVertex = vertex;
}

public void setToVertex(String vertex)
{
 toVertex = vertex;
}

public void setHowClose(int howClose)
{
 this.howClose = howClose;
}

public int compareTo(Relationship other)
{
 return (this.howClose - other.getHowClose());
}

@Override
public String toString()
{
 return (fromVertex + "    " + toVertex + "    " + howClose);
}
}



