package org.fabasoad.annotations;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
@Statistics(
        getInstanceMethod = "self",
        getStatisticsMethod = "getResult"
)
public class Class3 {

    private static Class3 instance = new Class3();
    private int statistics;

    public static Class3 self() {
        return instance;
    }

    public int getResult() {
        return statistics;
    }

    public void push() {
        statistics++;
    }
}
