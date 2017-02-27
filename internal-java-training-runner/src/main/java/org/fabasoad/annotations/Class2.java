package org.fabasoad.annotations;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
@Statistics(getInstanceMethod = "self")
public class Class2 {

    private static Class2 instance = new Class2();
    private int statistics;

    public static Class2 self() {
        return instance;
    }

    public int getStatistics() {
        return statistics;
    }

    public void push() {
        statistics++;
    }
}
