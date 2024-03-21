package hashing;

import java.io.*;
import java.util.*;

public class MapExample {
	
	final static int MAPSIZE = 500;
	
	 public static void main(String[] args) {
		 
		 Scanner input = new Scanner(System.in);
		 
		 String filePath = "C:/Users/Bier/Desktop/touro/Data Structures/Do Not Adieu, a play in two acts.txt";
		    
		 System.out.println("Welcome! This program reads a file and creates a map of its word frequency.");
		 
		 System.out.println("To create a map, you need to choose your prefered hash function: ");
		 System.out.println("1 - naive\n2 - sophisticated");
		 
		 int hashChoice = chooseFromMenu(input, 0, 2, -1);
		 
		 HashFunctionInterface hashFunction;
		 if (hashChoice == 0)
			 hashFunction = new NaiveHashFunction();
		 else //if (hashChoice == 1)
			 hashFunction = new SophisticatedHashFunction();
		 
		Map<String, Integer> map = readFile(hashFunction, filePath);
			
		 System.out.println("Your map has been created! Choose what to do with it: ");
		 System.out.println("1 - View the word count for a particular word"
		 		+ " and how many words share its map location.");
		 System.out.println("2 - View words in descending order according to word count.");
		 System.out.println("3 - View a report on the internal structure of the map.");
		 
		 int actionChoice = chooseFromMenu(input, 0, 3, -1);
		 
		 if (actionChoice == 0)
			 wordCount(map, input, hashFunction);
		 else if (actionChoice == 1)
			 printMap(map);
		 else //if (actionChoice ==2)
			 mapReport(map);
		 
		 System.out.println("Thank you for using our map!");
		 input.close();
		 System.exit(0);

	 }
	 
	 public static Map<String, Integer> readFile(HashFunctionInterface hashFunction, String filePath) {
		 
		    
		    Map<String, Integer> map = new Map<String, Integer>(MAPSIZE);

		    try (FileReader fileReader = new FileReader(filePath);
		         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

		        String line;
		        while ((line = bufferedReader.readLine()) != null) {
		            String[] words = line.split("\\s+");
		            for (String word : words) {
		                processWord(word, hashFunction, map);
		            }
		        }
		        
		        return map; // Moved outside of the while loop

		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return null;
		}


	    
	    public static Map<String, Integer> processWord(String word, HashFunctionInterface hashFunction, Map<String, Integer> map) {

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
	    	
	    	for (MapEntryInterface mapEntry: map)
	    		list.add(mapEntry);
	    	
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
	    
	    public static void wordCount(Map<String, Integer> map, Scanner input, HashFunctionInterface hashFunction) {
	    	
	    	System.out.println("Enter a word: ");
	    	input.nextLine();
	    	String wordChoice = input.nextLine();
	    	WordEntry checkingEntry = new WordEntry(wordChoice, hashFunction);
	    	
	    	if (map.contains(checkingEntry)) {
	    		WordEntry chosenEntry = (WordEntry) map.get(checkingEntry);
	    		System.out.println("There are " + chosenEntry.getValue() + 
	    				" instances of the word '" + chosenEntry.getKey() + "' in this file.");
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
	    	System.out.println("\n" + map + "\n");
	    }
}
