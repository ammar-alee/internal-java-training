package org.fabasoad.annotations;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
@Statistics
public class Class1 {

    private static Class1 instance = new Class1();
    private int statistics;

    public static Class1 getInstance() {
        return instance;
    }

    public int getStatistics() {
        return statistics;
    }

    public void push() {
        statistics++;
    }
}
