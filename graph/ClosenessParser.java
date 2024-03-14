package graphing;

public class ClosenessParser implements Parser{
	
	//i need to implement relationship weights as ints so i can 
	//add and/or compare weight, but i want an easy way to print their value
	
	final static int NORELATIONSHIP = 0;
	final static int NEUTRAL = 5;
	final static int FRIEND = 10;
	final static int CLOSEFRIEND = 15;
	final static int BFF = 20;
	
	@Override
	public String parse(int closeness) {
		
		if (closeness == NORELATIONSHIP) {
			return "no rel.";
		}
		else if (closeness == NEUTRAL) 
			return "neutral";
		else if (closeness == FRIEND)
			return "friend";
		else if (closeness == CLOSEFRIEND)
			return "close friend";
		else
			return "BFF";
	}

}
