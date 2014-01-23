package pt.coimbra.jug.lambda;

import java.util.*;
import java.util.function.Consumer;

import static pt.coimbra.jug.lambda.Person.Gender.*;

/**
 * @author Roberto Cortez
 */
public class LambdaExample {
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
        // Old way. Several concerns. Start the cycle, check variables. Nightmares with IndexOutOfBoundsException.
        System.out.println("\nRegular for");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }

        // Improved way to iterate elements, but we're still in control over the iteration.
        System.out.println("\nEnhanced for Loop");
        for (Person person : persons) {
            System.out.println(person);
        }

        // We're no longer in control over the iteration. We only need to worry with the behaviour that we want to
        // implement. However this is a very expressive form and requires a lot of boiler plate code.
        System.out.println("\nFor each inner class");
        persons.stream()
                .forEach(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) {
                        System.out.println(person);
                    }
                });

        // First lambda. We have arguments, body and return. Im this caase the argument is the (Person person). The
        // invocation is performed using the arrow operator "->". The body is enclosed inside the brackets {} and there
        // is no return type (it's void). Check java.util.function.Consumer.
        System.out.println("\nFor each lambda");
        persons.stream()
                .forEach((Person person) -> { System.out.println(person); });

        // Simplified lambda. No need for Person type declaration and the enclosing brackets can be omitted if the body
        // only had 1 line.
        System.out.println("\nFor each simplified lambda");
        persons.stream()
                .forEach(person -> System.out.println(person));

        // If the lambda is only calling another method that matches the argument thats it's possible to replace it with
        // a method reference and call the method using the operator "::".
        System.out.println("\nFor each method reference");
        persons.stream()
                .forEach(System.out::println);

        System.out.println("\nPersons with age > 18 and ordered by name the usual way");
        complicatedExample();
        System.out.println("\nPersons with age > 18 and ordered by name using lambdas");
        simpleLambda();
    }

    // In the following examples we want to filter the persons with aga greater than 18, eliminate the duplicates,
    // order by name and finally print the names.

    // Before Java 8.
    private static void complicatedExample() {
        Set<Person> personsNoDuplicates = new HashSet<>();
        for (Person person : persons) {
            if (person.getAge() >= 18) {
                personsNoDuplicates.add(person);
            }
        }

        List<Person> personsSorted = new ArrayList<>(personsNoDuplicates);
        Collections.sort(personsSorted, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Person person : personsSorted) {
            System.out.println(person.getName());
        }
    }

    // With Java 8.
    private static void simpleLambda() {
        persons.stream()
                .filter(p -> p.getAge() >= 18)
                .distinct()
                .sorted(Comparator.comparing(Person::getName))
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
