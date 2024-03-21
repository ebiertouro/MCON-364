package hashing;
import java.util.*;

public class Map<K, V extends Comparable<V>> 
implements Iterable<MapEntryInterface>, HashMapInterface {

	private int capacity;
	
	private Array<LinkedList<MapEntryInterface>> map;
	
	private int usedCapacity = 0;
	
	private final double loadFactor;
	
	public Map(int capacity) {
		this.capacity = capacity;
		loadFactor = .75 * capacity;
		map = new Array<LinkedList<MapEntryInterface>>(capacity);
	}

	@Override
	public Iterator<MapEntryInterface> iterator() {
	    return new MyIterator();
	}

	private class MyIterator implements Iterator<MapEntryInterface> {
	    private Iterator<MapEntryInterface> entryIterator;

	    public MyIterator() {
	        LinkedList<MapEntryInterface> allEntries = getAllEntries();
	        entryIterator = allEntries.iterator();
	    }

	    @Override
	    public boolean hasNext() {
	        return entryIterator.hasNext();
	    }

	    @Override
	    public MapEntryInterface next() {
	        if (!hasNext()) throw new NoSuchElementException();
	        return entryIterator.next();
	    }
	}


	public LinkedList<MapEntryInterface> getAllEntries() {
	    LinkedList<MapEntryInterface> allEntries = new LinkedList<>();
	    for (LinkedList<MapEntryInterface> bucket : map) {
	        if (bucket != null) {
	            for (MapEntryInterface entry : bucket) {
	                allEntries.add(entry);
	            }
	        }
	    }
	    return allEntries;
	}

	@Override
	public void insert(MapEntryInterface mapEntry) {
	
		//precondition - this element does not already exist in the map
		
		usedCapacity++;
		
		if (usedCapacity >= loadFactor) {
			resizeArray();
		}
		
		int index = mapEntry.getIndex(capacity);
		
		if (map.get(index) == null) {
	        map.set(index, new LinkedList<MapEntryInterface>());
	    }
		
		map.get(index).add(mapEntry);
		
		
    }
	
	public int getListSize(LinkedList<MapEntryInterface> bucket) {
		return bucket.size();
	}
	
	private void resizeArray() {
	    int newCapacity = capacity * 2;
	    Array<LinkedList<MapEntryInterface>> newArray = new Array<LinkedList<MapEntryInterface>>(newCapacity);

	    for (int i = 0; i < capacity; i++) {
	        if (map.get(i) != null) {
	            for (MapEntryInterface entry : map.get(i)) {
	                int newIndex = entry.getIndex(newCapacity);
	                if (newArray.get(newIndex) == null) {
	                    newArray.set(newIndex, new LinkedList<MapEntryInterface>());
	                }
	                newArray.get(newIndex).add(entry);
	            }
	        }
	    }

	    map = newArray;
	    capacity = newCapacity;
	}


	@Override
	public int size() {
		return capacity;
	}
	
	@Override
	public boolean contains(MapEntryInterface mapEntry) {
	    LinkedList<MapEntryInterface> bucket = map.get(mapEntry.getIndex(capacity));
	    if (bucket == null || bucket.isEmpty())
	        return false;
	    
	    if (bucket.get(0).getKey().equals(mapEntry.getKey())) 
	        return true;
	    for (int i = 1; i < bucket.size(); i++) {
	        if (bucket.get(i).getKey().equals(mapEntry.getKey())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public int getBucketSize(MapEntryInterface mapEntry) {
		return map.get(mapEntry.getIndex(capacity)).size();
	}

	@Override
	public void remove(MapEntryInterface mapEntry) {
	    LinkedList<MapEntryInterface> bucket = map.get(mapEntry.getIndex(capacity));
	    if (bucket.isEmpty()) {
	        return; // Nothing to remove
	    }
	    if (bucket.get(0).getKey().equals(mapEntry.getKey())) {
	        bucket.remove(0);
	    } else {
	        for (int i = 1; i < bucket.size(); i++) {
	            if (bucket.get(i).getKey().equals(mapEntry.getKey())) {
	                bucket.remove(0);
	                break; // Exit loop after removing the element
	            }
	        }
	    }
	}

	@Override
	public MapEntryInterface get(MapEntryInterface mapEntry) {
			 LinkedList<MapEntryInterface> bucket = map.get(mapEntry.getIndex(capacity));
			    if (bucket.get(0).getKey().equals(mapEntry.getKey()))
			        return bucket.get(0);
			    else {
			    for (int i = 1; i < bucket.size(); i++) {
			        if (bucket.get(i).getKey().equals(mapEntry.getKey())) {
			           return bucket.get(i);
			        }
			    }
			    }
				return null;
	}
	
	@Override
	public String toString() {
	    StringBuilder result = new StringBuilder("This map consists of " + map.size() + " buckets.\n");
	    int totalValue = 0;
	    int nullBuckets = 0;
	    for (int i = 0; i < map.size(); i++) {
	    	LinkedList<MapEntryInterface> bucket = map.get(i);
	        if (bucket == null) 
	        	nullBuckets++;
	        else{	// Print out each slot and its length
	        	
	        	 if (bucket.size() == 1) 
		            	result.append("Slot ").append(i +1).append(": 1 word: ");
		            else
		            	result.append("Slot ").append(i +1).append(": " + bucket.size() + " words: ");
		            
	        	 
	            for (MapEntryInterface mapEntry : bucket) {
	                totalValue += (int) mapEntry.getValue();
	                result.append("\t" + mapEntry + " ");
	            
	            } 
	            result.append("\n");
	        }
	    }
	    result.append("Total words: " + totalValue + "\nUnused map slots: ").append(nullBuckets);
	    

	    /*
	     
o	How many buckets do you have using one hash function, vs the other ? 
How long are your chains with one hash function vs another (which is a measure of collisions)
	     */

	    return result.toString();
	}

}