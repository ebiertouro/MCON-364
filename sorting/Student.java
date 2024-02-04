package sorting;

public class Student implements Comparable<Student> {
	
	private String name;
	private int grade;
	
	public Student(String name, int grade) {
		this.name = name;
		this.grade = grade;
	}
	
	public Student(String name) {
		this.name = name;
		this.grade = 0;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String getName() {
		return name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	@Override
	public int compareTo(Student otherStudent) {
		return Integer.compare(this.grade, otherStudent.getGrade());
	}
	
	@Override
	public String toString() {
		return "Name: " + name + ", Grade: " + grade;
	}
	 
	}

