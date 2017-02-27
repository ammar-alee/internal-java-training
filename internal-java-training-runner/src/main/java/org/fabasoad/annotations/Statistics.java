package org.fabasoad.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Statistics {

    String getInstanceMethod() default "getInstance";

    String getStatisticsMethod() default "getStatistics";
}
