package hashing;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MapExample {
	
	final static int MAPSIZE = 500;
	
	 public static void main(String[] args) {
		 
		 Scanner input = new Scanner(System.in);
		 

		 
		 System.out.println("Welcome! This program reads a file and creates a map of its word frequency.");
		 
		 System.out.println("To create a map, you need to choose your prefered hash function: ");
		 System.out.println("1 - naive\n2 - sophisticated");
		 
		 int hashChoice = chooseFromMenu(input, 0, 2, -1);
		 
		 HashFunctionInterface hashFunction;
		 
		 if (hashChoice == 0)
			 hashFunction = new NaiveHashFunction();
		 else //if (hashChoice == 1)
			 hashFunction = new SophisticatedHashFunction();
		 
		Map<String, Integer> map = readFile(hashFunction, input);
			
		 System.out.println("Your map has been created! Choose what to do with it: ");
		 System.out.println("1 - View the word count for a particular word"
		 		+ " and how many words share its map location.");
		 System.out.println("2 - View words in descending order according to word count.");
		 System.out.println("3 - View a report on the internal structure of the map.");
		 System.out.println("4 - Get a report as you insert new entries into the map.");
		 
		 int actionChoice = chooseFromMenu(input, 0, 4, -1);
		 
		 if (actionChoice == 0)
			 wordCount(map, input, hashFunction);
		 else if (actionChoice == 1)
			 printMap(map);
		 else if (actionChoice ==2)
			 mapReport(map);
		 else // if (actionChoice ==3)
			 insertionReport(map, input, hashFunction);
		 
		 System.out.println("Thank you for using our map!");
		 input.close();
		 System.exit(0);

	 }
	 
	 public static Map<String, Integer> readFile(HashFunctionInterface hashFunction, Scanner input) {
		 
		 //this method reads the file, breaks it down into words, and passes each word to the 
		 //processWord method
		    
		    Map<String, Integer> map = new Map<String, Integer>(MAPSIZE);
		    URL url = MapExample.class.getResource("DoNotAdieu.txt");
		  //this text file is a "Hello World" program in the Shakespeare programming language
		    
		    try (FileReader fin = new FileReader(new File(url.getFile()));
		             Scanner fileReader = new Scanner(fin)) {

		            while (fileReader.hasNextLine()) {
		                String line = fileReader.nextLine();
		                String[] words = line.split("\\s+");
		                for (String word : words) {
		                    processWord(word, hashFunction, map);
		                }
		            }

		            return map;

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    return null;
		}


	    
	    public static Map<String, Integer> processWord(String word, HashFunctionInterface hashFunction, Map<String, Integer> map) {

	    	/*
	    	 * this method removes punctuation, converts to lowercase, and instantiates a 
	    	 * WordEntry object for each word. incrementing values if the word exists already
	    	 * is done in main, NOT in the map, because the map must remain GENERIC, for different
	    	 * type of MapEntryInterfaces and different usages
	    	 */
	    	
			String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
	    	WordEntry wordEntry = new WordEntry(cleanedWord, hashFunction);  
	        	if (map.contains(wordEntry)) {
	        		WordEntry retrievedWordEntry = (WordEntry) map.get(wordEntry);
	        		retrievedWordEntry.incrementValue();
	        	}
	        	else
	        		map.insert(wordEntry);
	        	
	        	return map;
	        
	    }
	    
	    public static void printMap(Map<String, Integer> map) {
	    	
	    	ArrayList<MapEntryInterface> list = new ArrayList<MapEntryInterface>();
	    	
	    	//use the map's iterator to retrieve each entry
	    	
	    	for (MapEntryInterface mapEntry: map)
	    		list.add(mapEntry);
	    	
	    	//sort by WordEntry's Comparable value
	    	
	    	Collections.sort(list);
	    	
	    	for(MapEntryInterface mapEntry: list)
	    		System.out.println(mapEntry);
	    }
	    public static int chooseFromMenu(Scanner input, int lowerbound, int upperbound, int priorChoice) {
			
			//an input validation that forces the user to choose a correct menu option
			
			int menuOption;
			while(!input.hasNextInt()) {
				System.out.println("Invalid input. Enter a number from the list: ");
				input.next();
			}
			menuOption = input.nextInt();
			 
			while (menuOption <= lowerbound || menuOption > upperbound) {
				System.out.println("Invalid input. Enter a number from the list: ");
				menuOption = input.nextInt();
				}
			while (menuOption == priorChoice) {
				System.out.println("Invalid input. You may not enter the same number twice: ");
				menuOption = input.nextInt();
				}
			return menuOption -1;
		}
	    
	    public static void wordCount(Map<String, Integer> map, Scanner input, 
	    		HashFunctionInterface hashFunction) {
	    	
	    	System.out.println("Enter a word: ");
	    	input.nextLine();	//clear the keyboard buffer
	    	String wordChoice = input.nextLine();
	    	
	    	//create a new WordEntry with the user's word
	    	WordEntry checkingEntry = new WordEntry(wordChoice, hashFunction);
	    	
	    	if (map.contains(checkingEntry)) {
	    		
	    		//we retrieve the matching WordEntry from the map and check its value
	    		WordEntry chosenEntry = (WordEntry) map.get(checkingEntry);
	    		
	    		System.out.println("There are " + chosenEntry.getValue() + 
	    				" instances of the word '" + chosenEntry.getKey() + "' in this file.");
	    		
	    		//we check how many words share the same index
	    		int bucketSize = map.getBucketSize(chosenEntry);
	    		if (bucketSize == 1)
	    			System.out.println("'" + chosenEntry.getKey() + 
	    					"' does not share a hashcode with any other word.");
	    		else
	    			System.out.println("'" + chosenEntry.getKey() + "' shares a hashcode with " 
	    					+ (bucketSize -1) + " other words.");
	    	}
	    	else
	    		System.out.println("Sorry, the map does not contain that word.");
	    }
		
	    public static void mapReport(Map<String, Integer> map) {
	    	
	    	//we simply call the map's toString method, which has all the information we need!
	    	
	    	System.out.println("\n" + map + "\n");
	    }
	    
	    public static void insertionReport(Map<String, Integer> map,
	    									Scanner input, HashFunctionInterface hashFunction) {
	    	
	    	//the user can choose from a list of 5 words
	    	
	    	String[] wordList = {"romeo", "pitcher", "cake", "juliet", "sky"};
	    	
	    	System.out.println("Choose a word from the menu to insert it into the map."
	    			+ "\nYou have 5 choices, and may choose the same word multiple times: ");
	    	for (int i  = 0; i < 5; i++) {
	    		
	    		for (int k = 0; k < wordList.length; k++)
	    			System.out.println((k + 1) + " - " + wordList[k]);
	    			
	    		int wordChoice = chooseFromMenu(input, 0, 5, -1);
	    		
	    		//we create a WordEntry with the user's choice
	    		WordEntry userEntry = new WordEntry(wordList[wordChoice], hashFunction);
	    		
	    		/*
	    		 * if the map contains that word, we retrieve it, report it's value,
	    		 * increment it, and report again
	    		 */
	    		if(map.contains(userEntry)) {
	    			System.out.println("The map already contains the word " 
	    					+ userEntry.getKey() + " at index " 
	    					+ userEntry.getIndex(map.size()) + ".");
	    			
	    			WordEntry retrievedWordEntry = (WordEntry) map.get(userEntry);
	    			System.out.println("That word was added to the map " + retrievedWordEntry.getValue() 
	    			+ " times.");
	    			retrievedWordEntry.incrementValue();
	    			System.out.println("Adding " + retrievedWordEntry.getKey() +
	    					"...\nThat word has now been added to the map " + retrievedWordEntry.getValue() 
	    					+ " times.");
	    		}
	    		//if the map does not contain that word, we insert it
	    		else {
	    			System.out.println("The map does not yet contain the word " + userEntry.getKey());
	    			System.out.println("We are inserting it at index: " + userEntry.getIndex(map.size()));
	    			map.insert(userEntry);
	    			System.out.println("The map now contains the word " + userEntry.getKey());
	    		}
	    			
	    	}
	    	System.out.println();
	    }
}
