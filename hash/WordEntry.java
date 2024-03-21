package hashing;

public class WordEntry implements MapEntryInterface  
{
	
	private String key;
	private int value = 1;
	private int hashcode;
	private int mapLocation;
	private HashFunctionInterface hashFunction;
	
	public WordEntry(String key, HashFunctionInterface hashFunction) {
		this.key = key;
		this.hashFunction = hashFunction;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getIndex(int mapSize) {
		hash();
		compress(mapSize);
		return mapLocation;
	}

	@Override
	public void hash() {
		this.hashcode = hashFunction.hash(key);
	}

	@Override
	public int getHashcode() {
		return hashcode;
	}

	@Override
	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}

	@Override
	public void compress(int mapsize) {
		this.mapLocation = hashcode % mapsize;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}
	
	public void incrementValue() {
		value++;
		
	}
	
	@Override
	public String toString() {
		return "Key: " + key + ", Value: " + value;
	}

	@Override
	public int compareTo(MapEntryInterface mapEntry) {
	    // Compare the values of the MapEntries
	    if (value > mapEntry.getValue()) {
	        return -1;
	    } else if (value < mapEntry.getValue()) {
	        return 1;
	    } else {
	        return 0; // Values are equal
	    }
	}


}
