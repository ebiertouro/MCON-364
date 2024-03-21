package hashing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapTests {
	
	 Map<String, Integer> testMap = new Map<String, Integer>(3);
	 HashFunctionInterface function = new DummyHash();
     WordEntry word1 = new WordEntry("hello", function);
     WordEntry word2 = new WordEntry("hi", function);
     WordEntry word3 = new WordEntry("goodbye", function);
     WordEntry word4 = new WordEntry("bye", function);
	

	@BeforeEach
	void setUp() {
		testMap.insert(word1);
		testMap.insert(word2);
		word4.setHashcode(2);
	}

	@Test
	void test1_contains_checking_first_element_in_array_list() {
		Assertions.assertTrue(testMap.contains(word1));
	}

	@Test
	void test2_contains_checking_other_element_in_array_list() {
		Assertions.assertTrue(testMap.contains(word2));
	}
	
	@Test
	void test3_contains_when_not_in_map() {
		Assertions.assertFalse(testMap.contains(word3));
	}
	
	@Test
	void test4_remove() {
		testMap.remove(word1);
		Assertions.assertFalse(testMap.contains(word1));
	}
	
	@Test
	void test5_insert_into_same_bucket() {
		int bucketSizeBefore = testMap.getBucketSize(word1);
		testMap.insert(word3);
		int bucketSizeAfter = testMap.getBucketSize(word1);
		int bucketGrew = bucketSizeAfter - bucketSizeBefore;
		Assertions.assertEquals(bucketGrew, 1);
		Assertions.assertTrue(testMap.contains(word3));
		
	}
	
	@Test
	void test6_insert_into_different_bucket() {
		int bucketSizeBefore = testMap.getBucketSize(word1);
		testMap.insert(word4);
		int bucketSizeAfter = testMap.getBucketSize(word1);
		int bucketGrew = bucketSizeAfter - bucketSizeBefore;
		Assertions.assertEquals(bucketGrew, 0);
		Assertions.assertTrue(testMap.contains(word4));
	}
}
