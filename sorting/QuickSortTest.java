package sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuickSortTest {
	

		Integer[] intArray = {15, 10, 100, 4};
		Integer[] presortedIntArray = {4, 10, 15, 100};
		Character[] charArray = {'d', 'k', 'a', 'z'};
		Character[] presortedCharArray = {'a', 'd', 'k', 'z'};

	@Test
	void test_with_characters() {
		QuickSort<Character> charSorter = new QuickSort<Character>(charArray);
		Character[] sortedChars = charSorter.getSortedArray();
		Assertions.assertArrayEquals(sortedChars, presortedCharArray);
		
	}
	
	@Test
	void test_with_integers() {
		QuickSort<Integer> intSorter = new QuickSort<Integer>(intArray);
		Integer[] sortedInts = intSorter.getSortedArray();
		Assertions.assertArrayEquals(sortedInts, presortedIntArray);
		
	}

}
