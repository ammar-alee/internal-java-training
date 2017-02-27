package org.fabasoad.annotations2;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
@StatisticsClass(getInstanceMethod = "self")
public class Class3 {

    private static Class3 instance = new Class3();
    private int statistics;

    public static Class3 self() {
        return instance;
    }

    @StatisticsMethod
    public int getResult() {
        return statistics;
    }

    public void push() {
        statistics++;
    }
}
