package sorting;

public class MergeSort<T extends Comparable<? super T>> {
	
    private int SIZE = 0;
    
   private T[] array;
	
	public MergeSort(T[] arr) {
		System.out.print("\nWe will explain what is happening in our Merge Sort.\n"
				+ "First, we make a deep copy of the array.\n");
		this.array = arr.clone();
		sort(array);
	}
	
    private void sort(T[] array)  {
        this.SIZE = array.length;

		System.out.print("\nThen, we call a recursive method to split the array.\n");
        split(0, SIZE -1);
    }

    public void split(int firstIndex, int lastIndex) {


		System.out.print("\nThe base case is that the first index is smaller than the last index.\n"
				+ "Meaning, we split until there is one item in each list.\n");
        if (firstIndex < lastIndex) {

            int midPoint = (lastIndex + firstIndex) / 2;
            

    		System.out.print("\nWe split the right and left halves of the list again.\n");
    		
            split(firstIndex, midPoint);
            split(midPoint + 1, lastIndex); 


    		System.out.print("\nWhen we hit the base case, we start merging our lists together.\n");
            mergeTogether(firstIndex, midPoint, midPoint +1, lastIndex);
        }
    }

   /*
        Lists are merged together.
        We start with the easiest case of 2 lists of one cell each..
        (this calling of this happens in the recursive function MergeSortSplit)
        a Temporary array is used to store our list, in the end it is copied back to this.arrayToSort.
        We receive indicies and we work on the list arrayToSort.
        leftFirst = first index of left array
        leftLast = last index of left array
        rightFirst = first index of right array
        rightLast = last index of right array
    */
    public void mergeTogether(int leftFirst, int leftLast, int rightFirst, int rightLast){
    
        int tempPosition = leftFirst;
        System.out.print("\nWe create a temporary array which will hold "
        		+ "each value from our merged arrays as we sort them.\n");
        @SuppressWarnings("unchecked")
		T[] tempArray = (T[]) new Comparable[rightLast + 1];
        int startLocation = leftFirst ;

        //while neither list exceeds its bounds)
        System.out.print("\nWe compare the values from the mini lists and place them in the temp list.\n"); 
        
        while(leftFirst <= leftLast && rightFirst <= rightLast){   
            if (this.array[leftFirst].compareTo(this.array[rightFirst]) < 0) {  
                tempArray[tempPosition++] =  this.array[leftFirst++];
            }
            else {
                tempArray[tempPosition++] =  this.array[rightFirst++];
            }
        }
        //fill the rest of the list with whatever is left over from left list
        while(leftFirst <= leftLast){
            tempArray[tempPosition++] =  this.array[leftFirst++];
        }
        //fill the rest of the list with whatever is left over from right list
        while(rightFirst <= rightLast){
            tempArray[tempPosition++] =  this.array[rightFirst++];
        }
        //copy temp list into arrayToSort - and you're done.
        System.out.print("\nFinally, we copy the temporary array into our array.\n");
        for(tempPosition= startLocation;  tempPosition <= rightLast; tempPosition++){
            this.array[tempPosition] =  tempArray[tempPosition];
        }
        
    }
    
   	 public T[] getSortedArray() {
   		 System.out.print("\nWe return the deep copy to main.\n");
		 return array;
	 }
}
