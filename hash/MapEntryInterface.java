package hashing;

interface MapEntryInterface extends Comparable<MapEntryInterface>{

	String getKey();
	
	int getValue();
	
	int getIndex(int mapsize);
	
	int getHashcode();
	
	void setHashcode(int hashcode);
	
	void compress(int mapsize);
	
	void hash();
	
	void setKey(String key);
	
	@Override
	String toString();

	@Override
	int compareTo(MapEntryInterface mapEntry);
	
}
