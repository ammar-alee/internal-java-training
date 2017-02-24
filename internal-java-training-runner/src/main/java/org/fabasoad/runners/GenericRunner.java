package org.fabasoad.runners;

import java.util.Collection;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.02.2017.
 */
public class GenericRunner {

    public void test(String str) {
    }

    public void test(Collection<Integer> col) {
    }

    public static void main(String[] args) {
        MyClass1 myClass1 = new MyClass1(2);
        MyClass1 myClass2 = new MyClass1(4);
//        List c;
//        c.get(1).
        run(myClass1, myClass2);
    }

    private static <T extends Runnable & Comparable<T>> void run(T o1, T o2) {
        if (o1.compareTo(o2) > 0) {
            o1.run();
        } else {
            o2.run();
        }
        Parent<Child> p = new Child();
        p.getChild().print();
    }
}

interface Parent<C> {

    C getChild();
}

class Child implements Parent<Child> {

    public void print() {
        System.out.println("hello");
    }

    public Child getChild() {
        return this;
    }
}