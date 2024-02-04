package sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeSortTest {

	Integer[] intArray = {15, 10, 100, 4};
	Integer[] presortedIntArray = {4, 10, 15, 100};
	Character[] charArray = {'d', 'k', 'a', 'z'};
	Character[] presortedCharArray = {'a', 'd', 'k', 'z'};

	@Test
	void test_with_characters() {
		MergeSort<Character> charSorter = new MergeSort<Character>(charArray);
		Character[] sortedChars = charSorter.getSortedArray();
		Assertions.assertArrayEquals(sortedChars, presortedCharArray);
	
	}

	@Test
	void test_with_integers() {
		MergeSort<Integer> intSorter = new MergeSort<Integer>(intArray);
		Integer[] sortedInts = intSorter.getSortedArray();
		Assertions.assertArrayEquals(sortedInts, presortedIntArray);
	
	}

}
