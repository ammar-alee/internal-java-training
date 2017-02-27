package org.fabasoad.annotations;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
public class Class4 {

    private static Class4 instance = new Class4();
    private int statistics;

    public static Class4 self() {
        return instance;
    }

    public int getResult() {
        return statistics;
    }

    public void push() {
        statistics++;
    }
}
