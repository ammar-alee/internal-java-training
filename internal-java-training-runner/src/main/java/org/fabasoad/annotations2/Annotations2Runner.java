package org.fabasoad.annotations2;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
public class Annotations2Runner {

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
            if (clazz.isAnnotationPresent(StatisticsClass.class)) {
                StatisticsClass annotation = (StatisticsClass) clazz.getAnnotation(StatisticsClass.class);
                String instanceMethod = annotation.getInstanceMethod();

                Object obj = clazz.getMethod(instanceMethod).invoke(clazz);

                Method streamMethod = Arrays.class.getMethod("stream", Object[].class);
                Stream stream = (Stream) streamMethod.invoke(Arrays.class, new Object[] { clazz.getMethods() });
                stream
                        .filter(m -> ((Method) m).isAnnotationPresent(StatisticsMethod.class))
                        .forEach(m -> {
                            try {
                                Object result = ((Method) m).invoke(obj);
                                System.out.printf("%s: %d\n", clazz.getSimpleName(), result);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });
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
