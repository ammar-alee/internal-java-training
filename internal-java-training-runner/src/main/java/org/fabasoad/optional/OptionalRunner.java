package org.fabasoad.optional;

import org.fabasoad.Person;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author efabizhevsky
 * @date 2/23/2017.
 */
public class OptionalRunner {

    private Person[] persons = {
            new Person("John", "Doe", 35),
            new Person("Maria", "Doe", 24),
            new Person("Eugene", "Fabizhevsky", 28),
            new Person("George", "Washington", 120)
    };

    private Person findUserByName1(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    private Optional<Person> findUserByName2(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    @Test
    public void emptyExample() {
        Optional.ofNullable(findUserByName1("George")).ifPresent(p -> System.out.println(p.getSurname()));

        findUserByName2("Anatoliy").ifPresent(p -> System.out.println(p.getSurname()));
    }

    @Test
    public void equalsExample() {
        System.out.println(Optional.of("test").equals(Optional.of("test")));
        System.out.println(Optional.empty().equals(Optional.empty()));
    }

    @Test
    public void filterExample() {
        findUserByName2("George")
                .filter(p -> p.getSurname().equals("Washington"))
                .ifPresent(p -> System.out.println(p.getSurname()));
    }

    @Test
    public void getExample() {
        Optional.of("ddd").get(); // ok
        Optional.empty().get(); // Exception!
    }

    @Test
    public void ifPresentIsPresentExample() {
        Optional<Person> person = findUserByName2("George1")
                .filter(p -> p.getSurname().equals("Washington"));
        if (person.isPresent() /* the same: person != Optional.<Person>empty() */) {
            System.out.println(person.get().getSurname());
        } else {
            System.out.println("User not found");
        }
    }

    @Test
    public void mapFlatMapExample() {
        findUserByName2("George").map(Person::getAge).ifPresent(System.out::println);

        Optional.ofNullable(findUserByName2("Eugene"))
                .flatMap(Function.identity())
                .ifPresent(p -> System.out.println(p.getSurname()));
    }

    @Test
    public void ofOfNullableExample() {
        System.out.println(Optional.ofNullable(null) == Optional.empty());
        Optional.of(null); // Exception!
    }

    @Test
    public void orElseOrElseGetOrElseThrowExample() {
        // orElse
        String surname1 = findUserByName2("George")
                .map(p -> (String) null)
                .map(p -> {
                    System.out.println("hello!");
                    return p;
                })
                .orElse("User not found");
        System.out.println(surname1);

        // orElseGet
        String surname2 = findUserByName2("George")
                .map(Person::getSurname)
                .orElseGet(new Supplier<String>() {
                    @Override
                    public String get() {
                        return "User not found";
                    }
                });
        System.out.println(surname2);

        // orElseThrow
        Person person = findUserByName2("George3")
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(person.getSurname());
    }

    @Test
    public void toStringExample() {
        System.out.println(Optional.of(new Person("John", "Doe", 35)).toString());
        System.out.println(Optional.empty().toString());
    }
}
