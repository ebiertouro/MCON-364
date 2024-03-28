package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentLambdas {
	
	public static void main(String[] args) {

	/*
	 * /// PREDICATE : 
/*
QUESTION 1: 
Write a Predicate<Student> called isAdult that tests if a student is an adult (age >= 18).

*/
	Predicate<Student> isAdult = student -> student.getAge() >=18;





// CONSUMER  : 
/*
QUESTION 2 : 

Write a Consumer<Student> called printName that prints a student's name.

*/

	Consumer<Student> printName = student -> System.out.println(student.getName());





// SUPPLIER   : 
/*
QUESTION 3 : 

Write a Supplier<Student>  called createDefaultStudent that 
returns a new student with a default name, age, and GPA.

*/


	Supplier<Student> createDefaultStudent = () -> new Student("Sam", 22, 3.6);



// FUNCTION   : 
/*
QUESTION 4 : 

Write a Function<Student, String> called getName that returns a student's name.*/


	Function<Student, String> getName = student -> student.getName();





// BIFUNCTION   : 
/*
QUESTION 5 : 

Write a BiFunction<Student, Double, Student> called updateGPA that
 updates a student's GPA and returns the student.

*/

	
	BiFunction<Student, Double, Student> updateGPA = 
			(student, gpa) -> {student.setGpa(gpa); return student;};




// BIPREDICATE   : 
/*
QUESTION 6 : 


Write a BiPredicate<Student, Double> called hasHighGpa that tests
 if a student's GPA is above a certain threshold.




			BiPredicate<Student, Double> hasHighGpa = 
					(student, gpa) -> student.getGpa() >= gpa;
					
					*/
			Predicate<Student> hasHighGpa = 
					student -> student.getGpa() >= 3.6;

// BICONSUMER   : 
/*
QUESTION 7 : 


Write a BiConsumer<Student, String> called updateName that updates a student's name.

*/

		BiConsumer<Student, String> updateName = 
				(student, name) -> student.setName(name);


//Question 8 
/*
Q: Create a list of students. Use the stream method to create a stream,
 then use filter to find all adult students (isAdult), 
map to get their names, and collect to put the names into a new list.


*/
			Student alice = new Student("alice", 19, 3.4);
			Student bob = new Student("bob", 17, 2.9);
			Student charlesVII = new Student ("Charles VII", 101, 4.0);
			Student daniella = new Student("daniella", 21, 3.7);
			
			ArrayList<Student> studentList = new ArrayList<>();
			studentList.add(alice);
			studentList.add(bob);
			studentList.add(charlesVII);
			studentList.add(daniella);
			
			List<String> nameList = studentList.stream()
					.filter(isAdult).map(getName).toList();
			
/*
			Question 9
			Use the randomStudentSupplier to generate a list of 100 students.
			Filter out students who are adults and have a GPA above 3.0 using isAdult and hasHighGpa.
			Use the appendToName BiConsumer to append " - Scholar" to the name of each of these students.
			Collect the names of these students into a list.
			*/
			
			  List<Student> students = Stream.generate(randomFemaleStudentSupplier())
			  .filter(isAdult.and(hasHighGpa))
			  .limit(100)
			  .peek(student -> student.setName(student.getName() + " - Scholar"))
			   .collect(Collectors.toList());
			  
			  List<String> studentNames = students.stream()
		                .map(Student::getName)
		                .collect(Collectors.toList());
		
	}


// Define a supplier to generate random female students
	
    public static Supplier<Student> randomFemaleStudentSupplier() {
        Random random = new Random();
        String[] femaleNames = {"Alice", "Emma", "Grace", "Olivia", "Sophia", "Isabella", "Mia", "Amelia"};
        int minAge = 18;
        int maxAge = 25;
        double minGpa = 2.5;
        double maxGpa = 4.0;


        return () -> {
            String name = femaleNames[random.nextInt(femaleNames.length)];
            int age = random.nextInt(maxAge - minAge + 1) + minAge;
            double gpa = minGpa + (maxGpa - minGpa) * random.nextDouble();
            return new Student(name, age, gpa);
        };
    }

    



/*
Question 10
Use the randomStudentSupplier to generate a list of 100 students.
Filter out students who have a GPA above a certain threshold using hasGpaAbove.
Use the addSuffixToName BiFunction to add a suffix to each student's name based 
on their GPA (suffix is "- Honors" if GPA is above 3.5, "- Regular" otherwise).
Collect the names of these students into a list.

 

*/
    public static Student addSuffixToName(Student student, double gpa) {
        String suffix = (gpa > 3.5) ? "- Honors" : "- Regular";
        student.setName(student.getName() + suffix);
        return student;
    }
    
    Predicate<Student> hasGpaAbove (double gpa){
    	return student -> student.getGpa() > gpa;
    }
    
    List<Student> studentsWithSuffix = Stream.generate(randomFemaleStudentSupplier())
    				.limit(100)
    				.filter(hasGpaAbove(3.5))
    				.map(student -> addSuffixToName(student, student.getGpa()))
    				.collect(Collectors.toList());



	
	 
}
