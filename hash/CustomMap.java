package hashing;
import java.util.*;

public class CustomMap<K, V extends Comparable<V>>  implements Iterable<MapEntryInterface>, HashMapInterface {

	private int capacity;
	
	private Array<LinkedList<MapEntryInterface>> map;
	//our map consists of a custom array of custom linked lists, which hold map entries
	
	private int usedCapacity = 0;
	
	private final double loadFactor;
	//when we hit the loadfactor, we resize the map 
	
	public CustomMap(int capacity) {
		this.capacity = capacity;
		loadFactor = .75 * capacity;
		map = new Array<LinkedList<MapEntryInterface>>(capacity);
	}

	@Override
	
	 // a double iterator which iterates over each linked list in each array
	//it works by putting each element into one big linked list
	 
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


	private LinkedList<MapEntryInterface> getAllEntries() {
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
		
		//check if we have to resize
		if (usedCapacity >= loadFactor) {
			resizeArray();
		}
		
		int index = mapEntry.getIndex(capacity);
		
		//if there is not yet a linked list in that array index, we create one containing our entry
		if (map.get(index) == null) {
	        map.set(index, new LinkedList<MapEntryInterface>());
	    }
		
		//if a linked list exists, we append our entry
		map.get(index).add(mapEntry);	
    }
	
	private void resizeArray() {
		
		//we create a new map and copy
		
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
	    
	    //we check the index - if null, no entry with that hashcode was inserted
	    if (bucket == null || bucket.isEmpty())
	        return false;
	    
	    //then we walk down the linked list to find the entry
	    
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
		
		//first check if an entry with that hashcode was inserted
		
	    LinkedList<MapEntryInterface> bucket = map.get(mapEntry.getIndex(capacity));
	    if (bucket.isEmpty()) {
	        return; // Nothing to remove
	    }
	    
	    //we walk down the linked list. if found, we remove. otherwise, we exit
	    
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
		
	// we return the map entry that already exists in the map if it matches the new one
		
		//we look for it. if not found, we return null
		
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
		
		 /*
		  * a grand state-of-the-union report about our map
		  * we print out every non-null bucket, as well as info about how many buckets are used
		  * it shows a clear visual of average chain length
		 */
		
	    StringBuilder result = new StringBuilder("This map consists of " + map.size() + " buckets.\n");
	    int totalValue = 0;
	    int nullBuckets = 0;
	    for (int i = 0; i < map.size(); i++) {
	    	LinkedList<MapEntryInterface> bucket = map.get(i);
	        if (bucket == null) 
	        	nullBuckets++;
	        else{	// Print out each slot and its length
	        	
	        	 if (bucket.size() == 1) 
		            	result.append("Slot ").append(i +1).append(": 1 entry: ");
		            else
		            	result.append("Slot ").append(i +1).append(": " + bucket.size() + " entries: ");
		            
	        	 
	            for (MapEntryInterface mapEntry : bucket) {
	                totalValue += (int) mapEntry.getValue();
	                result.append("\t" + mapEntry + " ");
	            
	            } 
	            result.append("\n");
	        }
	    }
	    result.append("Total entries: " + totalValue + "\nUnused map slots: ").append(nullBuckets);

	    return result.toString();
	}

}
