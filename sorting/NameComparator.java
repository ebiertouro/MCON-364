package sorting;
import java.util.*;

public class NameComparator implements Comparator<Student> {
	
	public int compare(Student student1, Student student2) {
	
		return student1.getName().compareTo(student2.getName());
	}

}
