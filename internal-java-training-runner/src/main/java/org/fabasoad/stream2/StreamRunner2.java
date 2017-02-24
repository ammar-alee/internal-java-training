package org.fabasoad.stream2;

import org.fabasoad.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author efabizhevsky
 * @date 2/23/2017.
 */
public class StreamRunner2 {

    private static void builderExample() {
        Stream.builder().add(1).add(2).add(4).build().forEach(System.out::println);
    }

    private static void reduceExample() {
        Person person1 = new Person("John", "Doe", 35);
        Person person2 = new Person("Maria", "Doe", 24);

//        Arrays.asList(person1, person2).forEach(p -> p.setAge(p.getAge() + 1));

        String result1 = Arrays.asList("hhh", "ggg", "jjj").stream().reduce("lll", (x1, x2) -> x1 + " " + x2);
        Arrays.asList(1, 3, 4).stream().reduce(Integer::sum /* the same as: (x1, x2) -> x1 + x2 */).ifPresent(r -> {

        });

        System.out.println(result1);
    }

    private static void skipExample() {
        Arrays.asList("John", "Maria", "Eugene", "Elena").stream()
                .skip(1)
                .limit(2)
                .forEach(System.out::println);
    }

    private static void sortedExample() {
        Arrays.asList("John", "Maria", "Eugene", "Elena").stream()
                .sorted((o1, o2) -> -2)
                .forEach(System.out::println);
    }

    private static void toArrayExample() {
        List<String> list = Arrays.asList("John", "Maria", "Eugene", "Elena");
        Object[] objects = list.stream().toArray();
        String[] strings1 = new String[4];
        String[] strings = list.stream().toArray(StreamRunner2::create);
        System.out.println(String.join(",", strings));
    }

    private static String[] create(int size) {
        return new String[size];
    }

    public static void main(String[] args) {
        builderExample();
    }
}
