package org.fabasoad.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.02.2017.
 */
public class FRunner {

    List<Consumer<Integer>> consumers = Arrays.asList(
            this::cons1,
            this::cons2,
            this::cons3
    );

    Runnable[] runners = {
            new Runnable() {
                @Override
                public void run() {
                    FRunner.this.invoke1();
                }
            },
            () -> {
                FRunner.this.invoke2();
            },
            () -> FRunner.this.invoke3(),
            this::invoke4
    };

    public static void main(String[] args) {
//        Interface1 o1 = new Interface1() {
//            @Override
//            public String call(String s) {
//                System.out.println(s);
//                return "";
//            }
//        };

        Interface1 o2 = s -> {
            return s + "o2";
        };
        String result = o2.call("hi ");
    }

    public void run() {
        for (Runnable runner : runners) {
            runner.run();
        }
    }

    public void run2() {
        for (int i = 0; i < consumers.size(); i++) {
            consumers.get(i).accept(i);
        }
    }

    private void invoke1() {
        System.out.printf("i1");
    }

    private void invoke2() {
        System.out.printf("i2");
    }

    private void invoke3() {
        System.out.printf("i3");
    }

    private void invoke4() {
        System.out.printf("i4");
    }

    private void cons1(int i) {
        System.out.print(i);
    }

    private void cons2(int i) {
        System.out.print(i);
    }

    private void cons3(int i) {
        System.out.print(i);
    }
}
