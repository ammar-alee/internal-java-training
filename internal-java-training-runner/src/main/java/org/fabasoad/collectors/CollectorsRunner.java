package org.fabasoad.collectors;

import org.fabasoad.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yevhen Fabizhevskyi
 * @date 24.02.2017.
 */
public class CollectorsRunner {
    private static Person[] persons = {
        new Person("John", "Doe", 35),
        new Person("Maria", "Doe", 24),
        new Person("Eugene", "Fabizhevsky", 28),
        new Person("George", "Washington", 120)
    };

    private static void averagingIntExample() {
        Double avg = Arrays.stream(persons).collect(Collectors.averagingInt(Person::getAge));
        System.out.println(avg);
    }

    private static void collectingAndThenExample() {
        Integer size = Arrays.stream(persons).collect(Collectors.collectingAndThen(
            Collectors.toSet(),
            Set::size
        ));
        System.out.println(size);
    }

    private static void countingExample() {
        Long size = Arrays.stream(persons)
                .filter(p -> p.getAge() > 30)
                .filter(p -> p.getName().startsWith("J"))
                .collect(Collectors.counting());
        System.out.println(size);
    }

    private static void groupingByExample2() {
        Map<String, List<Person>> result =
                Arrays.stream(persons).collect(Collectors.groupingBy(Person::getSurname));
//        result.forEach((surname, persons) -> System.out.printf("%s: %d\n", surname, persons.size()));

        Map<String, LinkedList<Person>> result2 = Arrays.stream(persons).collect(Collectors.groupingBy(
                Person::getSurname,
                Collectors.toCollection(LinkedList::new)
        ));
//        result2.forEach((surname, persons) -> System.out.printf("%s: %d\n", surname, persons.size()));

        LinkedHashMap<String, LinkedList<Person>> result3 = Arrays.stream(persons)
                .collect(Collectors.groupingBy(
                        Person::getSurname,
                        LinkedHashMap::new,
                        Collectors.toCollection(LinkedList::new)
                ));

        result3.forEach((surname, persons) -> System.out.printf("%s: %d\n", surname, persons.size()));
    }

    private static void groupingByExample() {
        LinkedHashMap<String, Map<String, List<Person>>> result3 = Arrays.stream(persons)
                .collect(Collectors.groupingBy(
                        Person::getSurname,
                        LinkedHashMap::new,
                        Collectors.groupingBy(Person::getName)
                ));
        print(result3, "");
    }

    private static void print(Map map, String prefix) {
        for (Object key : map.keySet()) {
            System.out.println(prefix + key);
            if (map.get(key) instanceof Map) {
                print((Map) map.get(key), prefix + " ");
            } else if (map.get(key) instanceof Collection) {
                for (Object v : (Collection) map.get(key)) {
                    System.out.println(prefix + " " + ((Person) v).getName() + " " + ((Person) v).getSurname());
                }
            }
        }
    }

    private static void joiningExample() {
//        System.out.println(String.join(",", Arrays.asList("1", "2", "3")));
        String result = Arrays.stream(persons)
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Here are the names: ", "!"));
        System.out.println(result);
    }

    private static void mappingExample() {
        Set<Integer> ages = Arrays.stream(persons).collect(Collectors.mapping(
                Person::getAge,
                Collectors.toSet()
        ));
        ages.forEach(System.out::println);
    }

    private static void maxByMinByExample() {
        Arrays.stream(persons)
                .collect(Collectors.maxBy(Comparator.comparingInt(p -> p.getSurname().length())))
                .ifPresent(System.out::println);
    }

    private static void partitioningByExample() {
        Map<Boolean, Set<Person>> result = Arrays.stream(persons).collect(Collectors.partitioningBy(
                p -> p.getAge() > 30,
                Collectors.toSet()
        ));
        for (Map.Entry<Boolean, Set<Person>> entry : result.entrySet()) {
            System.out.println("Age > 30 = " + entry.getKey());
            for (Person person : entry.getValue()) {
                System.out.println(person.getName() + " " + person.getSurname());
            }
        }
    }

    private static void reducingExample() {
        Integer sum = Arrays.stream(persons)
                .map(Person::getAge)
                .collect(Collectors.reducing(0, Integer::sum));
        System.out.println(sum);
    }

    private static void summarizingIntExample() {
        double avg = Arrays.stream(persons)
                .collect(Collectors.summarizingInt(Person::getAge))
                .getAverage();
        System.out.println(avg);
    }

    private static void summingIntExample() {
        Integer result = Arrays.stream(persons).collect(Collectors.summingInt(Person::getAge));
        System.out.println(result);
    }

    private static void toCollectionExample() {
        LinkedList<String> persons = Arrays.stream(CollectorsRunner.persons)
                .map(p -> p.getName() + " " + p.getSurname())
                .collect(Collectors.toCollection(LinkedList::new));
        persons.forEach(System.out::println);
    }

    private static void toListToSetExample() {
        List<Person> filteredPersons = Arrays.stream(persons)
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.toList());
        Set<Person> filteredPersons2 = Arrays.stream(persons)
                .filter(p -> p.getSurname().startsWith("F"))
                .collect(Collectors.toSet());
        filteredPersons.forEach(System.out::println);
        filteredPersons2.stream()
                .map(p -> p.getName() + " " + p.getSurname())
                .forEach(System.out::println);
    }

    private static void toMapExample() {
        Map<String, Integer> persons = Arrays.stream(CollectorsRunner.persons).collect(Collectors.toMap(
                p -> p.getName() + " " + p.getSurname(),
                Person::getAge // CollectorsRunner::getAge
        ));
        persons.forEach((k, v) -> System.out.println(String.format(
                "Key: %s; Value: %s", k, v)));
    }

    private static int getAge(Person p) {
        return p.getAge();
    }

    public static void main(String[] args) {
        groupingByExample();
        // comment:
        // \u000d System.out.println("hello");
    }
}
