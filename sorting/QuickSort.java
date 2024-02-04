package sorting;

public class QuickSort<T extends Comparable<? super T>> {
	
	private T[] array;
	
	public QuickSort(T[] arr) {

		System.out.print("\nWe will explain what is happening in our Quick Sort.\n"
				+ "First, we make a deep copy of the array.\n");
		this.array = arr.clone();
		int size = array.length;
		System.out.print("\nWe call a recursive method to sort.\n");
		sort(array, 0, size - 1);
	}

	public T[] sort(T[] array, int low, int high) {
		
		System.out.print("\nOur base case is when low is equal to high.\n");
	     if (low < high) {
	         int pivotIndex = partition(array, low, high);
			 System.out.print("\nWe do not sort the pivot - it is locked.\n");
	         sort(array, low, pivotIndex - 1);
	         sort(array, pivotIndex + 1, high);
	     }
	     return array;
	 }
	
	private int partition(T[] array, int low, int high) {
		
		System.out.print("\nWe set the pivot as the highest value in the current array.\n");
		
       T pivot = array[high];
	    
       System.out.print("\nWe set i as -1.\n"
       		+ "We can't swap with a value which doesn't exist in the array, but i is iterated up before swapping.\n");
	    int i = (low - 1); 
	    System.out.print("\nWe start at low, then iterate through the array until before the pivot.\n"
	    		+ "If current is greater than the pivot, we swap it with i. i is iterated up.\n"
	    		+ "This makes sure that everything less than pivot goes to the left.\n");
	    
	     for (int current = low; current <= high - 1; current++) {
	         if(array[current].compareTo(pivot) < 0) {
	        	i++;
	            swap(array, i, current); 
	         }	
	       }
	
	     System.out.print("\nWe swap i+1 with high, this makes sure pivot is in the right place.\n");
	     
	     swap(array, i + 1, high); 
	     
	   //return the index of the pivot after the swap
	   //this index will be used to partition the array in the next recursive calls
	     System.out.print("\nWe return the new pivot.\n");
	     return (i + 1);
	 }
	
	 private void swap(T[] array, int i, int j) {
	     T temp = array[i];
	     array[i] = array[j];
	     array[j] = temp;
	 }

	 public T[] getSortedArray() {
		 System.out.print("\nWe return the deep copy to main.\n");
		 return array;
	 }
}
