package hashing;

public class NaiveHashFunction implements HashFunctionInterface{


	
	@Override
	public int hash(String string) {
		if (string.length() == 0)
			return 0;
		else
			return (int)string.charAt(0);
	}
	
	@Override
	public int hash(int Int) {
		return	Int % 100;
	}
	
	@Override
	public int  hash(char Char) {
		return (int) Char;
	}
	
	@Override
	public int hash(double dbl) {
	    return (int) dbl % 100;
	}
	
	@Override
	public int hash(long lng) {
	    return (int) lng % 100;
	}
}
