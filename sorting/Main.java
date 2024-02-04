package sorting;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Student anne = new Student("Anne");
		Student betty = new Student("Betty");
		Student charlie = new Student("Charlie");
		Student daniel = new Student("Daniel");
		
		Student[] roster = {anne, betty, charlie, daniel};
		
		System.out.println("Would you like to input grades or view randomly generated grades?"
				+ " \nEnter 0 to input or 1 to randomly generate: ");
		
		while(!input.hasNextInt()) {
			System.out.println("Invalid input. Enter 0 to input or 1 to randomly generate: ");
			input.next();
		}
		int choice = input.nextInt();
		 
		while (choice != 0 && choice != 1) {
			System.out.println("Invalid input. Enter 0 to input or 1 to randomly generate: ");
			choice = input.nextInt();
			}
		
		if(choice == 0) {
			userInput(roster);
		}
	
		else if(choice == 1) {
			randomlyGenerateGrades(roster);
		}
		//else - input validation
		input.close();
		
		System.out.println("We will sort using both MergeSort and QuickSort.");
		QuickSort<Student> quicksort = new QuickSort<Student>(roster);
		Student[] quicksortedRoster = quicksort.getSortedArray();
		
		MergeSort<Student> mergesort = new MergeSort<Student>(roster);
		Student[] mergesortedRoster = mergesort.getSortedArray();
		  
		  System.out.println("\nHere is the roster sorted by MergeSort\n");
		  for(Student student: mergesortedRoster)
		  		System.out.println(student);
		  
		  System.out.println("\nHere is the roster sorted by QuickSort\n");
		  for(Student student: quicksortedRoster)
		  		System.out.println(student);
		 
	}
	
	public static void userInput(Student[] roster) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a grade for each student.");
		for(Student student: roster) {
			System.out.println(student.getName() + ": ");
			
			while(!input.hasNextInt()) {
				System.out.println("Invalid input. Enter a number from 0 to 100: ");
				input.next();
			}
			int grade = input.nextInt();
			 
			while (0 > grade || grade > 100) {
				System.out.println("Invalid input. Enter a number from 0 to 100: ");
				grade = input.nextInt();
				}
			
			student.setGrade(grade);
		}
		input.close();
		//input validation!
	}
	
	public static void randomlyGenerateGrades(Student[] roster) {
		Random randomGrade = new Random();
		for(Student student: roster) {
			int grade = randomGrade.nextInt(63, 101);
			student.setGrade(grade);
			System.out.println(student);
		}
	}
}
