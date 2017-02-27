package org.fabasoad.exceptions;

import org.junit.Test;

/**
 * @author Yevhen Fabizhevskyi
 * @date 27.02.2017.
 */
public class ExceptionRunner {

    public static void main(String[] args) throws ValidationException {
        validateIsEmpty("");
        System.out.println("I am here");
    }

    public static void throwThrowable() throws Throwable {
        throw new Throwable();
    }

    public static void validateIsNull(String val) throws ValidationException {
        if (val == null) {
            throw new ValidationException("Value is null");
        }
    }

    public static void validateIsEmpty(String val) throws ValidationException {
        if (val.isEmpty()) {
            throw new ValidationException("Value is empty");
        }
    }

    @Test
    public void test1() {
        String val1 = null;

        try {
            validateIsNull(val1);
            validateIsEmpty(val1);
        } catch (ValidationException | NullPointerException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        } finally {
            // connection.close();
        }

        try {
            throwThrowable();
        } catch (Exception throwable) {
            System.out.println("Exception");
        } catch (Throwable throwable) {
            System.out.println("Throwable");
        }
    }
}
