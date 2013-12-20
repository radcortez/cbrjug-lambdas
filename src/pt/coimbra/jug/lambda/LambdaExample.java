package pt.coimbra.jug.lambda;

import com.cortez.lambda.old.LambdaOld;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Comparator.comparing;
import static pt.coimbra.jug.lambda.Person.Gender.FEMALE;

/**
 * @author Roberto Cortez
 */
public class LambdaExample {
    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person("João", 20, Person.Gender.MALE));
        persons.add(new Person("Pedro", 25, Person.Gender.MALE));
        persons.add(new Person("Miguel", 10, Person.Gender.MALE));
        persons.add(new Person("José", 45, Person.Gender.MALE));
        persons.add(new Person("Rui", 75, Person.Gender.MALE));
        persons.add(new Person("Maria", 32, FEMALE));
        persons.add(new Person("Joana", 60, FEMALE));
        persons.add(new Person("Rita", 5, FEMALE));
        persons.add(new Person("Isabel", 37, FEMALE));
    }

    public static void main(String[] args) {
        // Antigamente. Várias preocupações. Começar o ciclo, verificar variáveis. Pesadelos com IndexOutOfBoundsException.
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }

        // Uma forma melhorada para percorrer os elementos, mas ainda assim sempre em controlo de tudo.
        for (Person person : persons) {
            System.out.println(person);
        }

        // Deixamos de estar em controlo da iteração para apenas nos termos que preocupar com o comportamento que
        // pretendemos obter. No entanto esta forma é muito expressiva e requer demasiado código.
        persons.stream()
                .forEach(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) {
                        System.out.println(person);
                    }
                });

        // Primeiro Lambda. Composto por argumentos de entrada, um corpo e argumentos de saída. Neste caso o argumento de
        // entrada é o (Person person) a invocação é efectuada através da seta -> o corpo está dentro das chavetas {} e
        // não existem argumentos de saída. Ver java.util.function.Consumer.
        persons.stream()
                .forEach((Person person) -> { System.out.println(person); });

        // Versão mais simplificada do Lambda. Não é necessário declarar o tipo Person e as chavetas podem ser omitida
        // se o corpo da função apenas tiver uma linha.
        persons.stream()
                .forEach(person -> System.out.println(person));

        // No caso do lambda não fizer mais nada a não ser a chamada de outro método, é possível substituir o lambda
        // directamente pelo nome do método que queremos invocar.
        persons.stream()
                .forEach(System.out::println);
    }

    // Nos seguinte exemplos, pretendemos filtrar as pessoas que tenham idade superior a 18, eliminar as duplicadas,
    // ordernar pelo nome e finalmente imprimir o nome.

    // Como poderiamos fazer antes de Java 8.
    private static void complicatedExample() {
        List<Person> persons = LambdaOld.persons;

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

    // Com Java 8.
    private static void simpleLambda() {
        LambdaOld.persons
                .stream()
                .filter(p -> p.getAge() >= 18)
                .distinct()
                .sorted(comparing(Person::getName))
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
