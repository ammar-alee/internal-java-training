package org.fabasoad.annotations2;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
@StatisticsClass(getInstanceMethod = "self")
public class Class2 {

    private static Class2 instance = new Class2();
    private int statistics;

    public static Class2 self() {
        return instance;
    }

    @StatisticsMethod
    public int getStatistics() {
        return statistics;
    }

    public void push() {
        statistics++;
    }
}
