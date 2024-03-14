package graphing;
import java.util.*;

public class FriendshipModeling {
	
	final static int NEUTRAL = 5;
	final static int FRIEND = 10;
	final static int CLOSEFRIEND = 15;
	final static int BFF = 20;
	
	public static void main(String[] args) {
		
		//welcome the user and display the adjacency table
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to our Friendship Modeling program!");
		
		Parser parser = new ClosenessParser();
	
		WeightedGraph graph  = new WeightedGraph(10, parser);
		
		createRelationships(graph);
		
		System.out.println("Here is an adjacency table of relationships: \n\n" + graph + "\n\n");
		System.out.println("Menu: \n\t1 - Trace three paths through the graph." + 
		"\n\t2 - Find the shortest path from a given person to every other person.");
		int choice = chooseFromMenu(input, 0, 2, -1);
		
		 if(choice == 0) {
				for (int i = 0; i < 3; i++) {
					tracePath(graph, i +1, input);
				}
		 }
		else if (choice == 1) {
			dijkstra(graph, input);
		}
		
		input.close();
		
	}
	
	public static void createRelationships(WeightedGraph graph) {
		
		//populate our graph
		
		String ayala = "Ayala";
		String batya = "Batya";
		String chumie = "Chumie";
		String devorah = "Devorah";
		String esther = "Esther";
		String faigy = "Faigy";

		graph.addVertex(ayala);
		graph.addVertex(batya);
		graph.addVertex(chumie);
		graph.addVertex(devorah);
		graph.addVertex(esther);
		graph.addVertex(faigy);
		
		
		Relationship ayalaToEsther = new Relationship(ayala, esther, NEUTRAL);
		Relationship batyaToAyala = new Relationship(batya, ayala, BFF);
		Relationship estherToFaigy = new Relationship(esther, faigy, CLOSEFRIEND);
		Relationship batyaToDevorah = new Relationship(batya, devorah, FRIEND);
		Relationship faigyToChumie = new Relationship(faigy, chumie, NEUTRAL);
		Relationship faigyToBatya = new Relationship (faigy, batya, NEUTRAL);
		Relationship devorahToEsther = new Relationship(devorah, esther, CLOSEFRIEND);
		Relationship devorahToAyala = new Relationship(devorah, ayala, FRIEND);
		Relationship chumieToFaigy = new Relationship(chumie, faigy, BFF);
	
		graph.addEdge(devorahToAyala);
		graph.addEdge(batyaToDevorah);
		graph.addEdge(chumieToFaigy);
		graph.addEdge(faigyToChumie);
		graph.addEdge(batyaToAyala);
		graph.addEdge(faigyToBatya);
		graph.addEdge(ayalaToEsther);
		graph.addEdge(estherToFaigy);
		graph.addEdge(devorahToEsther);
	}
	
	public static void dijkstra(WeightedGraph graph, Scanner input) {
		
		//the user selects a person from the graph and dijkstra returns the distance to every other person
		
		Dijkstra dijkstra = new Dijkstra();
		
		int personNum = 1;
		
		ArrayList<String> people = graph.getVertexes();
		
		for (String person: people) {	
			System.out.println("Person #" + personNum + ": " + person);
			personNum++;
		}
		
		System.out.println("\nChoice 1: ");
		int choice = chooseFromMenu(input, 0, 6, -1);
		
		String person1 = graph.getVertexString(choice); 
		
		System.out.println("\nYou have selected " + person1);
		
		dijkstra.shortestPaths(graph, graph.getVertexString(choice));

	}
	
	public static void tracePath(WeightedGraph graph, int counter, Scanner input) {
		
		//the user selects 2 people from the menu and is given the path between them

		System.out.println("\nFor path number " + counter + 
				", enter two choices from the menu to trace their relationship path: ");

		System.out.println();
		
		int personNum = 1;
		
		ArrayList<String> people = graph.getVertexes();
		for (String person: people) {
			
			System.out.println("Person #" + personNum + ": " + person);
			personNum++;
		}
		
		System.out.println("\nChoice 1: ");
		int choice1 = chooseFromMenu(input, 0, 6, -1);
		
		String person1 = graph.getVertexString(choice1); 
		
		System.out.println("\nYou have selected " + person1);
		
		System.out.println("\nChoice 2: ");
		int choice2 = chooseFromMenu(input, 0, 6, choice1+1);
		
		String person2 = graph.getVertexString(choice2);
		
		System.out.println("\nYou have selected " + person2);
		
		boolean pathExists = graph.breadthFirstSearch(person1, person2);
		
		System.out.println("\nThere is a path between " + person1 + " and " 
		+ person2 + ": " + pathExists);
		if (pathExists) {
			System.out.println("The weight of this path is " + graph.getPathWeight() + ".");
		}	
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
	
	
}
