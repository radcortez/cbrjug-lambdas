package pt.coimbra.jug.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static pt.coimbra.jug.lambda.Person.Gender.FEMALE;
import static pt.coimbra.jug.lambda.Person.Gender.MALE;

/**
 * @author Roberto Cortez
 */
public class LambdaChallenge {
    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person("John", 20, MALE));
        persons.add(new Person("Michael", 25, MALE));
        persons.add(new Person("Carl", 10, MALE));
        persons.add(new Person("Paul", 45, MALE));
        persons.add(new Person("Scott", 75, MALE));
        persons.add(new Person("Anna", 90, FEMALE));
        persons.add(new Person("Jessica", 60, FEMALE));
        persons.add(new Person("Cindy", 5, FEMALE));
        persons.add(new Person("Heather", 37, FEMALE));
    }

    public static void main(String[] args) {
        persons.clear();
        lambdaChallenge();
        usualWay();
    }

    // Count how many seconds you need to read the following code.
    private static void usualWay() {
        Person oldest = null;
        for (Person person : persons) {
            if (oldest == null) {
                if (person.getGender().equals(MALE)) {
                    oldest = person;
                }
            } else {
                if (person.getAge() > oldest.getAge() && person.getGender().equals(MALE)) {
                    oldest = person;
                }
            }
        }

        if (oldest == null) {
            System.out.println("No persons!");
        } else {
            System.out.println(oldest.getName());
        }
    }

    // Now with lambdas and stream API. The code does the same thing. How longer did it took you in this form? :)
    private static void lambdaChallenge() {
        String personName = persons.stream()
                                   .filter(p -> p.getGender().equals(MALE))
                                   .max(Comparator.comparing(Person::getAge))
                                   .map(Person::getName)
                                   .orElse("No persons!");
        System.out.println(personName);
    }
}
