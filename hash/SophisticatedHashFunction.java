package hashing;

public class SophisticatedHashFunction implements HashFunctionInterface{	
	
	@Override
	public int hash(String string) {
		//DJB2
	    int hash = 5381;

	    for (int i = 0; i < string.length(); i++) {
	        hash = ((hash << 5) + hash) + string.charAt(i); /* hash * 33 + c */
	    }

	    return Math.abs(hash);
	}

	
	@Override
	public int hash(int Int) {
		return Int;
	}
	
	@Override
	public int  hash(char Char) {
		return (int) Char;
	}
	
	@Override
	public int hash(double dbl) {
	    long bits = Double.doubleToLongBits(dbl);
	    return (int) (bits ^ (bits >>> 32));
	}
	
	@Override
	public int hash(long lng) {
	    return (int) (lng ^ (lng >>> 32));
	}
}
