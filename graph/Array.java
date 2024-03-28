package hashing;
import java.util.*;

public class Array<T> implements Iterable<T>{
	
	private T[] genericArray;
	
	@SuppressWarnings("unchecked")
	public Array (int size) {
		this.genericArray = (T[]) new Object[size];
	}
	
	public T get(int index) {
		return (T) genericArray[index];
	}

	public void set(int index, T value) {
		this.genericArray[index] = value;
	}
	
	public int size() {
		return this.genericArray.length;
	}
	
	public boolean isEmpty() {
	boolean isEmpty;
	if (this.genericArray.length == 0)
		isEmpty = true;
	
	else
		isEmpty = false;
	
	return isEmpty;
	}
	

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < genericArray.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return genericArray[currentIndex++];
        }
    }
}