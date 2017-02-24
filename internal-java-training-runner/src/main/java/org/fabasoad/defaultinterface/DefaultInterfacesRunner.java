package org.fabasoad.defaultinterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.02.2017.
 */
public class DefaultInterfacesRunner {

    public static void main(String[] args) {
        Iterable<Integer> c = new ArrayList<Integer>();
        I2 myClass = new C1();
        myClass.run(Collections.singletonList(new I1() {
            @Override
            public void print() {
                System.out.println("hi");
            }
        }));
    }
}

interface I1 {

    void print();
}

interface I2 {

    void hello();

    default void run(Collection<I1> col) {
        hello();
        for (I1 item : col) {
            item.print();
        }
    }
}

class C1 implements I2 {
    @Override
    public void hello() {

    }
}