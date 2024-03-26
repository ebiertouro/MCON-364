package lambda;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<ShulMember> shulMembers = new ArrayList<>();
        Random random = new Random();
        String[] maleNames = {"Moshe", "Yitzchak", "Avraham", "Yaakov", "David", "Shmuel", "Yosef", "Eliyahu", "Chaim", "Mendel"};
        String[] femaleNames = {"Sarah", "Rivka", "Leah", "Rachel", "Chaya", "Miriam", "Esther", "Rochel", "Bracha", "Tzivia"};
        String[] lastNames = {"Cohen", "Levi", "Goldberg", "Katz", "Weiss", "Friedman", "Stein", "Rubin", "Adler", "Schwartz"};

        int maleCount = 0;
        int femaleCount = 0;

        for (int i = 0; i < 10; i++) {
            String lastName = lastNames[i];
            String maleName = maleNames[random.nextInt(maleNames.length)];
            String femaleName = femaleNames[random.nextInt(femaleNames.length)];
            Date birthDate = new Date(50 + random.nextInt(50), random.nextInt(12), random.nextInt(28));
            int yearsOfMembership = 3 + random.nextInt(29); // Membership for 3-31 years
            int numberOfChildren;
            do {
                numberOfChildren = 1 + random.nextInt(11); // Range from 1 to 11 children
            } while (numberOfChildren < 2 || numberOfChildren > 10); // Ensuring at least one child and maximum of 10

            String[] childrenNames = generateUniqueChildrenNames(numberOfChildren, maleNames, femaleNames, lastName, maleCount, femaleCount);

            ShulMember member = new ShulMember(lastName, maleName, birthDate, femaleName, lastName, childrenNames, yearsOfMembership);
            shulMembers.add(member);

            maleCount += numberOfChildren / 2;
            femaleCount += numberOfChildren / 2;
        }

        useLambdas(shulMembers);
        }


private static String[] generateUniqueChildrenNames(int numberOfChildren, 
		String[] maleNames, String[] femaleNames, String lastName, int maleCount, int femaleCount) {
Random random = new Random();
Set<String> uniqueNames = new HashSet<>();
String[] childrenNames = new String[numberOfChildren];

for (int i = 0; i < numberOfChildren / 2; i++) {
    String maleChildName = maleNames[maleCount % maleNames.length] + " " + lastName;
    String femaleChildName = femaleNames[femaleCount % femaleNames.length] + " " + lastName;

    uniqueNames.add(maleChildName);
    uniqueNames.add(femaleChildName);
    
    childrenNames[2 * i] = maleChildName;
    childrenNames[2 * i + 1] = femaleChildName;

    maleCount++;
    femaleCount++;
}

return childrenNames;
}

    
    public static void useLambdas(ArrayList<ShulMember> shulMembers) {
    	
    	for (ShulMember member: shulMembers)
    		System.out.println(member);
    	
    	System.out.println("\nHow many families belong to our shul?\n");
    	System.out.println(shulMembers.stream().count());
    	
    	System.out.println("\nHow long has each family been a member? (sorted)\n");
    	shulMembers.stream()
        	.collect(Collectors.groupingBy(
            ShulMember::getLastNameOfMember,
            Collectors.summarizingInt(ShulMember::getYearsOfMembership)))
        	.entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .forEach(entry -> System.out.println(entry.getKey() + ": "
            + entry.getValue().getSum() + " years"));

    	
    	System.out.println("\nHow old is each member? (sorted)\n");
    	shulMembers.stream()
    			.sorted(ShulMember::compareTo)
    			.forEach(member -> System.out.println(member.getFirstNameOfMember()
    					+ " " + member.getLastNameOfMember() + 
    					": " + calculateAge(member.getBirthDateOfMember()) + " years"));
    	
    	System.out.println("\nWho is each member's wife? (sorted)\n");
    	shulMembers.stream().
    				sorted((m1, m2) -> m1.getLastNameOfMember().compareTo(m2.getLastNameOfMember()))
    				.forEach(member -> System.out.println(member.getSpouseFirstName()
    						+ " " + member.getSpouseLastName()));
    	
    	System.out.println("\nWhich families have more than three children?\n");
    	shulMembers.stream()
    				.filter(member -> member.getChildrenNames().length > 3)
    				.map(ShulMember::getLastNameOfMember)
    				.distinct()
    				.forEach(System.out::println);
    				
    	System.out.println("\nWhich families have children that starts with the "
    			+ "letter 'd' or above? What are their names?\n");

    	shulMembers.stream()
    	        .filter(member -> Arrays.stream(member.getChildrenNames())
    	                .anyMatch(name -> Objects.nonNull(name)
    	                        && !name.isEmpty() && name.charAt(1) >= 'd'))
    	        .forEach(member -> {
    	            System.out.println(member.getLastNameOfMember() + ": ");
    	            Arrays.stream(member.getChildrenNames())
    	                    .filter(name -> Objects.nonNull(name)
    	                            && !name.isEmpty() && name.charAt(1) >= 'd')
    	                    .forEach(name -> System.out.println(name + ", "));
    	            System.out.println();
    	        });

 

    }
    
    private static int calculateAge(Date birthDate) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthday, today).getYears();
    }

}

