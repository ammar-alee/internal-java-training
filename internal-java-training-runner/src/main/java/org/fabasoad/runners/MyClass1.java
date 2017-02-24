package org.fabasoad.runners;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.02.2017.
 */
class MyClass1 implements Runnable, Comparable<MyClass1> {

    private int val;

    public MyClass1(int val) {
        this.val = val;
    }

    public int compareTo(MyClass1 o) {
        return Integer.compare(val, o.val);
    }

    public void run() {
        System.out.println(val);
    }
}
