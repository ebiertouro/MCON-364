package hashing;

public class DummyHash implements HashFunctionInterface{


	@Override
	public int hash(String string) {
		if (string.equals("bye"))
			return 1;
		else
			return 0;
	}

	@Override
	public int hash(int Int) {
		return 0;
	}

	@Override
	public int hash(double doubl) {
		return 0;
	}

	@Override
	public int hash(long lng) {
		return 0;
	}

	@Override
	public int hash(char Char) {
		return 0;
	}

}
