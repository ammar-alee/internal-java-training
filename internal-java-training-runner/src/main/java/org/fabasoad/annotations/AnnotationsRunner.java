package org.fabasoad.annotations;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
public class AnnotationsRunner {

    private static Class[] classes = {
        Class1.class,
        Class2.class,
        Class3.class,
        Class4.class
    };

    @Test
    public void testAnnotations() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        run();
        showStatistics();
    }

    private void showStatistics() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Class clazz : classes) {
            if (clazz.isAnnotationPresent(Statistics.class)) {
                Statistics annotation = (Statistics) clazz.getAnnotation(Statistics.class);
                String instanceMethod = annotation.getInstanceMethod();
                String statisticsMethod = annotation.getStatisticsMethod();

                Method getInstanceMethod = clazz.getMethod(instanceMethod);
                Object obj = getInstanceMethod.invoke(clazz);
                Method getStatisticsMethod = clazz.getMethod(statisticsMethod);
                Object result = getStatisticsMethod.invoke(obj);
                System.out.printf("%s: %d\n", clazz.getSimpleName(), result);
            }
        }
    }

    private void run() {
        Class1.getInstance().push();
        Class1.getInstance().push();
        Class2.self().push();
        Class2.self().push();
        Class2.self().push();
        Class3.self().push();
        Class4.self().push();
        Class4.self().push();
    }
}
